package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Alumno;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
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

    public void correoRegistroMultiple(List<Alumno> alumnos){
        SimpleMailMessage mensaje = new SimpleMailMessage();
        for(Alumno alumno:alumnos){
            mensaje.setSubject("Registro fp-enlance");
            mensaje.setText(generarTextoRegistro(alumno));
            mensaje.setFrom("enlacefp76@gmail.com");
            mensaje.setTo(alumno.getCorreoElectronico());
            mailSender.send(mensaje);
        }
    }

    public String generarTextoRegistro(Alumno alumno){

        StringBuilder mensaje= new StringBuilder("Ha sido invitado a usar la bolsa de trabajo privada de fp-enlace.\n");
        mensaje.append("Sus credenciales son:\n");
        mensaje.append("Usuario: "+alumno.getCorreoElectronico());
        mensaje.append(" Contraseña: "+ alumno.getDni()+".\n");
        mensaje.append("Por favor cambie la contraseña a la mayor brevedad posible.\n");
        mensaje.append("Bienvenido.");

        return mensaje.toString();

    }
}
