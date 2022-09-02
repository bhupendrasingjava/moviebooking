package com.movie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.BDDMockito.given;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.annotation.Rollback;
import com.movie.entity.MovieEntity;
import com.movie.repository.MovieRepository;
import com.movie.service.MovieServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MovieServiceTest {

	@Mock
	private MovieRepository movieRepository;

	@InjectMocks
	private MovieServiceImpl movieService;

	private MovieEntity movie;

	@BeforeEach
	public void setup() {
		movie = MovieEntity.builder()
				.id(1L)
				.movieName("BAHUBALI")
				.directorName("S. Rajamoulli")
				.code("BHU001")
				.imageURL("http://92.168.2.21/bahu.jpg")
				.startCast("Anushka, Prabhash")
				.language("Hindi")
				.price("500")
				.screenNumber("AUDI-002").build();
	}

	@DisplayName("JUnit test for saveMovie method")
	@Test
	public void givenMovieObject_whenSaveMovie_thenReturnMovieObject() {

		given(movieRepository.findById(movie.getId())).willReturn(Optional.empty());

		given(movieRepository.save(movie)).willReturn(movie);
		// when - action or the behaviour that we are going test
		MovieEntity savedMovie = movieService.saveMovie(movie);
		// then - verify the output
		assertThat(savedMovie).isNotNull();
	}

	@DisplayName("JUnit test for getMovieById method")
	@Test
	public void givenMovieId_whenGetMovieById_thenReturnMovieObject() {
		// given
		given(movieRepository.findById(1L)).willReturn(Optional.of(movie));

		// when
		MovieEntity savedMovie = movieService.getMovieById(movie.getId()).get();

		// then
		assertThat(savedMovie).isNotNull();

	}

	@DisplayName("JUnit test for getAllMovie method")
	@Test
	public void givenMovieList_whenGetAllMovie_thenReturnMovieList() {
		// given - precondition or setup
		MovieEntity movie2 = MovieEntity.builder()
				.id(2L).movieName("RRR")
				.directorName("S. Rajamoulli")
				.code("RRR001")
				.imageURL("http://92.168.2.21/rrr.jpg")
				.startCast("Ramcharan, NTR")
				.language("Hindi")
				.price("400")
				.screenNumber("AUDI-003")
				.build();

		given(movieRepository.findAll()).willReturn(List.of(movie, movie2));

		// when - action or the behaviour that we are going test
		List<MovieEntity> MovieList = movieService.movieList();

		// then - verify the output
		assertThat(MovieList).isNotNull();
		assertThat(MovieList.size()).isEqualTo(2);
	}

	@DisplayName("JUnit test for getAllMovie method")
	@Test
	@Rollback // actually our test is "self-cleaning"...when no exceptions ;)
	void removeMovieByIdExists() {
		// Given:
		MovieEntity movie3 = MovieEntity.builder()
				.id(11L)
				.movieName("DIL")
				.directorName("S. Rajamoulli")
				.code("RRR001")
				.imageURL("http://92.168.2.21/rrr.jpg")
				.startCast("Aamir, Madhuri")
				.language("Hindi")
				.price("400")
				.screenNumber("AUDI-003").build();

		MovieEntity savedMovie = movieRepository.save(movie3);
		// When:
		movieService.deleteMovie(11L);

		// Then:
		assertFalse(movieRepository.findById(11L).isPresent());
	}

	@DisplayName("JUnit test for updateMovie method")
	@Test
	public void givenMovieObject_whenUpdateMovie_thenReturnUpdatedMovie() {
		// given - precondition or setup
		given(movieRepository.save(movie)).willReturn(movie);
		movie.setMovieName("safar");
		movie.setStartCast("Salman");
		// when - action or the behaviour that we are going test
		MovieEntity updatedMovie = movieService.updateMovie(movie);
		// then - verify the output
		assertThat(updatedMovie.getMovieName()).isEqualTo("safar");
		assertThat(updatedMovie.getStartCast()).isEqualTo("Salman");
	}

}
