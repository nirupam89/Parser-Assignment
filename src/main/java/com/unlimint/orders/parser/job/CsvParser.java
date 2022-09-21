package com.unlimint.orders.parser.job;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.unlimint.orders.parser.enums.Result;
import com.unlimint.orders.parser.model.Order;

@Component
public class CsvParser implements DataParser {

	private static final Logger LOGGER = Logger.getLogger(CsvParser.class.getName());
	
	@Override
	public List<Order> parse(String fileName) {
		List<Order> orders = new ArrayList<>();
		try {
			FileReader filereader = new FileReader(fileName);
			CSVReader csvReader = new CSVReader(filereader);

			String[] nextRecord;
			int lineCount = 1;
			csvReader.readNext();
			while ((nextRecord = csvReader.readNext()) != null) {
				Order order = new Order();
				try {
					order.setOrderId(Integer.parseInt(nextRecord[0]));
					order.setId(order.getOrderId());
					order.setAmount(new BigDecimal(nextRecord[1]));
					order.setResult(Result.OK);
				} catch (Exception e) {
					order.setResult(Result.NOT_OK);
				} finally {
					order.setCurrency(nextRecord[2]);
					order.setComment(nextRecord[3]);

					order.setFilename(fileName);
					order.setLine(lineCount++);
					orders.add(order);
				}
			}
			orders.forEach(System.out::println);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
		return orders;
	}
	
	@Override
	public List<Order> parse(File inputFile) {
		return parse(inputFile.getAbsolutePath());
	}
}
