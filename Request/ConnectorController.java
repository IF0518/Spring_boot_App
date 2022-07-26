package com.offer.requestOffer;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ConnectorController {
	
	@Autowired
	CustomerRepository custDao;
	@Autowired
	PostRequestRepository reqImpl;
	@Autowired
	UserService us;
	
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/")
	public ModelAndView index(Model model) {
		List<PostRequest> listRequest = (List<PostRequest>) reqImpl.findAll();
		model.addAttribute("requests", listRequest);
		ModelAndView mAv = new ModelAndView("index");
		return mAv;
	}
	
	@GetMapping("/login")
	public ModelAndView loginUrl() {
		return new ModelAndView("login");
	}
	
	@GetMapping("/search")
	public String search(String keyword, Model model) {
		return searchByPage(keyword,model, 1);
	}
	
	@GetMapping("/search/page/{pageNum}")
	public String searchByPage(String keyword, Model model, @PathVariable(name="pageNum") int pageNum) {
		
		 Page<PostRequest> result = us.search(keyword, pageNum);
		 
		 List<PostRequest> listResult = result.getContent();
		 
		 model.addAttribute("totalPage", result.getTotalPages());
		 model.addAttribute("totalItems", result.getTotalElements());
		 model.addAttribute("currentPage", pageNum);
		 
		 long startCount = (pageNum-1)*us.search_result_per_page + 1;
		 
		 model.addAttribute("startCount", startCount);
		 
		 long endCount = startCount + us.search_result_per_page -1;
		 
		 if(endCount > result.getTotalElements()) {
			 endCount = result.getTotalElements();
		 }
		 
		 model.addAttribute("endCount", endCount);
		 model.addAttribute("listResult", listResult);
		 model.addAttribute("keyword",keyword);
		 
		 return "search_result";
	}
	
		
	@GetMapping("/register")
	public ModelAndView requestAccount(Model model) {
		model.addAttribute("customer", new Customer());
		List<Role> roles = roleRepository.findAll();
		model.addAttribute("allRoles", roles);
		return new ModelAndView("register");
	}
	
	@PostMapping("/Register")
	public ModelAndView requestAcc(Customer customer, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
		us.register(customer, requestUrl(request));
		
		return new ModelAndView("register_success");
	}
	
	
		public String requestUrl(HttpServletRequest req) {
			
			String siteUrl =req.getRequestURI().toString();
			return siteUrl.replace(req.getServletPath(), "");
			
		}
		
		@GetMapping("/verify")
		public ModelAndView verifying(@Param("code") String code) {
			if(us.verify(code)) {
				return new ModelAndView("verify_suc");
			}else {
				return new ModelAndView("verify_fail");
			}
			
		}
		
		@GetMapping("/postRequest")
		public ModelAndView request(Model model) {
			model.addAttribute("request", new PostRequest());
			
			return new ModelAndView("postRequest");
			}
		@PostMapping("/saveRequest")
		public void posting(PostRequest request, @RequestParam("image") MultipartFile multipart) throws IOException {
            String filename = StringUtils.cleanPath(multipart.getOriginalFilename());
			
			request.setUpload(filename);
			request.setUploadImagePath(request.getUploadImagePath());
			
			PostRequest savedRequest = reqImpl.save(request);
			String uploadDir = "post_request/" + savedRequest.getId();
			
			FileUploadUtil.saveFile(uploadDir, filename, multipart);
				
		}

}
