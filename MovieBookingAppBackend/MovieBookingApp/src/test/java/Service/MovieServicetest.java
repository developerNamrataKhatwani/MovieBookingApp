package Service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

import com.cognizant.MovieBookingApp.Entity.Movie;
import com.cognizant.MovieBookingApp.Entity.Ticket;
import com.cognizant.MovieBookingApp.KafkaProducer.Producer;
import com.cognizant.MovieBookingApp.Repository.MovieRepository;
import com.cognizant.MovieBookingApp.Service.Impl.MovieServiceImpl;

@RunWith(MockitoJUnitRunner.class) // JUnit 4
public class MovieServicetest {
	@InjectMocks
	MovieServiceImpl movieServiceImpl;

	@Mock
	MovieRepository movieRepository;

	@Mock
	Producer producer;

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
	public void testAddMovie() throws Exception {

		Long transactionId = 873282878L;

		int bookedSeats = 10;

		Long movie_id_fk = 82742746L;

		Movie movieTest = new Movie(id, movieName, rating, genre, audio, theatreName, totalNoseats, seatsAvailable,
				imgUrl, ticket);

		Ticket testTicket = new Ticket(transactionId, movieName, totalNoseats, seatsAvailable, bookedSeats,
				movie_id_fk);
		ticket.add(0, testTicket);

		when(movieRepository.save(movieTest)).thenReturn(movieTest);
		Movie addtest = movieServiceImpl.addMovie(movieTest);

		assertEquals(movieTest.getMovieName(), addtest.getMovieName());

	}
}
