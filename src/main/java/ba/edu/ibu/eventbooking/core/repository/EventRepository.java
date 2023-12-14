package ba.edu.ibu.eventbooking.core.repository;

import ba.edu.ibu.eventbooking.core.model.Event;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends MongoRepository<Event, Integer> {
    Event findByEventId(int eventId);

    @Aggregation(pipeline = """
        { $match: { _id: { $exists: true } } }
    """)
    List<Event> findAllCustom();
    @Query(value = "{ 'eventName' : ?0 }", fields = "{ 'id': 1, 'eventName': 1, 'eventDate': 1, 'eventType': 1 }")
    Optional<Event> findByEventNameCustom(String eventName);

    @Query(value = "{ 'eventType' : ?0 }")
    List<Event> findByEventTypeCustom(String eventType);

    @Query(value = "{ 'eventDate' : { $gte : ?0, $lte : ?1 } }")
    List<Event> findByEventDateRangeCustom(String startDate, String endDate);

    @Query("{ 'eventDate' : { $gte : new Date() } }")
    List<Event> findUpcomingEvents();

    @Query("{ 'organizer.userId' : ?0 }")
    List<Event> findEventsByOrganizerUserId(String organizerUserId);

}
