package com.innovation.scheduler;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.innovation.mail.Mailer;
import com.persitence.TaskQueue;
import com.pract.task.Task;

public class WriterJob implements Job{
	
	private final Mailer mailer = new Mailer();
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Writer Job::::::::::");
		for(Task task : TaskQueue.getList()){
			System.out.println(task.getName());
			
			Date currentDate = new Date();
			
			if(!task.isProcessed() && currentDate.after(task.getRemindDate())){
				System.out.println("Picked up the Task : " + task.getSubject());
				task.setProcessed(true);
				mailer.sendMail(task);
				
			}
		}

	}

}