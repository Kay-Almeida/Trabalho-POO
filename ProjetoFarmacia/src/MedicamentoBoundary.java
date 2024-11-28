import javafx.scene.control.TableColumn;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.control.TableCell;

public class MedicamentoBoundary implements Tela {

    private Label lbID = new Label(""); 
    private TextField txtNome = new TextField();
    private TextField txtCategoria = new TextField();  
    private TextField txtFabricante = new TextField(); 
    private TextField txtPreco = new TextField();  

    private MedicamentoControl medicamentoControl = new MedicamentoControl();  

    private TableView<Medicamento> tableView = new TableView<>(); 

    public MedicamentoBoundary() throws FarmaciaException {
    }

    @Override
    public Pane render() {
        BorderPane panePrincipal = new BorderPane(); 
        GridPane paneForm = new GridPane(); 
        
        Button btnGravar = new Button("Gravar"); 
        btnGravar.setOnAction(e -> {
            try {
                medicamentoControl.gravar();
            } catch (FarmaciaException err) {
                new Alert(AlertType.ERROR, "Erro ao gravar", ButtonType.OK).showAndWait();
            }
            tableView.refresh(); 
        }); 

        Button btnPesquisar = new Button("Pesquisar"); 
        btnPesquisar.setOnAction(e -> {
            try {
                medicamentoControl.pesquisar();
            } catch (FarmaciaException erro1) {
                new Alert(AlertType.ERROR, "Erro ao gravar", ButtonType.OK).showAndWait();
            }
        });

        Button btnNovo = new Button("*"); 
        btnNovo.setOnAction(e -> medicamentoControl.limparTudo());
    
        paneForm.add(new Label("Id: "), 0, 0);
        paneForm.add(lbID, 1, 0);
        paneForm.add(new Label("Nome: "), 0, 1);
        paneForm.add(txtNome, 1, 1);
        paneForm.add(btnNovo, 2, 1);
        paneForm.add(new Label("Categoria: "), 0, 2);
        paneForm.add(txtCategoria, 1, 2);
        paneForm.add(new Label("Fabricante: "), 0, 3);
        paneForm.add(txtFabricante, 1, 3);
        paneForm.add(new Label("Preço: "), 0, 4);
        paneForm.add(txtPreco, 1, 4);

        paneForm.add(btnGravar, 0, 5);
        paneForm.add(btnPesquisar, 1, 5);

        ligacoes();
        gerarColunas();

        panePrincipal.setTop(paneForm);
        panePrincipal.setCenter(tableView);

        return panePrincipal;
    }
    public void gerarColunas() {
        
        if (!tableView.getColumns().isEmpty()) {
            return; 
        }

        TableColumn<Medicamento, Long> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));
    
        TableColumn<Medicamento, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory(new PropertyValueFactory<>("nome"));
    
        TableColumn<Medicamento, String> col3 = new TableColumn<>("Categoria");
        col3.setCellValueFactory(new PropertyValueFactory<>("categoria"));
    
        TableColumn<Medicamento, String> col4 = new TableColumn<>("Fabricante");
        col4.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
    
        TableColumn<Medicamento, Double> col5 = new TableColumn<>("Preço");
        col5.setCellValueFactory(new PropertyValueFactory<>("preco"));
    
        tableView.getSelectionModel().selectedItemProperty().addListener( (obs, antigo, novo) -> {
            if (novo != null) { 
                System.out.println("Nome: " + novo.getNome());
                medicamentoControl.entidadeParaTela(novo);
            }
        });

        Callback<TableColumn<Medicamento, Void>, TableCell<Medicamento, Void>> cb = 
            new Callback<>() {
                @Override
                public TableCell<Medicamento, Void> call(TableColumn<Medicamento, Void> param) {
                       TableCell<Medicamento, Void> celula = new TableCell<>() {  
                        final Button btnApagar = new Button("Apagar");
    
                        {
                            btnApagar.setOnAction(e -> {
                                Medicamento medicamento = tableView.getItems().get(getIndex());
                                try { 
                                    medicamentoControl.excluir(medicamento); 
                                } catch (FarmaciaException err) { 
                                    new Alert(AlertType.ERROR, "Erro ao excluir", ButtonType.OK).showAndWait();
                                }
                            });
                        }
                        
                        @Override
                        public void updateItem(Void item, boolean empty) {                             
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
        
    
        TableColumn<Medicamento, Void> col6 = new TableColumn<>("Ação");
        col6.setCellFactory(cb);
    
        tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6);
        tableView.setItems(medicamentoControl.getLista());
    }

    public void ligacoes() {
        medicamentoControl.idProperty().addListener((obs, antigo, novo) -> {
            lbID.setText(String.valueOf(novo));
        });
        Bindings.bindBidirectional(medicamentoControl.nomeProperty(), txtNome.textProperty());
        Bindings.bindBidirectional(medicamentoControl.categoriaProperty(), txtCategoria.textProperty());
        Bindings.bindBidirectional(medicamentoControl.fabricanteProperty(), txtFabricante.textProperty());
        Bindings.bindBidirectional(txtPreco.textProperty(), medicamentoControl.precoProperty(), new NumberStringConverter());

    }
}
