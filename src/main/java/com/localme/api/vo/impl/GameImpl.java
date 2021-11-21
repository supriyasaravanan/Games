package com.localme.api.vo.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.localme.api.dao.CategoryRepo;
import com.localme.api.service.GameService;
import com.localme.api.vo.Category;
import com.localme.api.vo.GameDetail;

@Transactional
@Component
public class GameImpl implements GameService{
	
	@Autowired
	private CategoryRepo catRepo;
	
	 @Override
	 public List<GameDetail> findGamesForCategory(String name) {
		 System.out.println("name"+name);
	   Category category = catRepo.findByName(name).orElse(null);
	   return category.getGames();
	   }
}
