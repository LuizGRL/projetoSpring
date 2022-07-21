package br.com.luizgrl.projeto.service;

import br.com.luizgrl.projeto.domain.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService{
    @Value("${default.sender}")
    private  String sender;
    @Override
    public void sendOrderConfirmationEmail(Pedido obj){
        SimpleMailMessage simpleMailMessage = prepareSimpleMainMessageFromPedido(obj);
        sendEmail(simpleMailMessage);
    }
    protected SimpleMailMessage prepareSimpleMainMessageFromPedido(Pedido obj){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(obj.getCliente().getEmail()); // destinatrio do email
        simpleMailMessage.setFrom(sender); // remetende do email
        simpleMailMessage.setSubject("Pedido Confirmado. Id: "+ obj.getId());
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText(obj.toString());
        return simpleMailMessage;
    }
}
