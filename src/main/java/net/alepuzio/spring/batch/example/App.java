package net.alepuzio.spring.batch.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {
	
	private static final Logger logger = LoggerFactory.getLogger("net.alepuzio.spring.batch.example.App");

	public static void main(String[] args) {

		String[] springConfig = { "spring/batch/jobs/job-batch-demo.xml" };
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("DemoJobXMLWriter");
		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.toJobParameters();
		JobExecution execution = null;
		try {
			execution = jobLauncher.run(job, jobParameters);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("Exit Status : " + execution.getStatus());
		context.close();
	}
}
