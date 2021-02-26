package net.alepuzio.spring.batch.example;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class ConfigDerby {

	private Environment env;
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Bean
	public DataSource batchDataSource() {
		log.info("eseguito batchDataSource");
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
	    resourceDatabasePopulator.addScript(new ClassPathResource("/script/db/2-create-db.sql"));
	    log.info("eseguita creazione tabelle spring abtch");
	    resourceDatabasePopulator.addScript(new ClassPathResource("/script/db/3-create-db-report.sql"));
	    log.info("eseguita creazione tabelle dominio");
	    
		return datasource();
	}

	
	ConfigDerby(Environment newEnv){
		this.env = newEnv;
	}

	private DataSource datasource() {
		return new Connection(env).datasource();
	}

}

class Connection {

	private Logger log;

	Connection(Environment newEnv) {
		super();
		this.env = newEnv;
		this.log = LoggerFactory.getLogger(this.getClass());
	}
	
	DataSource datasource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		log.debug("-------------->"+env.getProperty("spring.datasource.driverClassName"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.user"));
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
		return dataSource;
	}

	private final Environment env;

}