package pl.gredel.mongoAPI;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.gredel.mongoAPI.flashcard.Flashcard;
import pl.gredel.mongoAPI.user.Address;
import pl.gredel.mongoAPI.user.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Document
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String login;
    private String password;
    private String email;
    private Gender gender;
    private Address address;
    private List<Flashcard> flashcards;
    private LocalDate created;

    public User(String login, String password, String email, Gender gender, Address address, LocalDate created) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.created = created;
    }
}
