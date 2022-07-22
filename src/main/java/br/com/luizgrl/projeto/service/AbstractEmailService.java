package br.com.luizgrl.projeto.service;

import br.com.luizgrl.projeto.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService{
    @Value("${default.sender}")
    private  String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;
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

    protected  String htmlFromTemplatePedido(Pedido obj){
        Context context = new Context();
        context.setVariable("pedido",obj);
        return templateEngine.process("email/confirmacaoPedido",context);

    }
    @Override
    public void sendOrderConfirmationHtmlEmail(Pedido obj){
        try {
            MimeMessage mm = prepareMimeMainMessageFromPedido(obj);
            sendHtmlEmail(mm);
        }catch (MessagingException e){
            sendOrderConfirmationEmail(obj);

        }
    }


    private MimeMessage prepareMimeMainMessageFromPedido(Pedido obj) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(obj.getCliente().getEmail());
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject("Pedido Confirmado Código: "+obj.getId());
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTemplatePedido(obj), true);
        return  mimeMessage;

    }
}
