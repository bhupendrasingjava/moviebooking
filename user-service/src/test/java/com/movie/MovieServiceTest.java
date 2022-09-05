/*package com.movie;

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

import com.user.entity.UserEntity;
import com.user.repository.UserRepository;
import com.user.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MovieServiceTest {

	@Mock
	private UserRepository movieRepository;

	@InjectMocks
	private UserServiceImpl userService;

	private UserEntity user;

	@BeforeEach
	public void setup() {
		user = UserEntity.builder()
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

		given(movieRepository.findById(user.getId())).willReturn(Optional.empty());

		given(movieRepository.save(user)).willReturn(user);
		// when - action or the behaviour that we are going test
		UserEntity savedMovie = userService.saveUser(user);
		// then - verify the output
		assertThat(savedMovie).isNotNull();
	}

	@DisplayName("JUnit test for getMovieById method")
	@Test
	public void givenMovieId_whenGetMovieById_thenReturnMovieObject() {
		// given
		given(movieRepository.findById(1L)).willReturn(Optional.of(user));

		// when
		UserEntity savedMovie = userService.getUserById(user.getId()).get();

		// then
		assertThat(savedMovie).isNotNull();

	}

	@DisplayName("JUnit test for getAllMovie method")
	@Test
	public void givenMovieList_whenGetAllMovie_thenReturnMovieList() {
		// given - precondition or setup
		UserEntity user2 = UserEntity.builder()
				.id(2L).movieName("RRR")
				.directorName("S. Rajamoulli")
				.code("RRR001")
				.imageURL("http://92.168.2.21/rrr.jpg")
				.startCast("Ramcharan, NTR")
				.language("Hindi")
				.price("400")
				.screenNumber("AUDI-003")
				.build();

		given(movieRepository.findAll()).willReturn(List.of(user, user2));

		// when - action or the behaviour that we are going test
		List<UserEntity> MovieList = userService.userList();

		// then - verify the output
		assertThat(MovieList).isNotNull();
		assertThat(MovieList.size()).isEqualTo(2);
	}

	@DisplayName("JUnit test for getAllMovie method")
	@Test
	@Rollback // actually our test is "self-cleaning"...when no exceptions ;)
	void removeMovieByIdExists() {
		// Given:
		UserEntity movie3 = UserEntity.builder()
				.id(11L)
				.movieName("DIL")
				.directorName("S. Rajamoulli")
				.code("RRR001")
				.imageURL("http://92.168.2.21/rrr.jpg")
				.startCast("Aamir, Madhuri")
				.language("Hindi")
				.price("400")
				.screenNumber("AUDI-003").build();

		UserEntity savedMovie = movieRepository.save(movie3);
		// When:
		userService.deleteUser(1L);

		// Then:
		assertFalse(movieRepository.findById(11L).isPresent());
	}

	@DisplayName("JUnit test for updateMovie method")
	@Test
	public void givenMovieObject_whenUpdateMovie_thenReturnUpdatedMovie() {
		// given - precondition or setup
		given(movieRepository.save(user)).willReturn(user);
		user.setMovieName("safar");
		user.setStartCast("Salman");
		// when - action or the behaviour that we are going test
		UserEntity updatedMovie = userService.updateUser(user);
		// then - verify the output
		assertThat(updatedMovie.getMovieName()).isEqualTo("safar");
		assertThat(updatedMovie.getStartCast()).isEqualTo("Salman");
	}

}
*/