package com.bot_iol.config;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bot_iol.controller.BotController;
import com.bot_iol.render.service.BotService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTasks {

    private final BotService botService;
    private static final long FIXED_RATE_MS = 1200000; // 20 minutos en milisegundos

    public ScheduledTasks(BotService botService) {
        this.botService = botService;
    }

    @Scheduled(fixedRate = FIXED_RATE_MS)
    public void performScheduledTask() {
        if (botService.isBotRunning()) {
        	Date now = new Date();
        	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        	log.info("... Tarea programada: Actualizando las cotizaciones a las {}", timeFormat.format(now));
            botService.startBot();
        } else {
            log.info("... Tarea programada: El bot esta apagado, enciendalo con el endpoint api/bot/start");
        }
    }


}