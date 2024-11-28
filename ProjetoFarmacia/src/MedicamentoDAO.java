import java.util.List; 

public interface MedicamentoDAO {
    void inserir(Medicamento m) throws FarmaciaException; 
    void atualizar(Medicamento m) throws FarmaciaException; 
    void excluir(Medicamento m) throws FarmaciaException; 
    List<Medicamento> pesquisarPorNome(String nome) throws FarmaciaException; 
}
