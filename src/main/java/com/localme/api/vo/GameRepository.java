package com.localme.api.vo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository

public class GameRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public List<Category> findAll()
	{
		return jdbcTemplate.query("select * from category",new GameMapper());
	}

}
