package vokra.vokraapp;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vokra.vokraapp.services.DatabaseInitializer;

@SpringBootApplication
public class VokraappApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(VokraappApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(DatabaseInitializer databaseInitializer)
	{
		return app -> databaseInitializer.initializeDatabase();
	}

}
