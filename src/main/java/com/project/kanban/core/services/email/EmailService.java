package com.project.kanban.core.services.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmailBoasVindas(String destinatario, String nome) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setFrom("usoaleatoriod@gmail.com"); 
        mensagem.setTo(destinatario);
        mensagem.setSubject("Cadastro realizado com sucesso!");
        mensagem.setText("Olá " + nome + ", seu cadastro foi realizado com sucesso!");
        
        mailSender.send(mensagem);
    }
}
