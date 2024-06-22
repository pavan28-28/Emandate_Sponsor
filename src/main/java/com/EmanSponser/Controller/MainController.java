package com.EmanSponser.Controller;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.EmanSponser.ServiceImpl.CustomUserDetailsService;
import com.EmanSponser.model.CustDetails_Banker;
import com.EmanSponser.model.UserDetails_Sponsor;
import com.EmanSponser.repository.CustDetails_banker_Repo;
import com.EmanSponser.service.CustDetailsService;

@Controller
public class MainController {

	private static final Logger log = Logger.getLogger(MainController.class);

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private CustDetailsService custdtl;

	@Autowired
	private CustomUserDetailsService custUserDetailSer;

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("UserDetails_Sponsor", new UserDetails_Sponsor());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("UserDetails_Sponsor")  UserDetails_Sponsor user, Model model) { // Check if the username is
																						// already registered UserThym
																						// existingUser
//    	UserDetails_Sponsor existingUser= repo.findByUsername(user.getUsername());
		UserDetails_Sponsor existingUser = new UserDetails_Sponsor();
		try {
			existingUser = custUserDetailSer.loadUserDetailsByUsername(user.getUsername());

			if(existingUser.getUsername() != null || existingUser.getUsername().equals(""))
			{
				log.info("User is already present....");
				model.addAttribute("message", "User is Already Registered..");

				return "redirect:/register";
			}else {
				System.out.println("User not found with username: " + user.getUsername());
				UserDetails_Sponsor SecureUser = new UserDetails_Sponsor();
				SecureUser.setUsername(user.getUsername());
				SecureUser.setPassword(encoder.encode(user.getPassword()));

				custUserDetailSer.saveUser(SecureUser);
				model.addAttribute("message", "Registration successful");
				return "redirect:/login";
			}
//			model.addAttribute("message", "Username already exists");
//			return "register";
		} catch (Exception e) {
//    		e.printStackTrace();
			System.out.println("User not found with username: " + user.getUsername());
			UserDetails_Sponsor SecureUser = new UserDetails_Sponsor();
			SecureUser.setUsername(user.getUsername());
			SecureUser.setPassword(encoder.encode(user.getPassword()));

			custUserDetailSer.saveUser(SecureUser);
			model.addAttribute("message", "Registration successful");
			return "redirect:/login";
		}

	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String username,@RequestParam String password,Model model,Principal principal) {
    	  UserDetails_Sponsor user = (UserDetails_Sponsor) custUserDetailSer.loadUserByUsername(username); 
		log.info("login postMapping...");
		String username1 = user.getUsername();
		String logoPath;

		if (username1 != null) {
			switch (username) {
			case "RMGB":
				logoPath = "/images/rmgb-logo.png";
				break;
			default:
				logoPath = "/images/cedge-logo.png";
				break;
			}

			model.addAttribute("logoPath", logoPath);
			return "redirect:/SaveCustData";
		} else {

			model.addAttribute("message", "Invalid username or password.");
			return "login";
		}
//	    return "login";
	}
	
//	@PostMapping("/login")
//	   public String login(@RequestParam String username, @RequestParam String password, Model model)
//	    {
//  	  UserDetails_Sponsor user = (UserDetails_Sponsor) custUserDetailSer.loadUserByUsername(username); 
//	    if (user != null) 
//	    {  
//	    model.addAttribute("message", "Login successful!"); 
//	    } else {
//	  
//	       model.addAttribute("message", "Invalid username or password."); 
//	       } 
//	    return "login";
//	    }

	private String getUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		} else {
			return principal.toString();
		}
	}

	@GetMapping("/SaveCustData")
	public String index(Model model) {
		log.info("hitting SaveCustData GET\"");
		model.addAttribute("CustDetails", new CustDetails_Banker());
//		String username = getUsername();
//		log.info("Username:"+username);
//		
//		if(username=="")
//		String logoPath = "/img/RMGB.jpg";
//		model.addAttribute("logoPath", logoPath);
//		return "demo1";
		
		String username = getUsername();
		String logoPath;

		if (username != null) {
			switch (username) {
			case "RMGB":
				logoPath = "/img/RMGB.jpg";
				break;
			default:
				logoPath = "/img/cedge-logo.png";
				break;
			}

			model.addAttribute("logoPath", logoPath);
			return "demo1";
		} else {

//			model.addAttribute("message", "Invalid username or password.");
			return "login";
		}
	}

	@PostMapping("/SaveCustData")
	public String index1(CustDetails_Banker custDetails) {
		log.info("Hitting SaveCustData POST.....");
		custdtl.custdetails(custDetails);
		log.info("data inserted into db....");
		return "redirect:SaveCustData";
	}

//	@GetMapping("/")
//	public String index3()
//	{
//		return "demo1";
//	}
}
