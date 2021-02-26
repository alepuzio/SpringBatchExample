package net.alepuzio.spring.batch.example;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ConfigDerby {

	//@Autowired
	private Environment env;
	private Logger log = LoggerFactory.getLogger(this.getClass());

	//@Qualifier("org.springframework.jdbc.datasource.DriverManagerDataSource")
	@Bean
	public DataSource batchDataSource() {
		log.info("eseguito batchDataSource");
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

	Connection(Environment env) {
		super();
		this.env = env;
	}
	
	DataSource datasource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		System.out.println("-------------->"+env.getProperty("spring.datasource.driverClassName"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.user"));
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
		return dataSource;
	}

	private final Environment env;

}