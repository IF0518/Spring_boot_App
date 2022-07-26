package com.offer.requestOffer;

import javax.persistence.*;

@Entity
@Table(name="PostRequest")
public class PostRequest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	@Column(nullable=false, length=100)
	String name;
	@Column(nullable=false, length=120)
	String title_description;
	@Column(length=1000)
	String full_description;
	@Column(nullable=false, length=80)
	String date;
	@Column(name="Upload")
	String upload;
	
	
	@Column(nullable=true)
	String upload_image_path;
	
	public PostRequest() {}
	
	public PostRequest(long id, String name, String title_description, String full_description,String date, String upload, String upload_image_path) {
		this.id=id;
		this.name=name;
		this.title_description=title_description;
		this.full_description=full_description;
		this.date=date;
		this.upload=upload;
		this.upload_image_path=upload_image_path;
	
	}
	

	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getTitleDescription() {
		return title_description;
	}
	public void setTitleDescription(String title_description) {
		this.title_description=title_description;
	}
	public String getFullDescription() {
		return full_description;
	}
	public void setFullDescription(String full_description) {
		this.full_description=full_description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date=date;
	} 
	@Transient
	
	public String getUploadImagePath() {
		
		if(upload == null || id == -1)
		return null;
		
		return "/post_request/"+ id +"/"+ upload;
	}
	public void setUploadImagePath(String upload_image_path) {
		this.upload_image_path=upload_image_path;
	}
	public void setUpload(String upload) {
		this.upload=upload;
	}
	
		
	
}
