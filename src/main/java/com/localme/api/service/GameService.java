package com.localme.api.service;

import java.util.List;
import com.localme.api.exception.BusinessException;
import com.localme.api.vo.GameDetail;
import com.localme.api.vo.GameList;

public interface GameService {

	//GameDetail addGameToCategory(GameDetail gameDetail, String name);
	
	public GameDetail addGame(GameDetail gamedetail) throws BusinessException;
	public GameDetail getGame(GameList gamedetails) throws BusinessException;
    List<GameDetail> findGamesForCategory(String name);
}
