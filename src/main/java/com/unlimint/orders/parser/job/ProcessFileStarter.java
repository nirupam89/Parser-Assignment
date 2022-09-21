package com.unlimint.orders.parser.job;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class ProcessFileStarter {
	private final ApplicationArguments applicationArguments;

	@Autowired
	private ParserFactory parserFactory;

	public ProcessFileStarter(ApplicationArguments applicationArguments) {
		this.applicationArguments = applicationArguments;
	}

	@PostConstruct
	public void startFileProcessing() {
		if (applicationArguments.getSourceArgs().length > 0) {
			ExecutorService fixedPool = Executors.newFixedThreadPool(applicationArguments.getSourceArgs().length);

			for (String file : applicationArguments.getSourceArgs()) {
				DataParser parser = parserFactory.getParser(file.split("\\.")[1]);
				fixedPool.submit(new ProcessFileJob(file, parser));
			}
		}
	}

}
