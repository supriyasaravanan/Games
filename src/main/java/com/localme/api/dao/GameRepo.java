package com.localme.api.dao;


import java.io.Serializable;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.localme.api.vo.GameDetail;

@RepositoryRestResource
public interface GameRepo extends  CrudRepository<GameDetail, Integer> {
	public GameDetail findByname(String name);
	public GameDetail findByid(String name);
	
	List<GameDetail> findByName(String name);
	
}
