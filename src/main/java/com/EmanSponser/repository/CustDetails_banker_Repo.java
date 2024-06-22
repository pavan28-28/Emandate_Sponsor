package com.EmanSponser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.EmanSponser.model.CustDetails_Banker;

@Repository
public interface CustDetails_banker_Repo extends CrudRepository<CustDetails_Banker, Integer>{

	public CustDetails_Banker findBytoken(String token);
	
}
