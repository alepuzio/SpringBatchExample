package net.alepuzio.spring.batch.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import net.alepuzio.spring.model.Report;

public class ReportFieldSetMapper implements FieldSetMapper<Report> {

	private DateFormat dateFormat;

	ReportFieldSetMapper() {
		this.dateFormat = new DateFormat("dd/MM/yyyy");
	}

	public Report mapFieldSet(FieldSet fieldSet) throws BindException {
		Report report = new Report();
		report.setId(fieldSet.readInt(0));
		report.setFirstName(fieldSet.readString(1));
		report.setLastName(fieldSet.readString(2));
		report.setDob(this.dateFormat.date(fieldSet.readString(3)));
		return report;
	}

}

class DateFormat {
	private final Logger logger;
	private SimpleDateFormat format;

	DateFormat(String newFormat) {
		this.logger = LoggerFactory.getLogger(this.getClass().getName());
		this.format = new SimpleDateFormat(newFormat);
	}

	Date date(String newDate) {
		Date result = null;
		try {
			result = this.format.parse(newDate);
		} catch (ParseException e) {
			logger.error(String.format("Error in date(%s):%s", newDate, e.getMessage()));
			e.printStackTrace();
		}
		return result;
	}
}