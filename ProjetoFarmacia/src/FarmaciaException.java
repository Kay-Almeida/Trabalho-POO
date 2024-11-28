public class FarmaciaException extends Exception {
    
    public FarmaciaException(String message) {
        super(message);
    }

    public FarmaciaException(){
        super(); 
    }

    public FarmaciaException(Throwable t){
        super(t);
    }
}