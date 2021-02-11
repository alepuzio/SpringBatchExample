package net.alepuzio.spring.batch.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import net.alepuzio.spring.model.Report;

public class ReportFieldSetMapper implements FieldSetMapper<Report> {
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public Report mapFieldSet(FieldSet fieldSet) throws BindException {
		Report report = new Report();
		report.setId(fieldSet.readInt(0));
		report.setFirstName(fieldSet.readString(1));
		report.setLastName(fieldSet.readString(2));

		// default format yyyy-MM-dd
		String date = fieldSet.readString(3);
		try {
			report.setDob(dateFormat.parse(date));
		} catch (ParseException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return report;
	}

}