package com.EmanSponser.model;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class UserDetails_Sponsor {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String username;
	    private String password;

	    @ElementCollection(fetch = FetchType.EAGER)
	    private List<String> roles;

	    // getters and setters

	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public List<String> getRoles() {
			return roles;
		}

		public void setRoles(List<String> roles) {
			this.roles = roles;
		}

		@Override
		public String toString() {
			return "UserDetails_Sponsor [id=" + id + ", username=" + username + ", password=" + password + ", roles="
					+ roles + "]";
		}

		public UserDetails_Sponsor(Long id, String username, String password, List<String> roles) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.roles = roles;
		}

		public UserDetails_Sponsor() {
			super();
		}
	    
	    
	    
}
