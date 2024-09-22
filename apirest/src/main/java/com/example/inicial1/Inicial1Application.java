package com.example.inicial1;

import com.example.inicial1.entities.*;
import com.example.inicial1.repositories.PersonaRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Inicial1Application {
	private static final Logger logger = LoggerFactory.getLogger(Inicial1Application.class);

	@Autowired
	private PersonaRepository personaRepository;
	public static void main(String[] args) {
		SpringApplication.run(Inicial1Application.class, args);

		System.out.println("funcionando");
	}

	@Bean
	@Transactional
	CommandLineRunner init(PersonaRepository personaRepository) {
		return args -> {

			// Creo una persona
			Persona per = Persona.builder().
					nombre("John").apellido("Salchichon").
					build();

			//Creo un domicilio
			Domicilio dom = Domicilio.builder().
					calle("Ruta").
					numero(66).build();

			per.setDomicilio(dom);

			//Creo una localidad
			List<Localidad> localidades = Arrays.asList(
					Localidad.builder().denominacion("Guaymallén").build(),
					Localidad.builder().denominacion("Godoy Cruz").build());

			dom.setLocalidades(localidades);

			//Creo un libro
			List<Libro> libros = Arrays.asList(
					Libro.builder().
					titulo("It").
							autor("Stephen King").
							genero("Terror").
							paginas(1504).
							fecha(1986).build());

			per.setLibros(libros);

			//Creo un autor
			List<Autor> autores = Arrays.asList(
					Autor.builder()
							.nombre("Stephen")
							.apellido("King")
							.biografia("Autor estadounidense, conocido por obras de terror y suspenso.")
							.build()
			);

			libros.get(0).getAutores().add(autores.get(0));

			personaRepository.save(per);

		};

	};




}
