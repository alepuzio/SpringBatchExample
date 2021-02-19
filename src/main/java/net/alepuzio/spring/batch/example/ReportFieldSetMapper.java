package net.alepuzio.spring.batch.example;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import net.alepuzio.spring.model.DateFormat;
import net.alepuzio.spring.model.Report;


public class ReportFieldSetMapper implements FieldSetMapper<Report> {
	private DateFormat dateFormat;

	ReportFieldSetMapper() {
		this.dateFormat = new DateFormat("yyyy-MM-dd");/* HH:mm:ss//*/
	}

	@Override
	public Report mapFieldSet(FieldSet fieldSet) throws BindException {
		Report report = new Report();
		businessLogic(fieldSet, report);
		return report;
	}

	private void businessLogic(FieldSet fieldSet, Report report) {
		report.setId(fieldSet.readInt(0));
		report.setFirstName(fieldSet.readString(1));
		report.setLastName(fieldSet.readString(2));
		report.setDob(/*this.dateFormat.ddMMyyyy*/(this.dateFormat.date(fieldSet.readString(3))));
	}

}

