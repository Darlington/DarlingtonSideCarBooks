package com.sidecardarbook;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sidecardarbook.dao.SideCarBookUserRepo;
import com.sidecardarbook.model.SideCarBookUser;

@Service
public class SideCarBookUserDetailService implements UserDetailsService {

	@Autowired
	SideCarBookUserRepo sideCarBookUserRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SideCarBookUser sideCarBookUser =  Optional.of(sideCarBookUserRepo.findByUsername(username)).flatMap(x ->x.stream().findFirst()).orElse(new SideCarBookUser());
		
		if(sideCarBookUser == null) {
			throw new UsernameNotFoundException("User 404");
		}
		return new SideCarBookUserDetails(sideCarBookUser);
	}

}
