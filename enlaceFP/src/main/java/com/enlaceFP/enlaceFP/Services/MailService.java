package com.enlaceFP.enlaceFP.Services;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MailService {
    private final JavaMailSender mailSender;

    public void enviarCorreo(String destinatario,String tema,String contenido){

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject(tema);
        mensaje.setText(contenido);
        mensaje.setFrom("enlacefp76@gmail.com");
        mailSender.send(mensaje);
    }

    public void enviarMultiplesCorreosDuplicados(List<String> destinatarios, String tema, String contenido){

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setSubject(tema);
        mensaje.setText(contenido);
        mensaje.setFrom("enlacefp76@gmail.com");

        for(String destinatario:destinatarios){
            mensaje.setTo(destinatario);
            mailSender.send(mensaje);
        }
    }
}
