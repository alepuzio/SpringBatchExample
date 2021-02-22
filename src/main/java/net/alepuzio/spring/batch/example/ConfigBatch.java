package net.alepuzio.spring.batch.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import net.alepuzio.spring.batch.processing.XMLOutput;
import net.alepuzio.spring.model.Report;
import net.alepuzio.spring.model.Upper;

@Configuration
public class ConfigBatch {

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job(Step step1, 
			Step step2, Step step3) {
		return jobs.get("myJob").start(step1)
				.next(step2)
				.next(step3)
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Report, Report>chunk(10)
				.reader(readCSV())
				.writer(writeXML())
				.build();
	}
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step52")
				.<Report, Report>chunk(10)
				.reader(readCSV())
				.writer(writeXML())
				.build();
	}
	@Bean
	public Step step3() throws Exception {
		return stepBuilderFactory.get("step3")
				.<Report, Report>chunk(2)
				.reader(readCSV())
				.writer(customerItemWriter())
				.build();
	}
	
	

	@Bean
	public ItemWriter<Report> writeXML() {
		int count = 0;
		return items -> {
			for (Report item : items) {
				item.setFirstName(new Upper(item.getFirstName()).value());
				item.setLastName(new Upper(item.getLastName()).value());
				System.out.println(String.format("[%s]>%s",count,item.toString()));
		//		count++; deve essere final
			}
		};
	}

	@Bean
	public FlatFileItemReader<Report> readCSV() {
		FlatFileItemReader<Report> reader = new FlatFileItemReader<Report>();
		reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource("input/csv/report.csv"));
		DefaultLineMapper<Report> customerLineMapper = new DefaultLineMapper<Report>();
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames(new String[] { "Id", "Name", "Surname", "Date" });//inutile se accede a fieldset con posizione e non nome campo
		customerLineMapper.setLineTokenizer(tokenizer);
		customerLineMapper.setFieldSetMapper(new ReportFieldSetMapper());
		customerLineMapper.afterPropertiesSet();
		reader.setLineMapper(customerLineMapper);
		return reader;
	}

	@Bean
	public StaxEventItemWriter<Report> customerItemWriter() throws Exception {
		return new XMLOutput().customerItemWriter();
	}


}
