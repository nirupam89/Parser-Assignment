package com.unlimint.orders.parser.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.unlimint.orders.parser.enums.Result;
import com.unlimint.orders.parser.model.Order;

@Component
public class JsonParser implements DataParser {

	private static final Logger LOGGER = Logger.getLogger(JsonParser.class.getName());

	@Override
	public List<Order> parse(String fileName) {
		List<Order> orders = new ArrayList<>();
		BufferedReader br = null;
		try {
			Gson gson = new Gson();
			FileReader filereader = new FileReader(fileName);
			br = new BufferedReader(filereader);
			int lineCount = 1;
			String line;
			while ((line = br.readLine()) != null) {
				Order order = new Order();
				try {
					order = gson.fromJson(line, Order.class);
					order.setResult(Result.OK);
				} catch (Exception e) {
					order.setResult(Result.NOT_OK);
				} finally {
					order.setId(order.getOrderId());
					order.setFilename(fileName);
					order.setLine(lineCount++);
					orders.add(order);
				}
			}
			orders.forEach(System.out::println);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					LOGGER.log(Level.SEVERE, e.toString(), e);
				}
			}
		}
		return orders;
	}

	public List<Order> parse(File inputFile) {
		return parse(inputFile.getAbsolutePath());
	}
}
