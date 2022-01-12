package pl.gredel.mongoAPI.flashcard;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface FlashcardRepository extends MongoRepository<Flashcard, String> {
}
