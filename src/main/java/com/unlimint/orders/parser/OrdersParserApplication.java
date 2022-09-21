package com.unlimint.orders.parser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.unlimint.orders.parser.job.ProcessFileJob;

@SpringBootApplication
public class OrdersParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersParserApplication.class, args);

		ExecutorService fixedPool = Executors.newFixedThreadPool(args.length);

		for (String file : args) {
			fixedPool.submit(new ProcessFileJob(file));
		}
	}

}
