package com.localme.api.dao;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.localme.api.vo.UserDetailsVO;


@RepositoryRestResource
public interface UserRepo extends CrudRepository<UserDetailsVO, Integer> {
	
	public UserDetailsVO findByuid(String username);
	
}
