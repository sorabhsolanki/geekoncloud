package com.pract.task;

import java.util.Date;


public class Task {

	private String name;
	private Date remindDate;
	private String subject;
	private String content;
	private boolean isProcessed;
	
	public Task(String name, Date remindDate, String subject, String content) {
		this.name = name;
		this.remindDate = remindDate;
		this.subject = subject;
		this.content = content;
	}

	public Date getRemindDate() {
		return remindDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Task(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isProcessed() {
		return isProcessed;
	}

	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	
	

}
