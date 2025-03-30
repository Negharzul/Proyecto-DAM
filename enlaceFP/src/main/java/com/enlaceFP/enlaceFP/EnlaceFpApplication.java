package com.enlaceFP.enlaceFP;

import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Models.Profesor;
import com.enlaceFP.enlaceFP.Repositories.AlumnoRepository;
import com.enlaceFP.enlaceFP.Repositories.ProfesorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EnlaceFpApplication {

	public static void main(String[] args) {
		//SpringApplication.run(EnlaceFpApplication.class, args);


		ApplicationContext context=SpringApplication.run(EnlaceFpApplication.class, args);

		var repoProfesor= context.getBean(ProfesorRepository.class);
		AlumnoRepository repoAlumno= context.getBean(AlumnoRepository.class);
		Profesor profesor = new Profesor();
		profesor.setNombre("Alfredo");
		profesor.setApellidos("Alcantara");

		Alumno alumno = new Alumno();
		alumno.setNombre("Valverde");


		repoAlumno.save(alumno);
		repoProfesor.save(profesor);




	}

}
