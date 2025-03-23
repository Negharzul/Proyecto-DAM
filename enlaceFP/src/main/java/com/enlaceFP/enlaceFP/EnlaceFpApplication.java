package com.enlaceFP.enlaceFP;

import com.enlaceFP.enlaceFP.Models.Grupo;
import com.enlaceFP.enlaceFP.Models.Profesor;
import com.enlaceFP.enlaceFP.Repositories.ProfesorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EnlaceFpApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnlaceFpApplication.class, args);

		/*
		ApplicationContext context=SpringApplication.run(EnlaceFpApplication.class, args);

		var repo= context.getBean(ProfesorRepository.class);

		Profesor profesor = new Profesor();
		profesor.setGrupo(new Grupo());
		profesor.setNombre("Alfredo");
		profesor.setApellidos("Alcantara");

		repo.save(profesor);


		 */

	}

}
