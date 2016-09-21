package com.innovation.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.innovation.mail.Mailer;

public class ReadJob implements Job{
	
	private final Mailer mailer = new Mailer();
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		mailer.readMail();
	}

}