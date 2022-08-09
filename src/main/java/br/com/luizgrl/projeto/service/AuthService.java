package br.com.luizgrl.projeto.service;

import br.com.luizgrl.projeto.domain.Cliente;
import br.com.luizgrl.projeto.repositories.ClienteRepository;
import br.com.luizgrl.projeto.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    public void sendNewPassword(String email){
        Cliente cliente =  clienteRepository.findByEmail(email);
        if(cliente==null){
            throw new ObjectNotFoundException("Não encontrado");
        }
        String newPassword =  sendNewPassword();
        cliente.setPassword(bCryptPasswordEncoder.encode(newPassword));
        clienteRepository.save(cliente);
        emailService.sendNewPasswordEmail(cliente,newPassword);

    }

    private String sendNewPassword() {
        char[] vet = new char[16];
        for(int i=0;i<16;i++){
            vet[i] = randomChar();
        }
        return  new String(vet);
    }

    private char randomChar() {
        Random rand = new Random();
        int opt = rand.nextInt(4);
        System.out.println(opt);
        if (opt == 0) {
            return (char) (rand.nextInt(10) + 48);
        } else if (opt == 1) {
            return (char) (rand.nextInt(26) + 65);
        } else if (opt == 2) {
        return (char) (rand.nextInt(26) + 97);
        }else{
            return (char) (rand.nextInt(15) + 33);
        }
    }
    }
