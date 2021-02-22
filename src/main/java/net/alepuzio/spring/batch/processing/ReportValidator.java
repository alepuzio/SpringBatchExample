package net.alepuzio.spring.batch.processing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

import net.alepuzio.spring.model.Report;

public class ReportValidator implements Validator<Report> {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void validate(Report value) throws ValidationException {
		if(!value.getFirstName().startsWith("A")){
			String msg = String.format("First names with no A like %s are invalid", value.getFirstName());
			//throw new ValidationException(msg);
			log.error(msg);
		}
	}

}
