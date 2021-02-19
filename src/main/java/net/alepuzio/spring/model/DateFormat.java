package net.alepuzio.spring.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateFormat {
	private final Logger logger;
	private SimpleDateFormat format;

	public DateFormat(String newFormat) {
		this.logger = LoggerFactory.getLogger(this.getClass().getName());
		this.format = new SimpleDateFormat(newFormat);
	}

	public Date date(String newDate) {
		Date result = null;
		try {
			result = this.format.parse(newDate);
		} catch (ParseException e) {
			logger.error(String.format("Error in date(%s):%s", newDate, e.getMessage()));
			e.printStackTrace();
		}
		return result;
	}
	
	public String ddMMyyyy(Date newDate) {
		String result = null;
		try {
			result = this.format.format(newDate);
		} catch (Exception e) {
			logger.error(String.format("Error in date(%s):%s", newDate, e.getMessage()));
			e.printStackTrace();
		}
		return result;
	}
}