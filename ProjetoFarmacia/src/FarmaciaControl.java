import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList; 


public class FarmaciaControl {
    private ObservableList<Farmacia> lista = FXCollections.observableArrayList(); 
    private long contador = 0; 

    private LongProperty id = new SimpleLongProperty(0); 
    private StringProperty nome = new SimpleStringProperty(""); 
    private StringProperty cidade = new SimpleStringProperty(""); 
    private StringProperty bairro = new SimpleStringProperty(""); 
    private StringProperty telefone = new SimpleStringProperty(""); 
    private StringProperty proprietario = new SimpleStringProperty(""); 

    private FarmaciaDAO farmaciaDAO = null; 

    public FarmaciaControl () throws FarmaciaException{
        farmaciaDAO = new FarmaciaDAOImpl(); 
    }

    public Farmacia paraEntidade(){
        Farmacia f = new Farmacia(); 
        f.setId(id.get());
        f.setNome(nome.get());
        f.setCidade(cidade.get());
        f.setBairro(bairro.get());
        f.setTelefone(telefone.get());
        f.setProprietario(proprietario.get());
        return f; 
    }

    public void excluir(Farmacia f) throws FarmaciaException{
        farmaciaDAO.excluir(f); 
        pesquisarTodos(); 
    }

    public void limparTudo(){
        id.set(0);
        nome.set("");
        cidade.set("");
        bairro.set("");
        telefone.set("");
        proprietario.set("");
    }

    public void paraTela(Farmacia f){
        if (f != null) {
            id.set(f. getId()); 
            nome.set(f.getNome());
            cidade.set(f.getCidade());
            bairro.set(f.getBairro()); 
            telefone.set(f.getTelefone()); 
            proprietario.set(f.getProprietario());
        }
    }

    public void gravar() throws FarmaciaException{
        Farmacia f = paraEntidade(); 
        if (f.getId() == 0) {
            this.contador +=1; 
            f.setId(this.contador);
            farmaciaDAO.inserir(f);   
        }else{
            farmaciaDAO.atualizar(f); 
        }
        limparTudo();
        pesquisarTodos(); 
    }

    public void pesquisar() throws FarmaciaException{
        lista.clear(); 
        lista.addAll(farmaciaDAO.pesquisarPorNome(nome.get())); 
    }

    public void pesquisarTodos() throws FarmaciaException{
        lista.clear(); 
        lista.addAll(farmaciaDAO.pesquisarPorNome("")); 
    }

    public LongProperty idProperty(){
        return this.id; 
    }

    public StringProperty nomeProperty(){
        return this.nome; 
    }

    public StringProperty cidadeProperty(){
        return this.cidade; 
    }

    public StringProperty bairroProperty(){
        return this.bairro; 
    }

    public StringProperty telefoneProperty(){
        return this.telefone; 
    }

    public StringProperty proprietarioProperty(){
        return this.proprietario; 
    }

    public ObservableList<Farmacia> getLista(){
        return this.lista; 
    }

    
}
