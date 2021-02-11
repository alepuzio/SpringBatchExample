package net.alepuzio.spring.batch.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import net.alepuzio.spring.model.Report;

public class CustomItemProcessor implements ItemProcessor<Report, Report> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	public Report process(Report item) throws Exception {
		logger.info("Processing..." + item);
		item.setFirstName(new Upper(item.getFirstName()).value());
		item.setLastName(new Upper(item.getLastName()).value());
		return item;
	}

}

class Upper {

	private String origin;

	Upper(String newInput) {
		this.origin = newInput;
	}

	String value() {
		return this.origin.toUpperCase();
	}

}
