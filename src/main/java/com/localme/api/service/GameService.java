package com.localme.api.service;

import java.util.List;

import com.localme.api.vo.GameDetail;

public interface GameService {

	//GameDetail addGameToCategory(GameDetail gameDetail, String name);
	
	
	 List<GameDetail> findGamesForCategory(String name);
}
