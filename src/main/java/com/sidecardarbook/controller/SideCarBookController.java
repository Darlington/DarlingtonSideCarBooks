package com.sidecardarbook.controller;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.sidecardarbook.dao.SideCarBookRepo;
import com.sidecardarbook.model.SideCarBook;


@Controller
public class SideCarBookController {
	
	Logger logger = LoggerFactory.getLogger(SideCarBookController.class);
	
	@Autowired
	SideCarBookRepo repo;
	private ModelAndView modelandview;
	
	@RequestMapping("/")
	public ModelAndView home(SideCarBook sideCarBook) {
	
		populateModelAndView();
		return modelandview;
	}
	
	@RequestMapping("/addBook")
	public ModelAndView addBook(SideCarBook sideCarBook) {
		
		repo.save(sideCarBook);
		populateModelAndView();
		logger.debug("added Book: " + new Gson().toJson(sideCarBook));
		return modelandview;
	}
	
	@RequestMapping("/updateBook")
	public ModelAndView updateBook(SideCarBook sideCarBook) {
		
		repo.save(sideCarBook);
		populateModelAndView();
		logger.debug("updated Book: " + new Gson().toJson(sideCarBook));
		return modelandview;
	}
	
	@RequestMapping("/deleteBook")
	public ModelAndView deleteBook(@PathParam(value = "id") int id) {
		
		if(repo.findById(id).isPresent()) {
			repo.deleteById(id);
		}
		logger.debug("deleted Book with id: " + id);
		populateModelAndView();
		return modelandview;
	}
	
	private void populateModelAndView() {

		modelandview = new ModelAndView("sideCarBooks.jsp");
		Iterable<SideCarBook> sideCarBooks = repo.findAll();
		modelandview.addObject("sideCarBooks",sideCarBooks);
	}
	

}
