import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class MedicamentoControl {

    private LongProperty id = new SimpleLongProperty(0l); 
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty categoria = new SimpleStringProperty("");
    private StringProperty fabricante = new SimpleStringProperty(""); 
    private DoubleProperty preco = new SimpleDoubleProperty(0.0);

    private ObservableList<Medicamento> lista = FXCollections.observableArrayList(); 

    private MedicamentoDAO medicamentoDAO;
    
    private int contador = 0; 

    public MedicamentoControl() throws FarmaciaException{
        medicamentoDAO = new MedicamentoDAOImpl(); 
    }

    public void excluir(Medicamento m) throws FarmaciaException{
        medicamentoDAO.excluir(m);
        pesquisarTodos(); 
    }

    public void gravar() throws FarmaciaException{
        Medicamento medicamento = telaParaEntidade();
        if (id.get() == 0) {
            medicamento.setId(++contador);
            medicamentoDAO.inserir(medicamento);
        }else{
            medicamentoDAO.atualizar(medicamento);
        }
        limparTudo();
        pesquisarTodos(); 
    }

    public void pesquisar() throws FarmaciaException{
        lista.clear(); 
        lista.addAll(medicamentoDAO.pesquisarPorNome(nome.get())); 
    }

    public void pesquisarTodos() throws FarmaciaException{
        lista.clear(); 
        lista.addAll(medicamentoDAO.pesquisarPorNome("")); 
    }

    public void limparTudo(){
        id.set(0l);
        nome.set("");
        categoria.set("");
        fabricante.set("");
        preco.set(0);
    }

    public void entidadeParaTela(Medicamento m){
        if (m != null) {
            id.set(m.getId());
            nome.set(m.getNome()); 
            categoria.set(m.getCategoria()); 
            fabricante.set(m.getFabricante());
            preco.set(m.getPreco());
        }
    }

    public Medicamento telaParaEntidade(){
        Medicamento medicamento = new Medicamento(); 
        medicamento.setId(id.get());
        medicamento.setNome(nome.get());
        medicamento.setCategoria(categoria.get());
        medicamento.setFabricante(fabricante.get()); 
        medicamento.setPreco(preco.get());
        return medicamento; 
    }

    public ObservableList<Medicamento> getLista() { 
        return this.lista;
    }

    public LongProperty idProperty() { 
        return this.id;
    }

    public StringProperty nomeProperty() { 
        return this.nome;
    }

    public StringProperty categoriaProperty() { 
        return this.categoria;
    }

    public StringProperty fabricanteProperty() { 
        return this.fabricante;
    }

    public DoubleProperty precoProperty() { 
        return this.preco;
    }

}
