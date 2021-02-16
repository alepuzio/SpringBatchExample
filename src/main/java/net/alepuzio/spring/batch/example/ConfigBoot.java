package net.alepuzio.spring.batch.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ConfigBoot {
	     
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;
	
}
