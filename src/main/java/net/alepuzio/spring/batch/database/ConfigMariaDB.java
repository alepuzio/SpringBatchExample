package net.alepuzio.spring.batch.database;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import net.alepuzio.spring.batch.example.ConfigDatabase;

public class ConfigMariaDB implements ConfigDatabase {

	private Environment env;
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Bean
	public DataSource batchDataSource() {
		log.info("eseguito batchDataSource");
		return datasource();
	}
	
	public ConfigMariaDB(Environment newEnv){
		this.env = newEnv;
	}

	private DataSource datasource() {
		return new Connection(env).datasource();
	}

}

