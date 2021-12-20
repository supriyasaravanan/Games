package com.localme.api.service;

import java.util.List;
import com.localme.api.exception.BusinessException;
import com.localme.api.vo.Category;
import com.localme.api.vo.GameDetail;
import com.localme.api.vo.GameList;

public interface GameService {

	//GameDetail addGameToCategory(GameDetail gameDetail, String name);
	
	public GameDetail addGame(GameDetail gamedetail) throws BusinessException;
	public Category addCat(Category gamedetail) throws BusinessException;
	public GameDetail getGame(GameList gamedetails) throws BusinessException;
    public List<GameDetail> findGamesForCategory(int id) throws BusinessException;
    Iterable<GameDetail> findAllGames();
    public GameDetail update(GameDetail gamedetail);
}
