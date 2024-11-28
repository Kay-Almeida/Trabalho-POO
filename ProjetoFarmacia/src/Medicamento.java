public class Medicamento {
    private long id;
    private String nome; 
    private String categoria; 
    private String fabricante; 
    private double preco;

    public Medicamento(){

    }
    
    public Medicamento(long id, String nome, String categoria, String fabricante, double preco){
        this.id = id; 
        this.nome = nome; 
        this.categoria = categoria; 
        this.fabricante = fabricante; 
        this.preco = preco; 
    }
    public long getId(){
        return id; 
    }

    public void setId(long id){
        this.id = id; 
    }

    public String getNome(){
        return nome; 
    }

    public void setNome(String nome){
        this.nome = nome; 
    }

    public String getCategoria(){
        return categoria; 
    }

    public void setCategoria(String categoria){
        this.categoria = categoria; 
    }

    public String getFabricante(){
        return fabricante; 
    }

    public void setFabricante(String fabricante){
        this.fabricante = fabricante; 
    }

    public double getPreco(){
        return preco; 
    }

    public void setPreco(double preco){
        this.preco = preco; 
    }
}
