package com.localme.api.dao;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import com.localme.api.vo.Users;

@RepositoryRestResource
public interface UsersRepo extends CrudRepository<Users, Integer> {
	
	Users findByUsername(String username);
}
