package pl.gredel.flashcard.user;


import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.gredel.flashcard.User;
import org.springframework.data.mongodb.*;
import java.util.Optional;

@Component
public class UserService {

    private MongoTemplate mongoTemplate;

    public UserService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Optional<User> login(String login, String password){

       Query query = new Query();
       query.addCriteria(Criteria.where("login").is(login));
       Optional<User> user = Optional.ofNullable(mongoTemplate.findOne(query, User.class));


      if(user.isPresent()) {
          if (!(user.get().getLogin().equals(login) && user.get().getPassword().equals(password)) ) user = Optional.empty();
      }
      return user;
    }

    public boolean register(User user){
        Query query = new Query();
        query.addCriteria(Criteria.where("login").is(user.getLogin()));
        boolean exists = mongoTemplate.exists(query, User.class);

        if(!exists){
            mongoTemplate.save(user);
            return true;
        }
        else
            return false;
    }

    public void resetPassword(User user , String password){
        Query query = new Query();
        query.addCriteria(Criteria.where("login").is(user.getLogin()));
        Update update1 = new Update();
        update1.set("password", password);
        mongoTemplate.updateFirst(query, update1, User.class);
    }
}
