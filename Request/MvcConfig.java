package com.offer.requestOffer;

import java.nio.file.Path;

import java.nio.file.Paths;

import org.springframework.context.annotation.*;

import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
   
	
	public void addResourceHandlers(String dirName, ResourceHandlerRegistry registry) {
		exposeDirectory("post_request", registry);
	}
	
	public void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
		
		Path uploadDir = Paths.get(dirName);
		
		String uploadpath = uploadDir.toFile().getAbsolutePath();
		if(dirName.startsWith("../")) dirName = dirName.replace("../", "");
		
		registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadpath + "/");
	}

}
