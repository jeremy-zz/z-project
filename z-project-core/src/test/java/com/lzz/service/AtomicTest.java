package com.lzz.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.Test;
import sun.rmi.runtime.Log;

/**
 * @version V1.0
 * @Author jeremy.li
 * @Date 2019/3/7
 * @Description TODO
 */
public class AtomicTest {


	public static AtomicInteger race = new AtomicInteger(0);

	public static void increase() {
		race.incrementAndGet();
	}

	private static final int THREAD_COUNT = 20;

	public static void main(String[] args) {
		Thread[] threads = new Thread[THREAD_COUNT];
		for (int i = 0; i < THREAD_COUNT; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int k = 0; k < 1000; k++) {
						increase();
					}
				}
			});
			threads[i].start();
		}
		while (Thread.activeCount() > 2) {
			//System.out.println(Thread.activeCount());
			Thread.yield();
		}

		System.out.println(race);
	}

	ThreadLocal threadLocal = new ThreadLocal();

	@Test
	public void ss() {
		Map<String, Object> map = new HashMap();
		map.put("string", "test");
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		map.put("integer", list);
		System.out.println(map.toString());
		String st = "{seq_no='CL19030814134034254543846', loan_id=nul\n"
			+ "l, state='0000', msg='还款成功', application_id='19030813423140714629806', pay_code='allinpay', repayDetails={1={\"principal\":934.00,\"insurance_fee\"\n"
			+ ":1.00,\"interest\":65.00}}, userId=null, source='other', loanState='closed'}";
		Map m1 = JSON.parseObject(st, Map.class);
		System.out.println(m1.get("seq_no"));
	}
}
