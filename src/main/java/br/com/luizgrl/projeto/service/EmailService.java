package br.com.luizgrl.projeto.service;

import br.com.luizgrl.projeto.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;


public interface EmailService {
    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

}
