package com.offer.requestOffer;



import javax.persistence.*;

@Entity
@Table(name="Role")
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="Name", nullable=false, length=45)
	private String name;
	
	public Role() {
	
	}
	
	
	
	public Role(String name) {
		this.name=name;
	}
	
	public Role(Long id, String name) {
		this.id=id;
		this.name=name;
	}
	public Role(Long id) {
		this.id=id;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}

}
