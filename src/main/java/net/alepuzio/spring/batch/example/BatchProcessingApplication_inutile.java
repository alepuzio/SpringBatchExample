package net.alepuzio.spring.batch.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchProcessingApplication_inutile {

  public static void main(String[] args) throws Exception {
    System.exit(
    		SpringApplication.exit(
    				SpringApplication.run(
    						BatchProcessingApplication_inutile.class, args)
    				)
    		);
  }
}
