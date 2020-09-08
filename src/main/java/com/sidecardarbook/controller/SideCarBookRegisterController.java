package com.sidecardarbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sidecardarbook.dao.SideCarBookUserRepo;
import com.sidecardarbook.model.SideCarBookUser;

@RestController
@RequestMapping("/users")
public class SideCarBookRegisterController {
	
	@Autowired
	SideCarBookUserRepo sideCarBookUserRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @PostMapping("/sign-up")
    public void signUp(@RequestBody SideCarBookUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        sideCarBookUserRepo.save(user);
    }
}
