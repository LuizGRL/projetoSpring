package br.com.luizgrl.projeto.resources.exceptions;

import java.io.Serializable;

public class FieldMensage implements Serializable{
    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String mensage;
    public String getFieldName() {
        return fieldName;
    }
    
    
    public FieldMensage() {
    }


    public FieldMensage(String fieldName, String mensage) {
        this.fieldName = fieldName;
        this.mensage = mensage;
    }


    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getMensage() {
        return mensage;
    }
    public void setMensage(String mensage) {
        this.mensage = mensage;
    }
    
    
}
