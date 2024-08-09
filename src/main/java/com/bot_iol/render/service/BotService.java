package com.bot_iol.render.service;

import java.util.List;

import com.bot_iol.entities.Stock;

public interface BotService {

	boolean isBotRunning();

	void startBot();

	void stopBot();
	
	List<Stock> getStocks();
	
	

}
