package pl.gredel.flashcard.flashcard;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Flashcard {
    @Id
    private String idCard;
    private String title;
    private String category;
    private Side frontSide;
    private Side backSide;

    public Flashcard(String title, String category, Side frontSide, Side backSide) {
        this.title = title;
        this.category = category;
        this.frontSide = frontSide;
        this.backSide = backSide;
    }
}
