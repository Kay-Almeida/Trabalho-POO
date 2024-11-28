import java.util.List;

public interface FarmaciaDAO {
    void inserir(Farmacia f) throws FarmaciaException; 
    void atualizar(Farmacia f) throws FarmaciaException;
    void excluir(Farmacia f) throws FarmaciaException;
    List<Farmacia> pesquisarPorNome (String nome) throws FarmaciaException; 
}
