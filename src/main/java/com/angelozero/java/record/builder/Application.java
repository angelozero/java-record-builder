package com.angelozero.java.record.builder;

//import org.springframework.boot.SpringApplication;
import com.angelozero.java.record.builder.service.PersonService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//SpringApplication.run(Application.class, args);

        PersonService.foo();
        PersonService.bar();
	}
}
