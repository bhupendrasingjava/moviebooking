package com.booking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="User_Order_Billing")
public class UserOrderBillingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private long id;
	
	@Column(name="First_name",length = 755)
	private String firstName;
	
	@Column(name="Last_name",length = 755)
	private String lastName;
	
	@Column(name="Phone",length = 755)
	private String phone;
	
	@Column(name="email",length = 755)
	private String email;
	
	@Column(name="Address",length = 1500)
	private String address;
	
}
