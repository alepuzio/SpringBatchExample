package net.alepuzio.spring.batch.example;


import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
	@PropertySource(value = { "classpath:application.properties"}, ignoreResourceNotFound = false),
//	@PropertySource(value = { "classpath:script/db/1-delete-db.sql"}, ignoreResourceNotFound = false),
//	@PropertySource(value = { "classpath:script/db/2-create-db.sql"}, ignoreResourceNotFound = false),
//	@PropertySource(value = { "classpath:script/db/3-create-db-report.sql"}, ignoreResourceNotFound = false)
//	@PropertySource(value = { "classpath:config/logback.xml"}, ignoreResourceNotFound = false)
})
//@PropertySource(value = { "classpath:application.properties"}, ignoreResourceNotFound = false)

@EnableBatchProcessing
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	
}
