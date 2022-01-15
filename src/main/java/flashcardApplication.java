package pl.gredel.flashcard;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.gredel.flashcard.ui.MenuApp;


import java.awt.*;

@SpringBootApplication
public class flashcardApplication {

    public static void main(String[] args) {
        SpringApplication.run(flashcardApplication.class, args);


    }

    @Bean
    CommandLineRunner init(MenuApp menuApp){
        return args -> {
            menuApp.menuStart();

        };
    }
}

