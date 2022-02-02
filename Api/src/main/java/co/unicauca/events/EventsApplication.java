package co.unicauca.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.unicauca.events.service.Firebase;;

@SpringBootApplication
public class EventsApplication {

	public static void main(String[] args) {
		Firebase.initializeFirebase();
		SpringApplication.run(EventsApplication.class, args);
	}
}
