package net.alepuzio.spring.batch.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.alepuzio.spring.model.Report;

@Configuration
@EnableBatchProcessing
// @Import(DataSourceConfiguration.class)
public class ConfigBatch {

//	@Autowired
//	private JobBuilderFactory jobs;
//
//	@Autowired
//	private StepBuilderFactory steps;
//
//	@Bean
//	public Job job(@Qualifier("readCSV") Step step1, @Qualifier("writeXML") Step step2) {
//		return jobs.get("myJob").start(step1).next(step2).build();
//	}
//
//	@Bean
//	protected Step readCSV(ItemReader<Report> reader,
//			ItemProcessor<Report, Report> processor/*
//													 * , ItemWriter<Report>
//													 * writer
//													 */) {
//		return steps.get("step1").<Report, Report> chunk(10).reader(reader).processor(processor)
//				/* .writer(writer) */
//				.build();
//	}
//
//	@Bean
//	protected Step writeXML(Tasklet tasklet) {
//		return steps.get("step2").tasklet(tasklet).build();
//	}

}
