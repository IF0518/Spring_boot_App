package com.offer.requestOffer;

import javax.persistence.*;


@Table(name="bid")
public class Bid {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String business;
	private String description;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id=id;
	}
	
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business=business;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description=description;
	}

}
