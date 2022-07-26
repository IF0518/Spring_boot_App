package com.offer.requestOffer;

import java.io.*;
import java.nio.file.*;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	
	public static void saveFile(String dir, String filename, MultipartFile mpf)  throws IOException{
		Path uploadDir = Paths.get(dir);
		
		if(!Files.exists(uploadDir)) {
			Files.createDirectories(uploadDir);
		}
		
		try(InputStream inp = mpf.getInputStream()){
			Path filePath = uploadDir.resolve(filename);
			Files.copy(inp, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch(IOException ioe) {
			throw new IOException("File not found for the " + filename);
		}
	}

}
