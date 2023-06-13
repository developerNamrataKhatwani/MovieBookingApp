package Repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.cognizant.MovieBookingApp.Entity.Movie;
import com.cognizant.MovieBookingApp.Entity.Ticket;
import com.cognizant.MovieBookingApp.Repository.MovieRepository;

@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("local")
public class MovieRepositoryTest {
	@Mock
	MovieRepository movieRepository;

	private String genre = "testGenre";

	private String audio = "testAudio";

	private String theatreName = "testName";

	private int totalNoseats = 100;

	private int seatsAvailable = 100;

	private String imgUrl = "";

	private List<Ticket> ticket = new ArrayList<>();

	private Long id = 837818716L;

	private static final String movieName = "testMovieName";

	private static final float rating = 6.77F;

	@Test
	public void testConstructorFields() {
		Long transactionId = 873282878L;
		
		int bookedSeats = 10;
		
		Long movie_id_fk = 82742746L;
		
		Movie movieTest = new Movie(id, movieName, rating, genre, audio, theatreName, totalNoseats, seatsAvailable,
				imgUrl, ticket);
		
		Ticket testTicket = new Ticket(transactionId, movieName, totalNoseats, seatsAvailable, bookedSeats,
				movie_id_fk);
		ticket.add(0, testTicket);
		
		assertEquals(movieTest.getMovieName(),"testMovieName");
		assertEquals(movieTest.getTicket().get(0), testTicket);

	}
}