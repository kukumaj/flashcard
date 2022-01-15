package pl.gredel.flashcard;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.gredel.flashcard.flashcard.Flashcard;
import pl.gredel.flashcard.user.AddressUser;
import pl.gredel.flashcard.user.Gender;

import java.time.LocalDate;
import java.util.List;

@Data
@Document
public class User {

    @Id
    private String id;

    private String login;
    private String password;
    private String email;
    private Gender gender;
    private AddressUser address;
    private List<Flashcard> flashcards;
    private LocalDate created;

    public User(String login, String password, String email, Gender gender, AddressUser address) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.created = LocalDate.now();
    }

}
