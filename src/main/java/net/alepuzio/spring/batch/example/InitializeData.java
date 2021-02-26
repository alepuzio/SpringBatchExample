package net.alepuzio.spring.batch.example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

@Component
public class InitializeData {

	@Autowired
	private DataSource dataSource;

	@EventListener(ApplicationReadyEvent.class)
	public void loadData() {
		
		scriptInitialization("1-create-table-spring-batch.sql");
		scriptInitialization("2-delete-db.sql");
		scriptInitialization("3-create-db-report.sql");
	}

	private void scriptInitialization(String relativePath) {
		ResourceDatabasePopulator resourceDatabasePopulator = 
				resourceDatabasePopulatorFailFirstNotIgnoreFailesDropUTF8(String.format("/script/db/%s",relativePath));
		resourceDatabasePopulator.execute(dataSource);
	}

	private ResourceDatabasePopulator resourceDatabasePopulatorFailFirstNotIgnoreFailesDropUTF8(String pathScript) {
		return new ResourceDatabasePopulator(
				false, 
				false,
				"UTF-8",
				new ClassPathResource(pathScript));
	}
}