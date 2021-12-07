package com.localme.api.vo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.localme.api.dao.CategoryRepo;
import com.localme.api.dao.GameRepo;
import com.localme.api.exception.BusinessException;
import com.localme.api.service.GameService;
import com.localme.api.vo.Category;
import com.localme.api.vo.GameDetail;
import com.localme.api.vo.GameList;


@Service
public class GameImpl implements GameService{
	
	@Autowired
	private CategoryRepo catRepo;
	
	@Autowired
	private GameRepo gameRepo;

	
	 @Override
	 public List<GameDetail> findGamesForCategory(String name) {
		 System.out.println("name"+name);
	   Category category = catRepo.findByName(name).orElse(null);
	   return category.getGames();
	   }
	 
	 @Override
		public GameDetail addGame(GameDetail gamedetail) {
		 
			try {
				GameDetail gameDet=gameRepo.findByname(gamedetail.getName());
				
				if(gameDet!=null)
				{
					throw new BusinessException("400","Already Exisit");
				}
				
				GameDetail savedEmployee = gameRepo.save(gamedetail);
				return savedEmployee;
			}
			catch (Exception e) {
				throw new BusinessException("603","Something went wrong in Service layer while saving the employee");
			}
			

	 }
	 
	 @Override
		public GameDetail getGame(GameList gamedetails) {

			try {
				GameDetail gameDet=gameRepo.findByname(gamedetails.getName());
				
				if(gameDet==null)
				{
					throw new BusinessException("405","Not Present");
				}
				GameDetail savedEmployees=gameRepo.findByname(gamedetails.getName());
				return savedEmployees;
				
			}
			catch (Exception e) {
				throw new BusinessException("603","Something went wrong in Service layer while saving the employee");
			}
			
	 }		
			
	 	
	 	 
}

