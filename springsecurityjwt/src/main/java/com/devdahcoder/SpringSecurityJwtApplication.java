package com.devdahcoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityJwtApplication {
	public static void main(String[] args) {
		/**
		 * So this might be a bit weired but, I am using jdbc and repository based pattern instead
		 * of the normal jdbc dao pattern.
		 * Usually you have a repository file and inside is the Crud Repository interface that accesses the database!
		 * and in some project you have dao file which contains Jdbc database access.
		 * but this time i/'ve used the repository file for my jdbc database access.
		 * */

		SpringApplication.run(SpringSecurityJwtApplication.class, args);

	}

}
