package com.EmanSponser.ServiceImpl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.EmanSponser.model.UserDetails_Sponsor;
import com.EmanSponser.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	UserDetails_Sponsor user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new org.springframework.security.core.userdetails.User(
        		user.getUsername(), 
        		user.getPassword(), 
        		user.getAuthorities()
        	);
    }
    
    public UserDetails_Sponsor loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
    
    public void saveUser(UserDetails_Sponsor user)
    {
    	userRepository.save(user);
    }
}
