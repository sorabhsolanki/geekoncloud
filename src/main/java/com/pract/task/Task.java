package com.pract.task;

import java.util.Date;


public class Task {

	private String name;
	private Date sentDate;
	private String subject;
	private Object content;
	private boolean isProcessed;
	
	public Task(String name, Date sentDate, String subject, Object content) {
		this.name = name;
		this.sentDate = sentDate;
		this.subject = subject;
		this.content = content;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
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
