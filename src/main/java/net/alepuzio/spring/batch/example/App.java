package net.alepuzio.spring.batch.example;


import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
/*@PropertySources({
    @PropertySource("./application.properties")
})
*/@PropertySource(value = { "classpath:application.properties"}, ignoreResourceNotFound = false)

@EnableBatchProcessing
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	
}
