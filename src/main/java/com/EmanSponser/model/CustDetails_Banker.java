package com.EmanSponser.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class CustDetails_Banker {

	@Id
	private String token;
	private String creditor_Number;
	private String name;
	private String mobile;
//	private String email;
	private String loan_amt;
	private String bank;
	private String mandate_type;//not mentioned in frontend
	private String debit_Account_number;
	private String confirm_debit_account;
	private String account_type;//not mentioned.....
//	private String ifsc;
	private String collection_amt;
//	private String max_coll_amt;
	private String frequency;//not mentioned
	private String coll_first_date;
	private String coll_last_date;
	private LocalDateTime expirationTime;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCreditor_Number() {
		return creditor_Number;
	}
	public void setCreditor_Number(String creditor_Number) {
		this.creditor_Number = creditor_Number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLoan_amt() {
		return loan_amt;
	}
	public void setLoan_amt(String loan_amt) {
		this.loan_amt = loan_amt;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getMandate_type() {
		return mandate_type;
	}
	public void setMandate_type(String mandate_type) {
		this.mandate_type = mandate_type;
	}
	public String getDebit_Account_number() {
		return debit_Account_number;
	}
	public void setDebit_Account_number(String debit_Account_number) {
		this.debit_Account_number = debit_Account_number;
	}
	public String getConfirm_debit_account() {
		return confirm_debit_account;
	}
	public void setConfirm_debit_account(String confirm_debit_account) {
		this.confirm_debit_account = confirm_debit_account;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getCollection_amt() {
		return collection_amt;
	}
	public void setCollection_amt(String collection_amt) {
		this.collection_amt = collection_amt;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getColl_first_date() {
		return coll_first_date;
	}
	public void setColl_first_date(String coll_first_date) {
		this.coll_first_date = coll_first_date;
	}
	public String getColl_last_date() {
		return coll_last_date;
	}
	public void setColl_last_date(String coll_last_date) {
		this.coll_last_date = coll_last_date;
	}
	public LocalDateTime getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(LocalDateTime expirationTime) {
		this.expirationTime = expirationTime;
	}
	@Override
	public String toString() {
		return "CustDetails_Banker [token=" + token + ", creditor_Number=" + creditor_Number + ", name=" + name
				+ ", mobile=" + mobile + ", loan_amt=" + loan_amt + ", bank=" + bank + ", mandate_type=" + mandate_type
				+ ", debit_Account_number=" + debit_Account_number + ", confirm_debit_account=" + confirm_debit_account
				+ ", account_type=" + account_type + ", collection_amt=" + collection_amt + ", frequency=" + frequency
				+ ", coll_first_date=" + coll_first_date + ", coll_last_date=" + coll_last_date + ", expirationTime="
				+ expirationTime + "]";
	}
	public CustDetails_Banker(String token, String creditor_Number, String name, String mobile, String loan_amt,
			String bank, String mandate_type, String debit_Account_number, String confirm_debit_account,
			String account_type, String collection_amt, String frequency, String coll_first_date, String coll_last_date,
			LocalDateTime expirationTime) {
		super();
		this.token = token;
		this.creditor_Number = creditor_Number;
		this.name = name;
		this.mobile = mobile;
		this.loan_amt = loan_amt;
		this.bank = bank;
		this.mandate_type = mandate_type;
		this.debit_Account_number = debit_Account_number;
		this.confirm_debit_account = confirm_debit_account;
		this.account_type = account_type;
		this.collection_amt = collection_amt;
		this.frequency = frequency;
		this.coll_first_date = coll_first_date;
		this.coll_last_date = coll_last_date;
		this.expirationTime = expirationTime;
	}
	public CustDetails_Banker() {
		super();
	}
	
	
	
}
