package com.bot_iol.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bot_iol.config.ScheduledTasks;
import com.bot_iol.entities.Stock;
import com.bot_iol.render.service.BotService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/bot")
@Slf4j
public class BotController {

	@Autowired
    private BotService botService;
	


    @GetMapping("/start")
    public String startBot() {
        botService.startBot();
        return "El bot se inicializo";
    }

    @GetMapping("/status")
    public String getStatus() {
        return botService.isBotRunning() ? "El bot esta encendido." : "El bot esta apagado.";
    }

    @GetMapping("/stop")
    public @ResponseBody String stopBot() {
    String respuesta = "";
 
    if(botService.isBotRunning()) {
    	 botService.stopBot();
    	 respuesta = "Se ha detenido el bot";
    	 
     } else {
    	 respuesta = "El bot esta apagado.";
     }
        return respuesta;
    }
    
    @GetMapping("/getStocks")
    public List<Stock> getStocks() {
    	List<Stock> stocks = botService.getStocks();
    	Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    	log.info("Se han enviado {} stocks el dia {} a las {}", stocks.size(), dateFormat.format(now), timeFormat.format(now));
        return botService.getStocks();
    } 
    
};