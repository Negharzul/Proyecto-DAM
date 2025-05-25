package com.enlaceFP.enlaceFP.Services;

import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Models.Empleo;
import com.enlaceFP.enlaceFP.Models.Usuario;
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

    public void correoRecomendacionEmpleo(List<Alumno> Alumnos,String nombreEmpleo){
        SimpleMailMessage mensaje = new SimpleMailMessage();
        for(Alumno alumno:Alumnos){
            mensaje.setSubject(nombreEmpleo);
            mensaje.setText("Nueva oferta disponible de " +nombreEmpleo+" visite la pagina web para mas información.");
            mensaje.setFrom("enlacefp76@gmail.com");
            mensaje.setTo(alumno.getCorreoElectronico());
            mailSender.send(mensaje);
        }
    }

    public void correoRegistro(Usuario usuario){
        SimpleMailMessage mensaje = new SimpleMailMessage();

        mensaje.setSubject("Registro fp-enlance");
        mensaje.setText(generarTextoRegistro(usuario));
        mensaje.setFrom("enlacefp76@gmail.com");
        mensaje.setTo(usuario.getCorreoElectronico());
        mailSender.send(mensaje);
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

    public void enviarCandidatos(List<Alumno> alumnos, Empleo empleo){

        SimpleMailMessage mensaje = new SimpleMailMessage();

        mensaje.setSubject("Candidatos para "+empleo.getNombreEmpleo());
        mensaje.setText(generarTextoEnviarCandidatos(alumnos,empleo));
        mensaje.setFrom("enlacefp76@gmail.com");
        mensaje.setTo(empleo.getEmpresa().getCorreoElectronico());
        mailSender.send(mensaje);
    }

    public String generarTextoRegistro(Usuario usuario){

        StringBuilder mensaje= new StringBuilder("Ha sido invitado a usar la bolsa de trabajo privada de fp-enlace.\n");
        mensaje.append("Sus credenciales son:\n");
        mensaje.append("Usuario: "+usuario.getCorreoElectronico());
        mensaje.append(" Contraseña: "+ usuario.getDni()+".\n");
        mensaje.append("Por favor cambie la contraseña a la mayor brevedad posible.\n");
        mensaje.append("Bienvenido.");

        return mensaje.toString();
    }



    public String generarTextoEnviarCandidatos(List<Alumno> alumnos,Empleo empleo){

        StringBuilder mensaje= new StringBuilder("Les escribimos para Informacles" +
                " que cinco de nuestros alumnos están interesados en postularse \n" +
                "al puesto de " + empleo.getNombreEmpleo() + " que ofrecen en "
                + empleo.getEmpresa().getNombre()+". Quedamos atentos a los siguientes pasos del proceso.\n");

        mensaje.append("Candidatos interesados:\n");
        for(Alumno alumno: alumnos){
            mensaje.append(generarTextoDatosAlumno(alumno));
        }

        return mensaje.toString();
    }

    public String generarTextoDatosAlumno(Alumno alumno){
        StringBuilder mensaje= new StringBuilder(alumno.getNombre()+alumno.getApellidos()+"\n");
        mensaje.append("Estudios: " + alumno.getEstudios()+"\n");
        mensaje.append("Correo Electronico: " + alumno.getCorreoElectronico()+"\n");
        mensaje.append("Telefono contacto: " + alumno.getTelefono()+"\n");

        return mensaje.toString();

    }
}
