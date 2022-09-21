package com.unlimint.orders.parser.job;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.unlimint.orders.parser.model.Order;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CsvParserTest {

	@Autowired
	private ParserFactory parserFactory;

	@Test
	void parseTest() {
		URL url = Thread.currentThread().getContextClassLoader().getResource("orders1.csv");
		File file = new File(url.getPath());

		List<Order> orders = parserFactory.getParser("csv").parse(file);
		orders.forEach(order -> order.setFilename(null));

		assertEquals(
				"[{\"id\":1,\"orderId\":1,\"amount\":100,\"currency\":\"USD\",\"comment\":\"order payment\",\"line\":1,\"result\":\"OK\"}, {\"id\":2,\"orderId\":2,\"amount\":123,\"currency\":\"EUR\",\"comment\":\"order payment\",\"line\":2,\"result\":\"OK\"}]",
				orders.toString());
	}

}
