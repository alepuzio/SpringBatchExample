package net.alepuzio.spring.batch.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import net.alepuzio.spring.model.Report;

public class CustomItemProcessor implements ItemProcessor<Report, Report> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	public Report process(Report item) throws Exception {
		logger.info("Processing..." + item);
		String fname = item.getFirstName();
		String lname = item.getLastName();
		item.setFirstName(fname.toUpperCase());
		item.setLastName(lname.toUpperCase());
		return item;
	}

}