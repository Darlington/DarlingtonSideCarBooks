package com.sidecardarbook.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sidecardarbook.dao.SideCarBookRepo;
import com.sidecardarbook.model.SideCarBook;

@RestController
public class SideCarBookAPIController {

	@Autowired
	SideCarBookRepo repo;

	
	@RequestMapping("/api/getBooks")
	@ResponseBody
	public List<SideCarBook> getBook(SideCarBook sideCarBook) {
		
		List<SideCarBook> books = new ArrayList<>();
		repo.findAll().forEach(books::add);
		return books;
	}
}
