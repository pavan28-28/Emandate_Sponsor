package com.EmanSponser.ServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmanSponser.Controller.MainController;
import com.EmanSponser.model.CustDetails_Banker;
import com.EmanSponser.repository.CustDetails_banker_Repo;
import com.EmanSponser.service.CustDetailsService;


@Service
public class CustDetailServiceImpl implements CustDetailsService {
    private  static final Logger logger = Logger.getLogger(CustDetailServiceImpl.class);

	@Autowired
	public CustDetails_banker_Repo repo;

	@Override
	public void custdetails(CustDetails_Banker custDetails) {

		try {

			String token = getRandomChar();
			logger.info("Lengthof token" + token.length());
			LocalDateTime dateafter24hr = LocalDateTime.now().plusHours(24);
			/*
			 * Instant instant = dateafter24hr.atZone(ZoneId.systemDefault()).toInstant();
			 * Date expiretime = Date.from(instant);
			 */
			custDetails.setToken(token);
			custDetails.setExpirationTime(dateafter24hr);
			repo.save(custDetails);
			String token1=custDetails.getToken();
			String mobile=custDetails.getMobile();
			sendSMS(mobile,token1);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

//	private String createToken() {
//
//		String sessionToken = UUID.randomUUID().toString();
//
//		logger.info("sessionToken:" + sessionToken);
//		return sessionToken;
//
//	}
	
	private static String getRandomChar()
	{
		String random="";
		String possibleChar="QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
		for(int i=0;i<5;i++)
		
			random+=possibleChar.charAt((int)Math.floor(Math.random()* possibleChar.length()));
			return random;
	}
	
	
	    public static String sendSMS(String Mob,String token) {
	        String template = "Dear customer, Please click on below link to register for e-mandate, {#var#}{#var#} this link is valid for next {#var#} hours, Thank you for banking with us. RMGB.";
	        String link = "https://emandate.nachcedge.in/emandate/V/"+token; 
	        String hours = "24";

	        
	        int placeholderLength = 30;
	        
	        
	        String firstPart;
	        String secondPart = "";

	        if (link.length() > placeholderLength) {
	            firstPart = link.substring(0, placeholderLength);
	            secondPart = link.substring(placeholderLength);
	        } else {
	            firstPart = link;
	        }

	        // Replace the first {#var#} with the first part of the link
	        String result = template.replaceFirst("\\{#var#\\}", firstPart);
	        // Replace the second {#var#} with the second part of the link (or blank if link was less than 30 characters)
	        result = result.replaceFirst("\\{#var#\\}", secondPart);
	        // Replace the third {#var#} with "24"
	        result = result.replaceFirst("\\{#var#\\}", hours);

	        System.out.println(result);
	        
	        logger.info("SMS:" + result);
	        
	        String userName = "mrgbhttpotp";
	        String sender_id = "RMGBBK";
	        String passWord = "mrgb1234";
//	        String tokentosend=token;
	        
	        logger.info("Sender ID: " + sender_id + " Username: " + userName + " Password: " + passWord);
//	        String inputLine = "";
	        if (Mob == null) {
	            Mob = "";
	        }
	        String reqU = "https://hahttp2.myvfirst.com/smpp/sendsms?";
	        String Mobile = Mob.replace("-", "");
	        Mobile = Mobile.replace("+", "");
	        
	        String Parameters = "username~" + userName + "&password~" + passWord + "&to~mob_no&from~" + sender_id
	                + "&text~text_message";
	        Parameters = Parameters.replaceAll("~", "=");
	        Parameters = Parameters.replaceAll("mob_no", Mobile.trim());

	        try {
	            Parameters = Parameters.replaceAll("text_message", URLEncoder.encode(result, "UTF-8"));

	        } catch (UnsupportedEncodingException e1) {
	            logger.warn("Exception occurred while encoding OTP Message: " + e1.getMessage());
	        }

	        String requestURL1 = reqU.concat(Parameters);
	        logger.info("URL: " + requestURL1);

	        try {
	            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.1.24.97", 8080));  //prod--> 10.43.5.6  3128  local -->10.1.24.97  8080
	            URLConnection yc = new URL(requestURL1).openConnection(proxy);
	            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
//	            while ((inputLine = in.readLine()) != null) {
//	                // Process response if needed
//	            }
	            in.close();
	        } catch (Exception e) {
	            logger.warn("Exception occurred while sending SMS: " + e.getMessage());
	        }
	        return result;
	    }

	    public static void main(String args[]) throws IOException {
	        String message = sendSMS("91-7972575012","W1eOM");
	        System.out.println("Return statement: " + message);
	    }

}
