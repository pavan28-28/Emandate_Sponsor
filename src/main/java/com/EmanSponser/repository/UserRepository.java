package com.EmanSponser.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmanSponser.model.UserDetails_Sponsor;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetails_Sponsor, Long> {
    Optional<UserDetails_Sponsor> findByUsername(String username);
	public UserDetails_Sponsor findByUsernameAndPassword(String username,String Password);

}

