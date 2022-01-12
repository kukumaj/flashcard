package pl.gredel.mongoAPI.flashcard;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Flashcard {
    @Id
    private String idCard;
    private String cardTitle;
    private String title;
    private String group;
    private Side frontSide;
    private Side backSide;

    public Flashcard(String cardTitle, String title, String group, Side frontSide, Side backSide) {
        this.cardTitle = cardTitle;
        this.title = title;
        this.group = group;
        this.frontSide = frontSide;
        this.backSide = backSide;
    }
}
