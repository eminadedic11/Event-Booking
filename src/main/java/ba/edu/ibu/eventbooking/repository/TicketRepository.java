package ba.edu.ibu.eventbooking.repository;

import ba.edu.ibu.eventbooking.model.Ticket;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, Integer> {
    @Aggregation(pipeline = """
        { $match: { _id: { $exists: true } } }
    """)
    @Query("{ 'type' : ?0 }")
    List<Ticket> findByType(String type);
    @Query("{ 'price' : { $lte: ?0 } }")
    List<Ticket> findByPriceLessThanOrEqualTo(double price);
    @Query("{ 'event.eventId' : ?0 }")
    List<Ticket> findByEventId(int eventId);

    @Query("{ 'status' : ?0 }")
    List<Ticket> findByStatus(String status);
    @Query("{ 'event.eventId' : ?0, 'status' : ?1 }")
    List<Ticket> findByEventIdAndStatus(int eventId, String status);


}

