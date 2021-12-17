package com.localme.api.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="game_lists")
public class GameDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	String name;
	String description;
	String url;
	//String cat_id;

	
	@ManyToOne
	@JsonBackReference(value = "category")
	@JoinColumn(name="cat_id")
	 private Category category;

	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	public GameDetail( String name, String description, String url, Category category) {
		this.name = name;
		this.description = description;
		this.url = url;
		this.category = category;
		
		
	}
	public GameDetail() {
		
	}
	@Override
	public String toString() {
		return "gameDetail [id=" + id + ", name=" + name + ", description=" + description + ", url=" + url
				+ ", category=" + category + "]";
	}
	
	

}
