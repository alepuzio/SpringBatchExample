package net.alepuzio.spring.batch.database;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

class Connection {

	private Logger log;

	Connection(Environment newEnv) {
		super();
		this.env = newEnv;
		this.log = LoggerFactory.getLogger(this.getClass());
	}
	
	DataSource datasource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
		return dataSource;
	}

	private final Environment env;

}