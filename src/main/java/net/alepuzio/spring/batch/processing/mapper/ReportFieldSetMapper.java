package net.alepuzio.spring.batch.processing.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import net.alepuzio.spring.model.Report;


public class ReportFieldSetMapper implements FieldSetMapper<Report> {

	public ReportFieldSetMapper() {
	}

	@Override
	public Report mapFieldSet(FieldSet fieldSet) throws BindException {
		Report report = new Report();
		businessLogic(fieldSet, report);
		return report;
	}

	private void businessLogic(FieldSet fieldSet, Report report) {
		report.setId(fieldSet.readInt("Id"));
		report.setFirstName(fieldSet.readString("Name"));
		report.setLastName(fieldSet.readString("Surname"));
		report.setDob(fieldSet.readDate("Date","yyyy-MM-dd"));
	}

	/*altro modo per settare i bean
	 * 
	 * 	report.setId(fieldSet.readInt(0));
	 * report.setFirstName(fieldSet.readString(1));
	 * report.setLastName(fieldSet.readString(2));
	 * report.setDob(this.dateFormat.date(fieldSet.readString(3)));
	 * log.info(String.format("businessLogic[%s]", fieldSet.readInt("Id")));
	 * this.dateFormat.date(fieldSet.readString(3))
*/
}

