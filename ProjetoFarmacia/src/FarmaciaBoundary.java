import javafx.beans.binding.Bindings;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.scene.control.TableCell;


public class FarmaciaBoundary implements Tela{
        
    private Label lbID = new Label(""); 
    private TextField txtNome = new TextField(); 
    private TextField txtCidade = new TextField(); 
    private TextField txtBairro = new TextField();
    private TextField txtTelefone = new TextField();
    private TextField txtProprietario = new TextField();

    private FarmaciaControl farmaciaControl = new FarmaciaControl(); 

    private TableView<Farmacia> tableView = new TableView<>(); 

    public FarmaciaBoundary() throws FarmaciaException {
    }

    @Override
    public Pane render() {
        BorderPane panePrincipal = new BorderPane(); 
        GridPane paneForm = new GridPane(); 
        
        Button btnGravar = new Button("Gravar"); 
        btnGravar.setOnAction(e -> {
            try {
                farmaciaControl.gravar();
            } catch (FarmaciaException err) {
                new Alert(AlertType.ERROR, "Erro ao gravar", ButtonType.OK).showAndWait();
            }
            tableView.refresh(); 
        }); 

        Button btnPesquisar = new Button("Pesquisar"); 
        btnPesquisar.setOnAction(e -> {
            try {
                farmaciaControl.pesquisar();
            } catch (FarmaciaException erro2) {
                new Alert(AlertType.ERROR, "Erro ao gravar", ButtonType.OK).showAndWait();
            }
        });
        
        Button btnNovo = new Button("*"); 
        btnNovo.setOnAction(e -> farmaciaControl.limparTudo());

        paneForm.add(new Label("Id: "), 0, 0);
        paneForm.add(lbID, 1, 0);
        paneForm.add(new Label("Nome: "), 0, 1);
        paneForm.add(txtNome, 1, 1);
        paneForm.add(btnNovo, 2, 1);
        paneForm.add(new Label("Cidade: "), 0, 2);
        paneForm.add(txtCidade, 1, 2);
        paneForm.add(new Label("Bairro: "), 0, 3);
        paneForm.add(txtBairro, 1, 3);
        paneForm.add(new Label("Telefone: "), 0, 4);
        paneForm.add(txtTelefone, 1, 4);
        paneForm.add(new Label("Proprietario: "), 0, 5);
        paneForm.add(txtProprietario, 1, 5);


        paneForm.add(btnGravar, 0, 6);
        paneForm.add(btnPesquisar, 1, 6);

        ligacoes();
        gerarColunas();

        panePrincipal.setTop( paneForm );
        panePrincipal.setCenter(tableView);

        return panePrincipal;

    }

    public void gerarColunas(){

        if (!tableView.getColumns().isEmpty()) {
            return; 
        }

        TableColumn<Farmacia, Long> col1 = new TableColumn<>("Id"); 
        col1.setCellValueFactory(new PropertyValueFactory<Farmacia, Long>("id")); 

        TableColumn<Farmacia, String> col2 = new TableColumn<>("Nome"); 
        col2.setCellValueFactory(new PropertyValueFactory<Farmacia, String>("nome")); 

        TableColumn<Farmacia, String> col3 = new TableColumn<>("Cidade"); 
        col3.setCellValueFactory(new PropertyValueFactory<Farmacia, String>("cidade"));

        TableColumn<Farmacia, String> col4 = new TableColumn<>("Bairro"); 
        col4.setCellValueFactory(new PropertyValueFactory<Farmacia, String>("bairro"));
        
        TableColumn<Farmacia, String> col5 = new TableColumn<>("Telefone"); 
        col5.setCellValueFactory(new PropertyValueFactory<Farmacia, String>("telefone"));
    
        TableColumn<Farmacia, String> col6 = new TableColumn<>("Proprietario"); 
        col6.setCellValueFactory(new PropertyValueFactory<Farmacia, String>("proprietario"));

        tableView.getSelectionModel().selectedItemProperty()
        .addListener( (obs, antigo, novo) -> {
            if (novo != null) { 
                System.out.println("Nome: " + novo.getNome());
                farmaciaControl.paraTela(novo);
            }
        });

        Callback<TableColumn<Farmacia, Void>, TableCell<Farmacia, Void>> cb = 
        new Callback<>() {
            @Override
            public TableCell<Farmacia, Void> call(
                TableColumn<Farmacia, Void> param) {
                TableCell<Farmacia, Void> celula = new TableCell<>() { 
                    final Button btnApagar = new Button("Apagar");

                    {
                        btnApagar.setOnAction( e -> {
                            Farmacia farmacia = tableView.getItems().get( getIndex() );
                            try { 
                                farmaciaControl.excluir( farmacia ); 
                            } catch (FarmaciaException err) { 
                                new Alert(AlertType.ERROR, "Erro ao excluir", ButtonType.OK).showAndWait();
                            }
                        });
                    }

                    @Override
                    public void updateItem( Void item, boolean empty) {                             
                        if (!empty) { 
                            setGraphic(btnApagar);
                        } else { 
                            setGraphic(null);
                        }
                    }
                }; 
                return celula;            
            }
    }; 

    TableColumn<Farmacia, Void> col7 = new TableColumn<>("Ação");
    col7.setCellFactory(cb);

    tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
    tableView.setItems( farmaciaControl.getLista() );
}
public void ligacoes() { 
    farmaciaControl.idProperty().addListener( (obs, antigo, novo) -> {
        lbID.setText( String.valueOf(novo) );
    });
    Bindings.bindBidirectional(farmaciaControl.nomeProperty(), txtNome.textProperty());
    Bindings.bindBidirectional(farmaciaControl.cidadeProperty(), txtCidade.textProperty());
    Bindings.bindBidirectional(farmaciaControl.bairroProperty(), txtBairro.textProperty());
    Bindings.bindBidirectional(farmaciaControl.telefoneProperty(), txtTelefone.textProperty());
    Bindings.bindBidirectional(farmaciaControl.proprietarioProperty(), txtProprietario.textProperty());
}


}
