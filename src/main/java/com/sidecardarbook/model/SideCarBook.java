package com.sidecardarbook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SideCarBook {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int year;
	private String author;
	private String category;
	private String name;
	private int numberofpages;	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}



	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getNumberofpages() {
		return numberofpages;
	}

	public void setNumberofpages(int numberofpages) {
		this.numberofpages = numberofpages;
	}


	
}
