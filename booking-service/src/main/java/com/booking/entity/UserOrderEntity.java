package com.booking.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="User_Order")
public class UserOrderEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private long id;
	
	@Column(name="Order_amount")
	private double orderAmount;
	
	@Column(name="paid_amount")
	private double paidAmount;
	
	@Column(name="Pay_Status",columnDefinition = "BIT default 0")
	private boolean payStatus;
	
	@ManyToOne
	@JoinColumn(name="user_order_billing_info_id", referencedColumnName = "id")
	private UserOrderBillingEntity userOrderBillingInfo;
	
	@Column(name="theator_Id")
	private  long theatorId;
	
	@Column(name="movie_Id")
	private  long movieId;
	
	@Column(name="created_date")
	private Date created_date;
	
}
