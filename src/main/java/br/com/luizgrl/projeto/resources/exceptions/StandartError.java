package br.com.luizgrl.projeto.resources.exceptions;

import java.io.Serializable;

public class StandartError implements Serializable{// utilizado para padronizar a forma que o erro vai ser inserido 
    private static final long serialVersionUID = 1L;
    private Integer status;
    private String mensage;
    private long timeStamp;
    
    public StandartError() {
    }

    public StandartError(Integer status, String mensage, long timeStamp) {
        this.status = status;
        this.mensage = mensage;
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMensage() {
        return mensage;
    }

    public void setMensage(String mensage) {
        this.mensage = mensage;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    
    
}
