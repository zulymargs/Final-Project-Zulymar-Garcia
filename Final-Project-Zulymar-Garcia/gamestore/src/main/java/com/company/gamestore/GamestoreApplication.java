/**-------------------------------------------------------------------------
					 Final Project: Game Store Server
 -Back-end API (via REST and GraphQL) inventory management web
 service for a video game store.

Authors:
 Ashley Chompre, Oriyomi Oluwaseun Adeliyi, and Zulymar García Sonera

 --------------------------------------------------------------------------*/


package com.company.gamestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(scanBasePackages={
//		"com.company.gamestore"})
@SpringBootApplication
public class GamestoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamestoreApplication.class, args);
	}


}
