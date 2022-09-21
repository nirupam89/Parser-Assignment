package com.unlimint.orders.parser.job;

import java.io.File;
import java.util.List;

import com.unlimint.orders.parser.model.Order;

public interface DataParser {

	List<Order> parse(String fileName);

	List<Order> parse(File inputFile);
}
