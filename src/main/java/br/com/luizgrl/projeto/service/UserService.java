package br.com.luizgrl.projeto.service;

import br.com.luizgrl.projeto.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;



public class UserService {


    public static  UserSS authenticated(){
        try{
            return  (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // vai retornar o usuario cadastrado no sistema

        }catch (Exception e){
            return null;

        }

    }
}
