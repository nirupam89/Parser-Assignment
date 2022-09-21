package com.unlimint.orders.parser.job;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;

import com.unlimint.orders.parser.model.Order;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProcessFileJob implements Callable<List<Order>> {

	@Autowired
	private ParserFactory parserFactory;

	private String fileName;

	public ProcessFileJob(String fileName) {

	}

	@Override
	public List<Order> call() {
		DataParser parser = parserFactory.getParser(fileName.split("\\.")[1]);
		return parser.parse(fileName);
	}

}
