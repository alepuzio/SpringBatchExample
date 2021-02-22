package net.alepuzio.spring.batch.processing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import net.alepuzio.spring.model.Report;

public class FilterProcessItem implements ItemProcessor<Report, Report> {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public Report process(Report item) throws Exception {
		Report newItem = null;
		if (0 == ( item.getId()%2)){
			newItem = item;
		} else {
			log.warn(String.format("item non valido", item));
		}
		return newItem;
	}

}
