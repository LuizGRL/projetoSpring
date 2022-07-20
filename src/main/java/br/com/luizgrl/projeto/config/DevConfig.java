package br.com.luizgrl.projeto.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.luizgrl.projeto.service.DBService;

@Configuration // para ser reconhecido como arqruivo de teste 
@Profile("dev") // so vai permitir os bean serem usados quando o perfil de teste estiver ativo 
public class DevConfig {
    @Autowired
    private DBService dbService;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException{

        if(!"create".equals(strategy)){
            return  false;
        }
        dbService.instantiateTestDatabase();

        return true;

    }


    
}
