package ba.edu.ibu.eventbooking.core.repository;


import ba.edu.ibu.eventbooking.core.model.User;
import ba.edu.ibu.eventbooking.core.model.enums.UserType;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Aggregation(pipeline = """
        { $match: { _id: { $exists: true } } }
    """)
    List<User> findAllCustom();


    @Aggregation(pipeline = """
    { $match: { email: ?0 } }
""")
    Optional<User> findByEmailCustom(String email);


    Optional<User> findFirstByEmailLike(String emailPattern);

    List<User> findByEmailAndUserTypeOrderByCreationDateDesc(String email, UserType userType);

    @Query(value="{$or:[{email:'?0'}, {username:'?0'}]}")
    Optional<User> findByUsernameOrEmail(String username);

    @Query("{ 'userType' : { $in: ['GUEST', 'REGISTERED_USER', 'EVENT_ORGANIZER', 'VENUE_MANAGERS'] } }")
    List<User> findUsersByRoles();

    Optional<User> findByEmail(String email);


}