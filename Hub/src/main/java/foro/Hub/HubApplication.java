package foro.Hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HubApplication {
	//Link para crear usuarios y poder hacer lo encriptar la contrasea que quieras agregar
	//https://www.browserling.com/tools/bcrypt
	public static void main(String[] args) {
		SpringApplication.run(HubApplication.class, args);
	}
}
