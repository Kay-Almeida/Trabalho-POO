
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAOImpl implements MedicamentoDAO {

    private final static String DB_CLASS = "org.mariadb.jdbc.Driver";
    private final static String DB_URL = "jdbc:mariadb://localhost:3307/farmaciadb?allowPublicKeyRetrieval=true"; 
    private final static String DB_USER = "root"; 
    private final static String DB_PASS = "123456";
    
    private Connection con = null; 

    public MedicamentoDAOImpl() throws FarmaciaException{
        try {
            Class.forName(DB_CLASS); 
            con = DriverManager.getConnection(DB_URL,DB_USER, DB_PASS); 
        } catch (ClassNotFoundException | SQLException e) {
            throw new FarmaciaException(e); 
        }
    }

    @Override
    public void inserir(Medicamento m) throws FarmaciaException {
        try {
            String sql = "INSERT INTO medicamento (id, nome, categoria, fabricante, preco) VALUES (?,?,?,?,?)"; 
            PreparedStatement stm = con.prepareStatement(sql); 
            stm.setLong(1, m.getId());
            stm.setString(2, m.getNome());
            stm.setString(3, m.getCategoria());
            stm.setString(4, m.getFabricante());
            stm.setDouble(5, m.getPreco());
            stm.executeUpdate(); 
        } catch (SQLException e) {
            throw new FarmaciaException(e); 
        }
    }

    @Override
    public void atualizar(Medicamento m) throws FarmaciaException {
        try {
            String sql = "UPDATE medicamento SET nome = ?, categoria = ?, fabricante = ?, preco = ? WHERE id = ?"; 
            PreparedStatement stm = con.prepareStatement(sql); 
            stm.setString(1, m.getNome());
            stm.setString(2, m.getCategoria()); 
            stm.setString(3, m.getFabricante());
            stm.setDouble(4, m.getPreco());
            stm.setLong(6, m.getId());
            stm.executeUpdate(); 
        } catch (SQLException e) {
            throw new FarmaciaException(e); 
        }
    }

    @Override
    public void excluir(Medicamento m) throws FarmaciaException {
        try {
            String sql = "DELETE FROM medicamento WHERE id = ?";
            PreparedStatement stm = con.prepareStatement(sql); 
            stm.setLong(1, m.getId());
            stm.executeUpdate();   
        } catch (SQLException e) {
            throw new FarmaciaException(e);
        }
    }

    @Override
    public List<Medicamento> pesquisarPorNome(String nome) throws FarmaciaException {
        List<Medicamento> lista = new ArrayList<>(); 
        try {
            String sql = "SELECT * FROM medicamento WHERE nome LIKE ?";
            PreparedStatement stm = con.prepareStatement(sql); 
            stm.setString(1, "%"+ nome+ "%");
            ResultSet rs = stm.executeQuery(); 
            while (rs.next()) { 
                Medicamento medicamento = new Medicamento(); 
                medicamento.setId(rs.getLong("id"));
                medicamento.setNome(rs.getString("nome"));
                medicamento.setCategoria(rs.getString("categoria")); 
                medicamento.setFabricante(rs.getString("fabricante"));
                medicamento.setPreco(rs.getDouble("preco"));
                lista.add(medicamento);
            } 
        } catch (SQLException e) {
            throw new FarmaciaException(e);
        }
        return lista; 
    }

}
