package com.example.loggingmongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggingAppenderTests {
	private static final Logger logger =  LoggerFactory.getLogger(LoggingAppenderTests.class);

	@Test
	public void contextLoads() {
		test();
	}

	private void test(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		final int threadNum= 5;
		final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
		long st = System.currentTimeMillis();
		logger.info("Start.");
		for (int i = 0; i < threadNum; i++) {
			CompletableFuture.runAsync(() -> {
				for(int j = 0; j < 1000; j++) {
					logger.info("uuid > {}", UUID.randomUUID().toString());
				}
				countDownLatch.countDown();
			});
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long et = System.currentTimeMillis();
		logger.info("End, cost={} ms", et - st);
	}

}
