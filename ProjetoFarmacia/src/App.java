import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class App extends Application {
    private Map<String, Tela> telas = new HashMap<>(); 

    @Override
        public void start(Stage stage) throws Exception {
            telas.put("Farmacia", new FarmaciaBoundary());
            telas.put("Medicamento", new MedicamentoBoundary());
    
            BorderPane panePrincipal = new BorderPane();
    
            MenuBar menuBar = new MenuBar();
            Menu mnuCadastro = new Menu("Cadastro");
    
            MenuItem mnuItemMedicamento = new MenuItem("Medicamentos");
            mnuItemMedicamento.setOnAction(e -> panePrincipal.setCenter(telas.get("Medicamento").render()));
    
            MenuItem menuItemFarmacia = new MenuItem("Farmácia");
            menuItemFarmacia.setOnAction(e -> panePrincipal.setCenter(telas.get("Farmacia").render()));
    
            mnuCadastro.getItems().addAll(menuItemFarmacia, mnuItemMedicamento);
    
            menuBar.getMenus().addAll(mnuCadastro);
    
            panePrincipal.setTop(menuBar);
    
            Scene scn = new Scene(panePrincipal, 800, 600);
            stage.setScene(scn);
            stage.setTitle("Catálogo de Farmácias");
            stage.show();  
    }

    public static void main(String[] args) {
        launch(args);
    }
}