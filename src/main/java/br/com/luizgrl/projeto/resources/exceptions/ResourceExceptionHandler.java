package br.com.luizgrl.projeto.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.luizgrl.projeto.service.exceptions.DataIntegrityException;
import br.com.luizgrl.projeto.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
    public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException exception, HttpServletRequest request ){ // padrao do contrle de avisos recebe a exeção (ObjectNotFound), e as informaç~es da requisição
        StandartError error = new StandartError(HttpStatus.NOT_FOUND.value(),exception.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);// vai passar o erro de não encotrado passando a mensagem no corpo do retorno 
    }

    public ResponseEntity<StandartError> dataIntegrityException(DataIntegrityException exception,HttpServletRequest request){
        StandartError error = new StandartError(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class) // 
    public ResponseEntity<StandartError> validation(MethodArgumentNotValidException exception, HttpServletRequest request){
        ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(),"Erro de validação ", System.currentTimeMillis());
        for(FieldError x : exception.getBindingResult().getFieldErrors()){
            error.addError(x.getField(),x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }
    
}
