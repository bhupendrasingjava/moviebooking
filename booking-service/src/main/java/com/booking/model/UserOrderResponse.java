package com.booking.model;

import java.util.Date;

import com.booking.entity.UserOrderBillingEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOrderResponse {
	
	private long orderId;
	private double amount;
	private double paidAmount;
	private boolean payStatus;
	
	private UserOrderBillingEntity userOrderBillingEntity;
	private TheaterEntity theaterEntity;
	private MovieEntity movieEntity;
	private Date createdDate;
	
	

}
