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
public class JsonParserTest {

	@Autowired
	private ParserFactory parserFactory;

	@Test
	void parseTest() {
		URL url = Thread.currentThread().getContextClassLoader().getResource("orders2.json");
		File file = new File(url.getPath());

		List<Order> orders = parserFactory.getParser("json").parse(file);
		orders.forEach(order -> order.setFilename(null));

		assertEquals(
				"[{\"id\":3,\"orderId\":3,\"amount\":1.23,\"currency\":\"USD\",\"comment\":\"order payment\",\"line\":1,\"result\":\"OK\"}, {\"id\":4,\"orderId\":4,\"amount\":1.24,\"currency\":\"EUR\",\"comment\":\"order payment\",\"line\":2,\"result\":\"OK\"}]",
				orders.toString());
	}

}
