package com.innovation;

import java.text.ParseException;

import org.quartz.SchedulerException;

import com.innovation.mbean.SimpleAgent;
import com.innovation.scheduler.MailScheduler;

public class Runner {

	public static void main(String[] args) throws ParseException, SchedulerException, InterruptedException {
		
		
		SimpleAgent agent = new SimpleAgent();
		
		//Thread.sleep(Integer.MAX_VALUE);
		MailScheduler mailScheduler = new MailScheduler();
		mailScheduler.trigger();
		
	}
}
