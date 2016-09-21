package com.innovation;

import java.text.ParseException;

import org.quartz.SchedulerException;

import com.innovation.scheduler.MailScheduler;

public class Runner {

	public static void main(String[] args) throws ParseException, SchedulerException {
		
		MailScheduler mailScheduler = new MailScheduler();
		mailScheduler.trigger();
		
	}
}
