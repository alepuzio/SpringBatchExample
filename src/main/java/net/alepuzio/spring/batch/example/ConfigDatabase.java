package net.alepuzio.spring.batch.example;

import javax.sql.DataSource;

public interface ConfigDatabase {
	public DataSource batchDataSource() ;
}


