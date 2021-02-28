package net.alepuzio.spring.batch.example;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import net.alepuzio.spring.batch.database.ConfigMariaDB;
import net.alepuzio.spring.batch.processing.CSVInput;
import net.alepuzio.spring.batch.processing.FilterProcessItem;
import net.alepuzio.spring.batch.processing.ReportValidator;
import net.alepuzio.spring.batch.processing.writer.MariaDbWriter;
import net.alepuzio.spring.batch.processing.writer.XMLOutput;
import net.alepuzio.spring.model.Report;
import net.alepuzio.spring.model.Upper;

@Configuration
public class ConfigBatch {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired//TODO spostare in configMariaDb
	private DataSource dataSource;

	@Bean
    public DataSource dataSource(Environment environment) {
        return new ConfigMariaDB(environment).batchDataSource();
    }

	@Bean
	public Job job(Step step1, Step step2, Step step3, Step step4) {
		return jobs.get("myJob")
				.start(step1)
				/*
				 * .next(step2) .next(step3)
				 */
				.next(step4).build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Report, Report> chunk(10).reader(readCSV()).processor(iterProcessor())
				.writer(writeXML("first")).build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step52").<Report, Report> chunk(10).reader(readCSV()).writer(writeXML("second"))
				.build();
	}

	@Bean
	public Step step3() throws Exception {
		return stepBuilderFactory.get("step3").<Report, Report> chunk(2).reader(readCSV()).processor(itemValidator())
				.writer(customerItemWriter("third")).build();
	}

	@Bean
	public Step step4() throws Exception {
		return stepBuilderFactory.get("step4").
				<Report, Report> chunk(5)
				.reader(readCSV())
				.processor(itemValidator())
				.writer(mariaDbWriter())
				.build();
	}

	@Bean
	public ItemWriter<Report> writeXML(String numberFile) {
		int count = 0;
		return items -> {
			for (Report item : items) {
				Report newItem = new Report();
				newItem.setId(item.getId());
				newItem.setFirstName(new Upper(item.getFirstName()).value());
				newItem.setLastName(new Upper(item.getLastName()).value());
				// System.out.println(String.format("[%s]>%s",count,item.toString()));
				log.info(String.format("[%s]>%s", count, item.toString()));
				// count++; deve essere final
			}
		};
	}

	@Bean
	public FilterProcessItem iterProcessor() {
		return new FilterProcessItem();
	}

	@Bean
	public ValidatingItemProcessor<Report> itemValidator() {
		ValidatingItemProcessor<Report> reportValidatingItem = new ValidatingItemProcessor<>(new ReportValidator());
		reportValidatingItem.setFilter(true);
		return reportValidatingItem;
	}

	@Bean
	public FlatFileItemReader<Report> readCSV() {
		return new CSVInput().customers();
	}

	@Bean
	public StaxEventItemWriter<Report> customerItemWriter(String numberFile) throws Exception {
		return new XMLOutput().customerItemWriter(numberFile);
	}

	@Bean
	public ItemWriter<? super Report> mariaDbWriter() throws Exception {
		log.info("Scrittura su db");
		return new MariaDbWriter().row(this.dataSource);
	}

}
