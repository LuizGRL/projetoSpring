package br.com.luizgrl.projeto.resources;

import br.com.luizgrl.projeto.dto.EmailDTO;
import br.com.luizgrl.projeto.security.JWTUtil;
import br.com.luizgrl.projeto.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
    @Autowired
    private AuthService authService;
    @Autowired
    private JWTUtil jwtUtil;
    @RequestMapping(value = "/forgot",method = RequestMethod.POST)
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDTO){
        authService.sendNewPassword(objDTO.getEmail());
        return  ResponseEntity.noContent().build();

    }
}
