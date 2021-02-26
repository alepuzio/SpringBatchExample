package net.alepuzio.spring.batch.processing.writer;

import javax.sql.DataSource;
import org.springframework.batch.item.ItemWriter;
import net.alepuzio.spring.model.Report;

public interface DBWriter extends ItemWriter<String> {

	public ItemWriter<? super Report> row(DataSource dataSource);
}
