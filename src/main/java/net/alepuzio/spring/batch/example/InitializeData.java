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
		//dropTableDomain();
		
	//	domainTable();
		
		domainData();
	}

	private void domainTable() {
		ResourceDatabasePopulator resourceDatabasePopulator = 
				resourceDatabasePopulatorFailFirstNotIgnoreFailesDropUTF8("/script/db/2-create-db.sql");
		resourceDatabasePopulator.execute(dataSource);
	}

	private void dropTableDomain() {
		ResourceDatabasePopulator resourceDatabasePopulator = 
				resourceDatabasePopulatorFailFirstNotIgnoreFailesDropUTF8("/script/db/1-delete-db.sql");
		resourceDatabasePopulator.execute(dataSource);
	}

	private void domainData() {
		ResourceDatabasePopulator resourceDatabasePopulator = 
				resourceDatabasePopulatorFailFirstNotIgnoreFailesDropUTF8("/script/db/3-create-db-report.sql");
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