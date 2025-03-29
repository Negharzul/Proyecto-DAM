package com.enlaceFP.enlaceFP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
