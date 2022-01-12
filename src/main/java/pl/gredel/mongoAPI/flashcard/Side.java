package pl.gredel.mongoAPI.flashcard;

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
}
