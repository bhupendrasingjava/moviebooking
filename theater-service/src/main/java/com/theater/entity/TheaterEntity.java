package com.theater.entity;

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
@Table(name = "theater")
public class TheaterEntity {
	
	@Id
	@Column(name = "id" )
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "theaterName",length = 755)
	private String theaterName;
	
	@Column(name= "city", length = 755)
	private String City;
	
	
	@Column(name= "type", length = 755)
	private String type;
	

	@Column(name= "address", length = 755)
	private String address;
	
	@Column(name= "image", length = 755)
	private String image;

	@Override
	public String toString() {
		return "TheaterEntity [id=" + id + ", theaterName=" + theaterName + ", City=" + City + ", type=" + type
				+ ", address=" + address + ", image=" + image + "]";
	}
	
	
	
}
