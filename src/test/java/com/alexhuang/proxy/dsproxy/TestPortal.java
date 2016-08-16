package com.alexhuang.proxy.dsproxy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:dsproxy.xml")
public class TestPortal {

	private @Autowired TestBiz testBiz;

	@Test
	public void test() throws Exception {
		ExecutorService es = Executors.newFixedThreadPool(10);
		int c = 2;
		CountDownLatch cdl = new CountDownLatch(c);

		for (int i = 0; i < c; i++) {
			final int x = i;
			es.execute(() -> {
				String cityCode = "city" + x;
				TestCityCodeHolder.set(cityCode);
				System.out.println("[TestPortal.test]Thread id is "
						+ Thread.currentThread().getId() + ", CityCode is "
						+ cityCode);
				testBiz.test();
				cdl.countDown();
			});
		}

		cdl.await();
		es.shutdown();
	}

}
