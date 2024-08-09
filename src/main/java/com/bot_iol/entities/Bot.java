package com.bot_iol.entities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
@Slf4j
public class Bot {
	
	private WebDriver driver;
	private List<Stock> stocks;
	private boolean isRunning = false;
	
	public void performTask() {
	    try {
	        inicializarBot();
	        search();
	    }catch (Exception e) {
			System.out.println("Ocurrio un error: " + e.getMessage());
		}
	}
	
	private void inicializarBot() {
        //System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        this.driver = new ChromeDriver();
	}
	
    public void stop() {
        this.setRunning(false);
    }
    
	public void sleep(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		}catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void scrollPage(int howMany, int scrollVolume) {
		JavascriptExecutor js =(JavascriptExecutor ) this.driver;
		for(int i = 0; i< howMany; i ++) {
			js.executeScript(String.format("window.scrollBy(0,%s)", scrollVolume));
		}
		
	}
	
	
	public void search() {
		this.driver.get("https://iol.invertironline.com/mercado/cotizaciones/argentina/acciones/panel-general");
		sleep(7);
		scrollPage(3, 2000);
	    try {
		List<WebElement> elementosHTML = driver.findElements(By.xpath("//*[@id=\"cotizaciones\"]/tbody/tr"));
		this.getStocks(elementosHTML);
	    } finally {
            driver.quit();
        }
	}
	
	public void getStocks(List<WebElement> elementos) {
		
		   
		List<Stock> stocks =  new ArrayList<Stock>();
		
		for(WebElement s : elementos) {
			Stock info = new Stock();
			info.setTicker(s.findElement(By.xpath(".//td[1]/a/b")).getText());
			info.setPrice(s.findElement(By.xpath(".//td[2]")).getText());
			info.setDescription(s.findElement(By.xpath(".//td[1]/a/span")).getText());
			WebElement linkElement =s.findElement(By.xpath(".//td[1]/a"));
			info.setUrl(linkElement.getAttribute("href"));
			stocks.add(info);			
		}
		log.info("... Insertando las cotizaciones en el bot");
		this.setStocks(stocks);
	}
	
}
