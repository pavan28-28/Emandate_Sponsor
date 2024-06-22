package com.EmanSponser.ServiceImpl;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmanSponser.model.CustDetails_Banker;
import com.EmanSponser.model.Details_status;
import com.EmanSponser.repository.CustDetails_banker_Repo;
import com.EmanSponser.service.custVerifydetailsService;

@Service
public class custVerifyDetailsServiceImpl implements custVerifydetailsService {

    private  static final Logger logger = Logger.getLogger(custVerifyDetailsServiceImpl.class);

	
	@Autowired
    private CustDetails_banker_Repo custdtlrepo;
	
	@Override
	public Details_status weblinkverify(String tokenfromLink) {
		
		Details_status dt_status=new Details_status();
		CustDetails_Banker MandateDetails =new CustDetails_Banker();
		try {
		
//			String status="";
			 MandateDetails=custdtlrepo.findBytoken(tokenfromLink);
			String Token=MandateDetails.getToken();
			logger.info("Token>>>"+Token);
			LocalDateTime curenttime = LocalDateTime.now();

		
			
			if(MandateDetails.getToken().equalsIgnoreCase(tokenfromLink) && MandateDetails.getExpirationTime().isAfter(curenttime) ) {
				logger.info("Token verified successfully...");
				
				dt_status.setDetails(MandateDetails);
				dt_status.setStatus(true);
				
				return dt_status;
			}
			
			else {
			//for error page
				dt_status.setDetails(MandateDetails);
				dt_status.setStatus(false);
				return dt_status;
				
			}
		}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		dt_status.setDetails(MandateDetails);
		dt_status.setStatus(false);
		return dt_status;
	
	}
	public void updateByToken(CustDetails_Banker custdetails,String token)
	{
		//CustDetails_Banker cu=custdtlrepo.findBytoken(token);
		CustDetails_Banker cu=new CustDetails_Banker();
		LocalDateTime expirationDate=custdtlrepo.findBytoken(token).getExpirationTime();
//		cu.setToken(token);
		cu.setExpirationTime(expirationDate);
//		cu.setCreditor_Number(custdetails.getCreditor_Number());
		cu.setCreditor_Number(custdetails.getCreditor_Number());
		cu.setName(custdetails.getName());
		cu.setMobile(custdetails.getMobile());
//		cu.setEmail(custdetails.getEmail());
		cu.setLoan_amt(custdetails.getLoan_amt());
		cu.setMandate_type(custdetails.getMandate_type());
		cu.setDebit_Account_number(custdetails.getDebit_Account_number());
		cu.setConfirm_debit_account(custdetails.getConfirm_debit_account());
		cu.setAccount_type(custdetails.getAccount_type());
//		cu.setIfsc(custdetails.getIfsc());
		cu.setCollection_amt(custdetails.getCollection_amt());
//		cu.setMax_coll_amt(custdetails.getMax_coll_amt());
//		cu.setFrequency(custdetails.getFrequency());
		cu.setFrequency(custdetails.getFrequency());
		cu.setColl_first_date(custdetails.getColl_first_date());
		cu.setColl_last_date(custdetails.getColl_last_date());
		cu.setBank(custdetails.getBank());
		cu.setToken(token);
		
		System.out.println("updating object>>>>>"+cu.toString());
		 custdtlrepo.save(cu);
		 
		 logger.info("record updated...");
	}
//	public CustDetails_Banker getDataByToken(String token)
//	{
//		CustDetails_Banker MandateDetails=custdtlrepo.findBytoken(token);
//        return MandateDetails;
//	}

	

}
