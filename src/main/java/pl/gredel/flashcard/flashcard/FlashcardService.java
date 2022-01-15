package pl.gredel.flashcard.flashcard;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.gredel.flashcard.User;
import pl.gredel.flashcard.user.UserService;

import java.util.List;


@AllArgsConstructor
@Component
public class FlashcardService {

    private MongoTemplate mongoTemplate;
    private UserService userService;

    public void addFlashcard(Flashcard flashcard, String login){
        Query query = new Query();
        query.addCriteria(Criteria.where("login").is(login));
        Update update = new Update();
        update.push("flashcards:", flashcard);
        mongoTemplate.updateFirst(query, update, User.class);
    }

    private  Query whereTitleLogin(String login, String title){
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(title).and("login").is(login));

        return query;
    }

    public void editFlashcardFront(String login, String title, Side side){
        whereTitleLogin(login,title);
        Update update = new Update();
        update.set("frontSide:", side);
        mongoTemplate.updateFirst(whereTitleLogin(login,title), update, Flashcard.class);
    }

    public void editFlashcardBack(String login, String title, Side side){
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(title).and("login").is(login));
        Update update = new Update();
        update.set("backSide:", side);
        mongoTemplate.updateFirst(whereTitleLogin(login,title), update, Flashcard.class);
    }
    public void editCategory(String login, String title, String category){
        Update update = new Update();
        update.set("category:", category);
        mongoTemplate.updateFirst(whereTitleLogin(login,title), update, Flashcard.class);
    }

    public void deleteFlashcard(String login, String title){
        mongoTemplate.findAndRemove(whereTitleLogin(login,title),Flashcard.class);
    }

    public void deleteAllByCategory(String login, String category){
        Query query = new Query();
        query.addCriteria(Criteria.where("login").is(login).and("category").is(category));
        mongoTemplate.findAllAndRemove(query,Flashcard.class);
    }

    public List<String> getCategories(String login){
        Query query = new Query();
        query.addCriteria(Criteria.where("login").is(login));
        List<String> categories = mongoTemplate.findDistinct(query, "category", Flashcard.class , String.class);
        return categories;
    }

    public List<Flashcard> getAllFlashcards(){
        List<Flashcard> flashcards = mongoTemplate.findAll(Flashcard.class);
        return flashcards;
    }
    public List<Flashcard> getFlashcardsByCategory(String category, String login){
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(category).and("login").is(login));
        List<Flashcard> flashcards = mongoTemplate.findAll(Flashcard.class);
        return flashcards;
    }
    public List<Flashcard> getFlashcardsByLogin(String login){
        Query query = new Query();
        query.addCriteria(Criteria.where("login").is(login));
        List<Flashcard> flashcards = mongoTemplate.findAll(Flashcard.class);
        return flashcards;
    }



}
