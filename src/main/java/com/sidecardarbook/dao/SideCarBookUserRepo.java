package com.sidecardarbook.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sidecardarbook.model.SideCarBookUser;

public interface SideCarBookUserRepo extends CrudRepository<SideCarBookUser, Integer> {
	
	List<SideCarBookUser> findByUsername(String username);
	
}
