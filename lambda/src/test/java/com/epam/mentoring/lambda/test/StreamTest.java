package com.epam.mentoring.lambda.test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.junit.Test;

public class StreamTest {

	private static final Logger logger = Logger.getLogger(StreamTest.class);

	@Test
	public void testPerfomance_sequential() {
		Random rand = new Random();

		List<String> list = Stream
				.generate(() -> String.valueOf(rand.nextInt())).limit(1000000)
				.collect(Collectors.toList());

		long t0 = System.currentTimeMillis();

		list.stream().sorted().count();

		long t1 = System.currentTimeMillis();

		logger.info(String.format("Sequential sort took: %d ms", t1 - t0));
	}

	@Test
	public void testPerfomance_parallel() {
		Random rand = new Random();

		List<String> list = Stream
				.generate(() -> String.valueOf(rand.nextInt())).limit(1000000)
				.collect(Collectors.toList());

		long t0 = System.currentTimeMillis();

		list.parallelStream().sorted().count();

		long t1 = System.currentTimeMillis();

		logger.info(String.format("Parallel sort took: %d ms", t1 - t0));
	}

	@Test
	public void testPerfomance_customThreadPool() throws InterruptedException,
			ExecutionException {
		int level = 1;

		Random rand = new Random();

		List<String> list = Stream
				.generate(() -> String.valueOf(rand.nextInt())).limit(1000000)
				.collect(Collectors.toList());

		for (int i = 1; i < 8; i++) {
			level *= i;
			long t0 = System.currentTimeMillis();

			ForkJoinPool customThreadPool = new ForkJoinPool(level);
			customThreadPool.submit(
					() -> list.parallelStream().sorted().count()).get();

			long t1 = System.currentTimeMillis();

			logger.info(String.format(
					"CustomThreadPool (%d) parallel sort took: %d ms", level,
					t1 - t0));
		}
	}
}
