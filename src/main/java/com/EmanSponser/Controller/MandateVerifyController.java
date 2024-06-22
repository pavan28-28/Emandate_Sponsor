package com.EmanSponser.Controller;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.EmanSponser.ServiceImpl.custVerifyDetailsServiceImpl;
import com.EmanSponser.model.CustDetails_Banker;
import com.EmanSponser.model.Details_status;
import com.EmanSponser.service.custVerifydetailsService;

@Controller
public class MandateVerifyController {

    private  static final Logger logger = Logger.getLogger(MandateVerifyController.class);

    @Autowired
	public custVerifydetailsService verifycust;

    
    @GetMapping("/V/{token}")
	public String linkVerifyGet(Model model ,@PathVariable String token) {
    	
    	
    	logger.info("verify hitting get...");
		Details_status checkToken=verifycust.weblinkverify(token);
		logger.info("Token verify status:"+checkToken.isStatus());
		
		if(checkToken.isStatus())
		{
			model.addAttribute("CustDetails", checkToken.getDetails());
			return "customerFile";
		}else {
			return "expire";
		}
	}
    
//	@PostMapping("/submitForm")
//	public String linkVerify(CustDetails_Banker custDetails) 
	
	 @PostMapping("/submitForm")
    public String linkVerify(@ModelAttribute("CustDetails") CustDetails_Banker custDetails,Model model) 
     {
		logger.info("verify hitting saveAll....");
//		boolean page=	verifycust.weblinkverify(token);
//		String token=custDetails.getToken();
		logger.info("Updated custDetails"+custDetails.toString());
		String cnfrndeb=custDetails.getConfirm_debit_account();
		String token=custDetails.getToken();
		System.out.println("Token>>>>>>>>>"+token);
		System.out.println("Confirm debit No>>>>>>>>>"+cnfrndeb);
		verifycust.updateByToken(custDetails,token);
		
		return "redirect:/submitForm";
	}
	
	
	@GetMapping("/submitForm")
	public String index(Model model)
	{
		logger.info("hiting");
		model.addAttribute("CustDetails",new CustDetails_Banker());
		return "demo1";
	}
}
