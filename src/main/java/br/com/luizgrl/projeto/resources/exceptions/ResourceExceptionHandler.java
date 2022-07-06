package br.com.luizgrl.projeto.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import br.com.luizgrl.projeto.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
    public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException exception, HttpServletRequest request ){ // padrao do contrle de avisos recebe a exeção (ObjectNotFound), e as informaç~es da requisição
        StandartError error = new StandartError(HttpStatus.NOT_FOUND.value(),exception.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);// vai passar o erro de não encotrado passando a mensagem no corpo do retorno 
    }
    
}
