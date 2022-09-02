package com.booking.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.booking.entity.UserOrderBillingEntity;
import com.booking.entity.UserOrderEntity;
import com.booking.model.MovieEntity;
import com.booking.model.TheaterEntity;
import com.booking.model.UserOrderResponse;
import com.booking.repository.UserOrderBillingRepository;
import com.booking.repository.UserOrderRepository;
import com.google.gson.Gson;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/booking")
public class BookingController {

	private static final Logger log = LoggerFactory.getLogger(BookingController.class);
	
	private static final String THEATER_SERVICE = "theater-service";
	private static final String MOVIE_SERVICE = "movie-service";
	
	@Autowired
	private UserOrderRepository userOrderRepository;

	@Autowired
	private UserOrderBillingRepository userOrderBillingRepository;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/create-order")
	public String createOrder(@RequestBody UserOrderEntity entity) {
		log.info("Inside BookingController : createOrder()");
		log.debug("Inside BookingController : Saving UserOrderBillingEntity");
		UserOrderBillingEntity billingInfo = userOrderBillingRepository.save(entity.getUserOrderBillingInfo());
		entity.setUserOrderBillingInfo(billingInfo);
		entity.setCreated_date(new Date());
		userOrderRepository.save(entity);
		log.debug("Inside BookingController : Saving order");
		return "Success";
	}

	@GetMapping("/userorders")
	public List<UserOrderResponse> getUserOrder() {
		List<UserOrderResponse> userOrderResponse = new ArrayList<UserOrderResponse>();
		log.info("Inside BookingController : createOrder()");
		log.debug("Inside BookingController : Saving UserOrderBillingEntity");
		List<UserOrderEntity> findAll = userOrderRepository.findAll();

		for (UserOrderEntity userorder : findAll) {

			UserOrderResponse userOrderinfo = new UserOrderResponse();

			userOrderinfo.setAmount(userorder.getOrderAmount());
			userOrderinfo.setPaidAmount(userorder.getPaidAmount());
			userOrderinfo.setPayStatus(userorder.isPayStatus());
			userOrderinfo.setCreatedDate(userorder.getCreated_date());

			userOrderinfo.setUserOrderBillingEntity(userorder.getUserOrderBillingInfo());
			log.debug("Calling getTheaterByID Service....");
			TheaterEntity theaterEntity = this.restTemplate.getForObject(
					"http://theater-service/theater/getTheaterById?id=" + userorder.getTheatorId(), TheaterEntity.class);
			log.debug("JSON RESPONSE for getTheaterById="+new Gson().toJson(theaterEntity));
			userOrderinfo.setTheaterEntity(theaterEntity);
			log.debug("Calling getMovieById Service....");
			MovieEntity movieEntity = this.restTemplate.getForObject(
					"http://movie-service/movie/getMovieById?id=" + userorder.getMovieId(), MovieEntity.class);
			log.debug("JSON RESPONSE for getMovieById="+new Gson().toJson(movieEntity));
			userOrderinfo.setMovieEntity(movieEntity);
			userOrderResponse.add(userOrderinfo);
		}
		return userOrderResponse;

	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/theaterList")
	@CircuitBreaker(name=THEATER_SERVICE, fallbackMethod = "theaterFallback")
	public java.util.List<TheaterEntity> List() {
		log.debug("Calling Booking: theaterList()");
		java.util.List<TheaterEntity> theaterList = this.restTemplate.getForObject(
				"http://theater-service/theater/theaterList", ArrayList.class);
		log.debug("Theater list "+new Gson().toJson(theaterList));
		return theaterList;
	}
	
	public java.util.List<TheaterEntity> theaterFallback(Exception e){
		log.debug("Theater service is down...");
		log.debug("Calling fallback method!!!!!!!");
		List<TheaterEntity> theaterList = new ArrayList<>();
		TheaterEntity theater1 = new TheaterEntity();
		theater1.setCity("Noida");
		theater1.setAddress("Sector-25");
		theater1.setId(10);
		theater1.setTheaterName("PVR");
		theater1.setType("Single Screen");
		theaterList.add(theater1);
		return theaterList;
				

    }
}
