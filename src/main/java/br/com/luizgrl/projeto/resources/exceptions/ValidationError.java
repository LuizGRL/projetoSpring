package br.com.luizgrl.projeto.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {

    private static final long serialVersionUID = 1L;

    private List<FieldMensage> list = new ArrayList<>();

    public List<FieldMensage> getErrors() {
        return list;
    }

    public void setList(List<FieldMensage> list) {
        this.list = list;
    }

    public ValidationError() {
    }

    public ValidationError(Integer status, String mensage, long timeStamp) {
        super(status, mensage, timeStamp);
    }

    public void addError(String fieldName, String mensage){
        list.add(new FieldMensage(fieldName,mensage));
    }
    
}
