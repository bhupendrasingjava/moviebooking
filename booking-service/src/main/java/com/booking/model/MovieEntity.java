package com.booking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "movie")
public class MovieEntity {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "movie_Name", length = 755)
	private String movieName;
	
	@Column(name = "code", length = 755)
	private String code;
	
	@Column(name = "screenNumber", length = 755)
	private String screenNumber;
	
	@Column(name = "language", length = 755)
	private String language;
	
	@Column(name = "directorName", length = 755)
	private String directorName;
	
	@Column(name = "startCast", length = 755)
	private String startCast;

	@Column(name = "price", length = 755)
	private String price;
	
	@Column(name = "imageURL", length = 755)
	private String imageURL;

}
