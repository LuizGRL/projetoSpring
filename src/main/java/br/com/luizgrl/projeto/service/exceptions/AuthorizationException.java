package br.com.luizgrl.projeto.service.exceptions;

public class AuthorizationException  extends  RuntimeException{
    private static final long serialVersionUID = 1L;


    public AuthorizationException(String mensage){
        super(mensage);
    }

    public AuthorizationException(String msg, Throwable cause) {
        super(msg, cause);
    }

}

