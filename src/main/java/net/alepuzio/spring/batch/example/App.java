package net.alepuzio.spring.batch.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

//
//	public void run(String... args) throws Exception {
//		App instance = new App();
//
//		instance.info("Start at : "
//				+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTimeInMillis()));
//		String[] springConfig = { "./config/batch/job-batch-demo.xml" };
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
//		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
//		Job job = (Job) context.getBean("DemoJobXMLWriter");
//		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
//				.toJobParameters();
//		JobExecution execution = null;
//		try {
//			execution = jobLauncher.run(job, jobParameters);
//			instance.info(String.format("Job %s v.%s startes at %s ", execution.getId(), execution.getVersion(),
//					execution.getStartTime()));
//		} catch (Exception e) {
//			instance.error(e);
//		} finally {//libero le risorse in ogni caso
//			instance.info(String.format("Job %s v.%s finished at %s with status %s", execution.getId(),
//					execution.getVersion(), execution.getEndTime(), execution.getStatus().name()));
//			context.close();
//		}
//	}
	
	void error(Exception e) {
		this.logger.error(e.getMessage());
		e.printStackTrace();
	}

	void info(String message) {
		this.logger.info(message);
	}	private final Logger logger;

	App() {
		this.logger = LoggerFactory.getLogger("net.alepuzio.spring.batch.example.App");
	}


}
