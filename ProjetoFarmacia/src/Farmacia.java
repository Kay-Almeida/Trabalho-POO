public class Farmacia {
        private long id;
        private String nome; 
        private String cidade;
        private String bairro; 
        private String telefone; 
        private String proprietario; 

        public Farmacia(){
        }

        public Farmacia(long id, String nome, String cidade, String bairro, String telefone, String proprietario ){
            this.id = id; 
            this.nome = nome; 
            this.cidade = cidade; 
            this.bairro = bairro; 
            this.telefone = telefone; 
            this.proprietario = proprietario; 
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
    
        public String getCidade(){
            return cidade; 
        }
    
        public void setCidade(String cidade){
            this.cidade = cidade; 
        }
    
        public String getBairro(){
            return bairro; 
        }
    
        public void setBairro(String bairro){
            this.bairro = bairro; 
        }
    
        public String getTelefone(){
            return telefone; 
        }
    
        public void setTelefone(String telefone){
            this.telefone = telefone; 
        }
    
        public String getProprietario(){
            return proprietario; 
        }
    
        public void setProprietario(String proprietario){
            this.proprietario = proprietario; 
        }
    }

