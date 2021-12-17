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
	 public List<GameDetail> findGamesForCategory(int id) {
		 try {
			 GameDetail gameDet=gameRepo.findByid(id);
			 /*if(gameDet!=null)
				{
					throw new BusinessException("400","Already Exisit");
				}*/
		 System.out.println("id"+id);
	   Category category = catRepo.findByid(id);
	   return category.getGames();
	   }
		 catch (Exception e) {
				throw new BusinessException("603","Something went wrong in Service layer while saving the employee");
			} 
	 }
	 @Override
		public GameDetail addGame(GameDetail gamedetail) {
		 
			try {
				
				if(gamedetail.getName().isEmpty() || gamedetail.getName().length() == 0 )
				{throw new BusinessException("404","Enter valid value");
							
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
				GameDetail gameDet=gameRepo.findByid(gamedetails.getId());
				
				if(gameDet==null)
				{
					throw new BusinessException("404","Not Present");
				}
				GameDetail savedEmployees=gameRepo.findByid(gamedetails.getId());
				return savedEmployees;
				
			}
			catch (Exception e) {
				throw new BusinessException("603","Something went wrong in Service layer while saving the employee");
			}
			
	 }		
	 @Override
		public Category addCat(Category gamedetail) {
		 
			try {
				Category gameDet=catRepo.findByname(gamedetail.getName());
				
				if(gameDet!=null)
				{
					throw new BusinessException("404","Already Exisit");
				}
				
				Category savedEmployee = catRepo.save(gamedetail);
				return savedEmployee;
			}
			catch (Exception e) {
				throw new BusinessException("603","Something went wrong in Service layer while saving the employee");
			}
			

	 }
	 @Override
	 public Iterable<GameDetail> findAllGames() 
	 {
	   return gameRepo.findAll();
	 }
		
}

