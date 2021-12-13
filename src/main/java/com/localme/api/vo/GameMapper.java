package com.localme.api.vo;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GameMapper implements RowMapper<Category> {

	public Category mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		Category Category = new Category();
		Category.setId(rs.getInt("id"));
		Category.setName(rs.getString("name"));
		
		return Category;
	}
}
