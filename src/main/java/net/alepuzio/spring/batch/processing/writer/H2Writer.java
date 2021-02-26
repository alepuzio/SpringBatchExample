package net.alepuzio.spring.batch.processing.writer;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import net.alepuzio.spring.model.Report;

public class H2Writer implements DBWriter {

	private final String sql = "INSERT INTO REPORT VALUES(:id, :firstName, :lastName, :dob )";
	
	//TODO provare a dichiarare qui il datasource
	
	public ItemWriter<? super Report> row(DataSource dataSource){
		JdbcBatchItemWriter<Report> itemWriter = new JdbcBatchItemWriter<Report>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql(sql);
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		itemWriter.afterPropertiesSet();
		return itemWriter;
	}

	@Override
	public void write(List<? extends String> items) throws Exception {
		
	}
}
