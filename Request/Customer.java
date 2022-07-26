package com.offer.requestOffer;


import javax.persistence.Table;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Customer")
public class Customer {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
long id;
@Column(name="Email", unique=true, nullable=false, length=80)
String username;
@Column(name="Password", nullable=false, length=80)
String password;
@Column(name="Firstname", nullable=false, length=65)
String firstname;

@Column(name="Lastname", nullable=false, length=65)
String lastname;

@Column(name="Phone", nullable=false, length=15)
String phone;

@Column(name="Country", nullable=false, length=60)
String country;
@Column(name="Gender", nullable=false, length=70)
String gender;
@Column(name="VerificationCode", length=64)
String verificationCode;
boolean enabled;

@OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name = "request_fid", referencedColumnName = "id")
Set < PostRequest > request = new HashSet < > ();
@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
@JoinTable(
		name="User_role",
		joinColumns=@JoinColumn(name="user_id"),
		inverseJoinColumns=@JoinColumn(name="role_id")
		)
Set<Role> roles = new HashSet<>();

public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password=password;
}
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

public String getUsername() {
	
	return username;
}
public void setEmail(String username) {
	 this.username=username;
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

public String getVerificationCode() {
	return verificationCode;
}

public void setVerificationCode(String verificationCode) {
	this.verificationCode=verificationCode;
}

public Set<PostRequest> getRequest(){
	return request;
}

public void setRequest(Set<PostRequest> request) {
	this.request=request;
}
public boolean isEnabled() {
	return enabled;
}
public void setEnabled(boolean enabled) {
	this.enabled = enabled;
} 


public Set<Role> getRoles(){
	return roles;
}

public void addRoles(Role role) {
	this.roles.add(role);
}

public void setRoles(Set<Role> roles) {
	this.roles = roles;
}

}




