package br.com.luizgrl.projeto.service.exceptions;

public class FileException extends RuntimeException{
    private static final long serialVersionUID=1L;

    public FileException(String mensage){
        super(mensage);
    }


    public FileException(String mensage, Throwable cause){
        super(mensage, cause);
    }
    
}
