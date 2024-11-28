import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FarmaciaDAOImpl implements FarmaciaDAO {

    private final static String DB_CLASS = "org.mariadb.jdbc.Driver"; 
    private final static String DB_URL = "jdbc:mariadb://localhost:3307/farmaciadb?allowPublicKeyRetrieval=true";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "123456";

    private Connection con = null;

    public FarmaciaDAOImpl() throws FarmaciaException{
        try {
            Class.forName(DB_CLASS); 
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new FarmaciaException(e); 
        }
    }

    @Override
    public void inserir(Farmacia f) throws FarmaciaException {
        try {
            String SQL = "INSERT INTO farmacia (id, nome, cidade, bairro, telefone, proprietario) VALUES (?,?,?,?,?,?)"; 
            PreparedStatement stm = con.prepareStatement(SQL); 
            stm.setLong(1, f.getId());
            stm.setString(2, f.getNome() );
            stm.setString(3, f.getCidade());
            stm.setString(4, f.getBairro());
            stm.setString(5, f.getTelefone());
            stm.setString(6, f.getProprietario());
            stm.executeUpdate(); 
        } catch (SQLException e) {
            throw new FarmaciaException(e); 
        }
    }

    @Override
    public void atualizar(Farmacia f) throws FarmaciaException {
        try{
            String SQL = "UPDATE farmacia SET nome = ?, cidade = ?, bairro = ?, telefone = ?, proprietario = ? WHERE id = ?"; 
            PreparedStatement stm = con.prepareStatement(SQL); 
            stm.setString(1, f.getNome()); 
            stm.setString(2, f.getCidade());
            stm.setString(3, f.getBairro());
            stm.setString(4, f.getTelefone());  
            stm.setString(5, f.getProprietario()); 
            stm.setLong(6, f.getId());
            stm.executeUpdate(); 
        }catch(SQLException e){
            throw new FarmaciaException(e); 
        }
    }

    @Override
    public void excluir(Farmacia f) throws FarmaciaException {
        try {
            String SQL = "DELETE FROM farmacia WHERE id = ?"; 
            PreparedStatement stm = con.prepareStatement(SQL); 
            stm.setLong(1, f.getId()); 
            stm.executeUpdate(); 
        } catch (SQLException e) {
            throw new FarmaciaException(e); 
        }
    }

    @Override
    public List<Farmacia> pesquisarPorNome(String nome) throws FarmaciaException {
        List<Farmacia> lista = new ArrayList<>(); 
        try {
            String SQL = "SELECT * FROM farmacia WHERE nome LIKE ?"; 
            PreparedStatement stm = con.prepareStatement(SQL); 
            stm.setString(1, "%" + nome + "%");
            ResultSet rs = stm.executeQuery(); 
            while (rs.next()) { 
                Farmacia f = new Farmacia();  
                f.setId(rs.getLong("id")); 
                f.setNome(rs.getString("nome"));
                f.setCidade(rs.getString("cidade"));
                f.setBairro(rs.getString("bairro"));
                f.setTelefone(rs.getString("telefone"));
                f.setProprietario(rs.getString("proprietario"));
                lista.add(f); 
            }
        } catch (SQLException e) {
            throw new FarmaciaException(e);
        }
        return lista; 
    }

}
