package com.offer.requestOffer;



import javax.persistence.*;

@Entity
@Table(name="Business")
public class Business {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	@Column(name="Firstname")
	String firstname;
	@Column(name="lastname")
	String lastname;
	@Column(name="Business")
	String business;
	@Column(name="Email")
	String email;
	@Column(name="Phone")
	String phone;
	@Column(name="Country")
	String country;
	@Column(name="Gender")
	String gender;
	

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname=firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname=lastname;
	}
	
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business=business;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone=phone;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country=country;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender=gender;
	}
}