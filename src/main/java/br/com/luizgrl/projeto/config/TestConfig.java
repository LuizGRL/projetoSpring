package br.com.luizgrl.projeto.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.luizgrl.projeto.service.DBService;

@Configuration // para ser reconhecido como arqruivo de teste 
@Profile("test") // so vai permitir os bean serem usados quando o perfil de teste estiver ativo 
public class TestConfig {
    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException{
        dbService.instantiateTestDatabase();

        return true;

    }


    
}
