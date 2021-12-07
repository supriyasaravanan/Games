package com.localme.api.vo;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name="category")
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<GameDetail> games;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category() {
		
	}
	public Category( String name) {
		this.name = name;
	}
	
	
	 public List<GameDetail> getGames() {
		return games;
	}
	public void setGames(List<GameDetail> games) {
		this.games = games;
	}
	/*public void addGameToCategory(GameDetail gameDetail) {
			if (getGames() == null) {
			this.games = new ArrayList<>();
		 }
			getGames().add(gameDetail);
			gameDetail.setCategory(this);
		 }
	*/
}
