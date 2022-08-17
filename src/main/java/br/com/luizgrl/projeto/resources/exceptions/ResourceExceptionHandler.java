package br.com.luizgrl.projeto.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import br.com.luizgrl.projeto.service.exceptions.AuthorizationException;
import br.com.luizgrl.projeto.service.exceptions.FileException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
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
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException exception, HttpServletRequest request ){ // padrao do contrle de avisos recebe a exeção (ObjectNotFound), e as informaç~es da requisição
        StandartError error = new StandartError(HttpStatus.NOT_FOUND.value(),exception.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);// vai passar o erro de não encotrado passando a mensagem no corpo do retorno 
    }

    @ExceptionHandler(DataIntegrityException.class)
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

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandartError> authorization(AuthorizationException exception, HttpServletRequest request ){ // padrao do contrle de avisos recebe a exeção (ObjectNotFound), e as informaç~es da requisição
        StandartError error = new StandartError(HttpStatus.FORBIDDEN.value(),exception.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(FileException.class)
    public ResponseEntity<StandartError> file(FileException exception, HttpServletRequest request ){ // padrao do contrle de avisos recebe a exeção (ObjectNotFound), e as informaç~es da requisição
        StandartError error = new StandartError(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<StandartError> amazonService(AmazonServiceException exception, HttpServletRequest request ){ // padrao do contrle de avisos recebe a exeção (ObjectNotFound), e as informaç~es da requisição
        HttpStatus status = HttpStatus.valueOf(exception.getErrorCode());
        StandartError error = new StandartError(status.value(),exception.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<StandartError> amazonClient(AmazonClientException exception, HttpServletRequest request ){ // padrao do contrle de avisos recebe a exeção (ObjectNotFound), e as informaç~es da requisição
        StandartError error = new StandartError(HttpStatus.FORBIDDEN.value(),exception.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);

    }

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<StandartError> amazonS3(AmazonS3Exception exception, HttpServletRequest request ){ // padrao do contrle de avisos recebe a exeção (ObjectNotFound), e as informaç~es da requisição
        StandartError error = new StandartError(HttpStatus.FORBIDDEN.value(),exception.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
    
}
