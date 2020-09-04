package com.sidecardarbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sidecardarbook.dao.SideCarBookRepo;
import com.sidecardarbook.model.SideCarBook;


@Controller
public class SideCarBookController {
	
	@Autowired
	SideCarBookRepo repo;
	
	@RequestMapping("/")
	public ModelAndView home(SideCarBook sideCarBook) {
		
		ModelAndView modelandview = new ModelAndView("home.jsp");
		populateModelAndView(modelandview);
		return modelandview;
	}
	
	@RequestMapping("/addBook")
	public ModelAndView addBook(SideCarBook sideCarBook) {
		
		ModelAndView modelandview = new ModelAndView("home.jsp");
		repo.save(sideCarBook);
		populateModelAndView(modelandview);
		return modelandview;
	}
	
	private void populateModelAndView(ModelAndView modelandview) {

		Iterable<SideCarBook> sideCarBooks = repo.findAll();
		modelandview.addObject("sideCarBooks",sideCarBooks);
	}
	

}
