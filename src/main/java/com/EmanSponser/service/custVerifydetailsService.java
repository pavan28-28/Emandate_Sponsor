package com.EmanSponser.service;

import java.util.HashMap;

import com.EmanSponser.model.CustDetails_Banker;
import com.EmanSponser.model.Details_status;

public interface custVerifydetailsService {

	public Details_status weblinkverify(String token);
//	public CustDetails_Banker getDataByToken(String token);
	public void updateByToken(CustDetails_Banker custdetails,String token);
}
