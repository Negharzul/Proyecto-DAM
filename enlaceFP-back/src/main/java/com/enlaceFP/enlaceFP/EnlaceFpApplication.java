package com.enlaceFP.enlaceFP;

import com.enlaceFP.enlaceFP.Models.Alumno;
import com.enlaceFP.enlaceFP.Models.Profesor;
import com.enlaceFP.enlaceFP.Models.Role;
import com.enlaceFP.enlaceFP.Repositories.RoleRepository;
import com.enlaceFP.enlaceFP.Services.AlumnoService;
import com.enlaceFP.enlaceFP.Services.ProfesorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EnlaceFpApplication {

	public static void main(String[] args) {
		//SpringApplication.run(EnlaceFpApplication.class, args);

		ApplicationContext context=SpringApplication.run(EnlaceFpApplication.class, args);


		var roleRepository= context.getBean(RoleRepository.class);
		var profesorService= context.getBean(ProfesorService.class);
		var alumnoService= context.getBean(AlumnoService.class);


		Role roleAlumno= Role.builder().role("ROLE_Alumno").build();
		Role roleProfesor= Role.builder().role("ROLE_Profesor").build();
		Role roleAdmin= Role.builder().role("ROLE_Admin").build();

		roleRepository.save(roleAlumno);
		roleRepository.save(roleProfesor);
		roleRepository.save(roleAdmin);

		Profesor profesor = Profesor.builder()
				.nombre("Alfredo")
				.apellidos("Alcantara")
				.correoElectronico("alfredo@gmail.com")
				.password("12345")
				.build();

		Alumno alumno = Alumno.builder()
				.nombre("Valverde")
				.correoElectronico("valverde@gmail.com")
				.password("12345")
		        .build();


		alumnoService.crearAlumno(alumno);
		profesorService.crearProfesor(profesor);




	}

}
