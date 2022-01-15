package pl.gredel.flashcard.flashcard;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Side {
    @Id
    private String idSide;
    private String sideTitle;
    private String description;

    public Side(String sideTitle, String description) {
        this.sideTitle = sideTitle;
        this.description = description;
    }
}
