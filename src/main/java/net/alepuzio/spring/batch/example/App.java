package net.alepuzio.spring.batch.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class App {

	private final Logger logger;
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Scheduled(cron = "0 */1 * * * ?")
	public void perform() throws Exception {
		JobParameters params = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(job, params);
	}

	App() {
		this.logger = LoggerFactory.getLogger("net.alepuzio.spring.batch.example.App");
	}

	/*
	 * public static void main(String[] args) { App instance = new App();
	 * 
	 * instance.info("Start at : " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"
	 * ).format(Calendar.getInstance().getTimeInMillis())); String[]
	 * springConfig = { "spring/batch/jobs/job-batch-demo.xml" };
	 * ClassPathXmlApplicationContext context = new
	 * ClassPathXmlApplicationContext(springConfig); JobLauncher jobLauncher =
	 * (JobLauncher) context.getBean("jobLauncher"); Job job = (Job)
	 * context.getBean("DemoJobXMLWriter"); JobParameters jobParameters = new
	 * JobParametersBuilder().addLong("time", System.currentTimeMillis())
	 * .toJobParameters(); JobExecution execution = null; try { execution =
	 * jobLauncher.run(job, jobParameters); instance.info(String.format(
	 * "Job %s v.%s startes at %s ", execution.getId(), execution.getVersion(),
	 * execution.getStartTime())); } catch (Exception e) { instance.error(e); }
	 * finally {//libero le risorse in ogni caso instance.info(String.format(
	 * "Job %s v.%s finished at %s with status %s", execution.getId(),
	 * execution.getVersion(), execution.getEndTime(),
	 * execution.getStatus().name())); context.close(); } }
	 */
	void error(Exception e) {
		this.logger.error(e.getMessage());
		e.printStackTrace();
	}

	void info(String message) {
		this.logger.info(message);
	}

}
