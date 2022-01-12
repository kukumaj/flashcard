package pl.gredel.mongoAPI.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.gredel.mongoAPI.User;

public interface UserRepository extends MongoRepository<User, String> {
}
