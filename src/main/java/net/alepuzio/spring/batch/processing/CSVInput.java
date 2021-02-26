package net.alepuzio.spring.batch.processing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import net.alepuzio.spring.batch.processing.mapper.ReportFieldSetMapper;
import net.alepuzio.spring.model.Report;

public class CSVInput {
	
	private  Logger log = LoggerFactory.getLogger(this.getClass());
	private final String fileName = "input/csv/report.csv";
	private final String[] columnNames = new String[] { "Id", "Name", "Surname", "Date" };
	
	public FlatFileItemReader<Report> customers() {
		DefaultLineMapper<Report> customerLineMapper = lineMapper();
		FlatFileItemReader<Report> reader = new FlatFileItemReader<Report>();
		reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource(fileName));
		reader.setLineMapper(customerLineMapper);
		return reader;
	}

	private DefaultLineMapper<Report> lineMapper() {
		DelimitedLineTokenizer tokenizer = lineTokenizer();
		DefaultLineMapper<Report> customerLineMapper = new DefaultLineMapper<Report>();
		customerLineMapper.setLineTokenizer(tokenizer);
		customerLineMapper.setFieldSetMapper(new ReportFieldSetMapper());
		customerLineMapper.afterPropertiesSet();
		return customerLineMapper;
	}

	private DelimitedLineTokenizer lineTokenizer() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames(columnNames);//inutile se accede a fieldset con posizione e non nome campo
		return tokenizer;
	}

}
