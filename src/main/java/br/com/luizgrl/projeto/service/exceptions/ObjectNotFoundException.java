package br.com.luizgrl.projeto.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ObjectNotFoundException(String mensage){
        super(mensage);
    }
    public ObjectNotFoundException(String mensage, Throwable cause ){
        super(mensage, cause);
    }
    
}
