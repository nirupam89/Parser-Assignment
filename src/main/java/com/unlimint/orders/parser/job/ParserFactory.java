package com.unlimint.orders.parser.job;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParserFactory {

	@Autowired
	private JsonParser jsonParser;
	
	@Autowired
	private CsvParser csvParser;
	
	public DataParser getParser(String fileType) {
		if (StringUtils.isEmpty(fileType))
			return null;
		switch (fileType) {
		case "json":
		case "JSON":
			return jsonParser;
		
		case "csv":
		case "CSV":
			return csvParser;
		
		default:
			throw new IllegalArgumentException("Unknown file type " + fileType);
		}
	}
}
