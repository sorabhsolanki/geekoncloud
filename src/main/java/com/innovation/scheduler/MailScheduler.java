package com.innovation.scheduler;

import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class MailScheduler {

	public void trigger() throws ParseException, SchedulerException{
		
		System.out.println("----------------------Inside Trigger -------------------");
		JobDetail readjob = new JobDetail();
		readjob.setName("mailReaderJob");
		readjob.setJobClass(ReadJob.class);

    	CronTrigger readtrigger = new CronTrigger();
    	readtrigger.setName("mailReader");
    	readtrigger.setCronExpression("0/10 * * * * ?");
		
		
		
		JobDetail job = new JobDetail();
    	job.setName("mailWriterJob");
    	job.setJobClass(WriterJob.class);

    	CronTrigger trigger = new CronTrigger();
    	trigger.setName("mailWriter");
    	trigger.setCronExpression("0/3 * * * * ?");

    	//schedule it
    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    	scheduler.start();
    	scheduler.scheduleJob(readjob, readtrigger);
    	scheduler.scheduleJob(job, trigger);
	}
}
