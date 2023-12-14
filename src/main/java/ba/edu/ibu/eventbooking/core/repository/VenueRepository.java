package ba.edu.ibu.eventbooking.core.repository;

import ba.edu.ibu.eventbooking.core.model.Venue;
import ba.edu.ibu.eventbooking.core.model.enums.VenueType;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface VenueRepository extends MongoRepository<Venue, Integer> {
    @Aggregation(pipeline = """
        { $match: { _id: { $exists: true } } }
    """)
    @Query("{ 'name' : ?0 }")
    List<Venue> findByName(String name);

    @Query("{ 'capacity' : { $gte: ?0 } }")
    List<Venue> findByMinimumCapacity(int capacity);

    @Query(value = "{ 'type' : ?0 }")
    List<Venue> findByType(String type);

    @Query(value = "{ 'location.city' : ?0, 'location.country' : ?1 }")
    List<Venue> findByCityAndCountry(String city, String country);

    @Query("{ $or: [ { 'name': { $regex: ?0, $options: 'i' } }, { 'location': { $regex: ?2, $options: 'i' } } ], 'venueType': ?1, 'location.country': { $regex: ?3, $options: 'i' } }")
    List<Venue> findByCriteria(String name, VenueType type, String city, String country);
}

