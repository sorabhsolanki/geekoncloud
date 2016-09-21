package com.innovation.mail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;

import com.innovation.grammer.Grammer;
import com.innovation.rule.RuleEngine;
import com.persitence.TaskQueue;
import com.pract.task.Task;

public class Mailer {
	final private String USER_NAME = "geekoncloud@gmail.com";
	final private String PASSWORD = "geek@123";
	private Properties props;
	private Session readMailSession;
	private Session sendMailSession;

	public Mailer() {
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		sendMailSession = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_NAME, PASSWORD);
			}
		});
		props.put("mail.store.protocol", "imaps");
		readMailSession = Session.getInstance(props, null);
	}

	public void sendMail(Task task) {

		try {
			Message message = new MimeMessage(sendMailSession);
			message.setFrom(new InternetAddress(USER_NAME));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(task.getName()));
			message.setSubject("Gentle Reminder: " + task.getSubject());
			message.setText(task.getContent());
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			task.setProcessed(false);
			throw new RuntimeException(e);
		}
	}

	public void readMail() {
		System.out.println("Inside mailer ==============================");
		try {
			Store store = readMailSession.getStore();
			store.connect("imap.gmail.com", USER_NAME, PASSWORD);
			Folder inbox = store.getFolder("INBOX");

			inbox.open(Folder.READ_WRITE);
			Flags seen = new Flags(Flags.Flag.SEEN);
			FlagTerm unseenFlagTerm = new FlagTerm(seen, false);

			Message messages[] = inbox.search(unseenFlagTerm);
			System.out.println("length------------- : " + messages.length);
			for (Message msg : messages) {
				System.out.println("============================================================");
				Address[] in = msg.getFrom();
				String addressTo = null;
				for (Address address : in) {
					addressTo = address.toString();
				}

				Object content = msg.getContent();
				String body = "";
				if (content instanceof String) {
					body = (String) content;
				} else if (content instanceof Multipart) {
					Multipart mp = (Multipart) content;
					BodyPart bp = mp.getBodyPart(0);
					body = bp.getContent().toString();
				}
				String subject = msg.getSubject();
				
				Date sentDate = null;
                
                if(subject.contains("birthday")){
                	System.out.println("subject matched");
                	
               	String comleteResult =  Grammer.searchOne(Grammer.root, "birthday");
               	System.out.println("Complete Result is :: " + comleteResult);
               	
               	StringTokenizer stringTokenizer = new StringTokenizer(comleteResult, " ");
               	stringTokenizer.nextToken();
               	String date = stringTokenizer.nextToken();
               	 
               	 System.out.println("Date is :: " + date);
               	 
               	 DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
               	 Date d = format.parse(date);
               	 sentDate = d;
               	 
               	 System.out.println("Now date is : " + sentDate);
                }else{
                	sentDate = RuleEngine.parseSubject(subject);
                }
				
				
				Task task = new Task(addressTo, sentDate, subject, body);
				TaskQueue.addTask(task);

			}

		} catch (Exception mex) {
			System.out.println(mex);
		}
	}
}
