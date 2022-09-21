package com.unlimint.orders.parser.job;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ProcessFileJob implements Runnable {

	private String fileName;

	private DataParser parser;

	@Override
	public void run() {
		parser.parse(fileName);
	}

}
