package com.bot_iol.render.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot_iol.config.ScheduledTasks;
import com.bot_iol.entities.Bot;
import com.bot_iol.entities.Stock;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BotServiceImpl implements BotService {

	@Autowired
	private Bot bot;

	public void startBot() {
		if (!bot.isRunning()) {
			bot.setRunning(true);
		} else {
			log.info("... El bot ya está en ejecución");
		}

		log.info("... Realizando WebScraping");
		bot.performTask();

	}

	public void stopBot() {
		if (bot.isRunning()) {
			bot.stop();
			log.info("... El bot se ha detenido");
		} else {
			log.info("... El bot no está en ejecución");
		}
	}

	public List<Stock> getStocks() {
		return bot.getStocks();
	}

	public boolean isBotRunning() {
		return bot.isRunning();
	}
}
