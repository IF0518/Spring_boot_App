package com.offer.requestOffer;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;

@Service
public class UserService {
	
	@Autowired
	CustomerRepository cr;
	
	
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	 private PostRequestRepository rRpo;
	
	int search_result_per_page = 10;
	
	public Page<PostRequest> search(String keyword, int pageNum){
		Pageable pageable = PageRequest.of(pageNum-1, search_result_per_page);
		return rRpo.search(keyword, pageable);
	}
	
	public void register(Customer customer, String siteurl) throws MessagingException, UnsupportedEncodingException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String passEncoded = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(passEncoded);
		
		String code = RandomString.make(64);
		
		customer.setVerificationCode(code);
		customer.setEnabled(false);
		customer.setRoles(customer.getRoles());
		
		cr.save(customer);
		
		sendVerificationCode(customer, siteurl);
	}
	
	public void sendVerificationCode(Customer customer, String siteurl) throws MessagingException, UnsupportedEncodingException {
		
		String toAddress=customer.getUsername();
		String fromAddress="georgegitau2960@gmail.com";
		String senderName="Connector";
		String subject = "Verify account";
		String content ="Dear [[name]]<br>"
				+"Please verify your account below"
				+"<h3><a href=\"[[url]]\"  target=\"_self\">verify</a></h3>"
				+"Thank You<br>"
				+ "Connector";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setTo(toAddress);
		helper.setFrom(fromAddress, senderName);
		helper.setSubject(subject);
		
		
		content=content.replace("[[name]]", customer.getFirstname());
		
		String verifyUrl = siteurl + "/verify?code=" + customer.getVerificationCode();
		
		content=content.replace("[[url]]", verifyUrl);
		
		helper.setText(content, true);
		
		mailSender.send(message);
					
	}
	
	public boolean verify(String code) {
		
		Customer customer = cr.findByVerificationCode(code);
		
		if(customer == null || customer.isEnabled()) {
			return false;
		}
		else
		{
			customer.setVerificationCode(null);
			customer.setEnabled(true);
			cr.save(customer);
			return true;
		}
			
	}

}
