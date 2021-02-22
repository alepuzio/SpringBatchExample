package net.alepuzio.spring.batch.processing;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import net.alepuzio.spring.model.Report;

public class XMLOutput {

	private  Logger log = LoggerFactory.getLogger(this.getClass());
	private final String root = "customers";
	private final String fileName = ".\\outputs\\xml\\customerOutput.xml";
	
	@Bean
	public StaxEventItemWriter<Report> customerItemWriter() throws Exception {
		StaxEventItemWriter<Report> itemWriter = new StaxEventItemWriter<>();
		itemWriter.setRootTagName(this.root);
		itemWriter.setMarshaller(marshaller());
		itemWriter.setResource(new FileSystemResource(createFile()));
		itemWriter.afterPropertiesSet();
		return itemWriter;
	}

	private XStreamMarshaller marshaller() {
		XStreamMarshaller marshaller = new XStreamMarshaller();
		marshaller.setAliases( aliases());
		return marshaller;
	}

	@SuppressWarnings("rawtypes")
	private Map<String, Class> aliases() {
		Map<String, Class> aliases = new HashMap<>();
		aliases.put(this.root, Report.class);
		return aliases;
	}

	private String createFile() throws IOException {
		File outputFile = new File(this.fileName);
		outputFile.createNewFile();
		String customerOutputPath = outputFile.getAbsolutePath();
		log.info(String.format(">> Output Path: %s",customerOutputPath));
		return customerOutputPath;
	}


}
