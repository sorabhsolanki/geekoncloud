package com.innovation.mail;

import java.util.Date;
import java.util.Properties;

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

import com.innovation.rule.RuleEngine;
import com.persitence.TaskQueue;
import com.pract.task.Task;

public class Mailer {

     public void sendMail(Task task) {
		final String username = "geekoncloud@gmail.com";
		final String password = "geek@123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("geekoncloud@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(task.getName()));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
     
     
     public void readMail(){
    	 System.out.println("Inside mailer ==============================");
    	 Properties props = new Properties();
         props.setProperty("mail.store.protocol", "imaps");
         try {
        	 Session session = Session.getInstance(props, null);
             Store store = session.getStore();
             store.connect("imap.gmail.com", "geekoncloud@gmail.com", "geek@123");
             Folder inbox = store.getFolder("INBOX");
        	 System.out.println("length------------- : 31");

             
             inbox.open(Folder.READ_WRITE);
             System.out.println("length------------- : 12");
             Flags seen = new Flags(Flags.Flag.SEEN);
             FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
             
             
             
             Message messages[] = inbox.search(unseenFlagTerm);
             System.out.println("length------------- : " + messages.length);
             for(Message msg : messages){
            	 System.out.println("=====================================================================================================================");
                 Address[] in = msg.getFrom();
                 String addressTo = null;
                 for (Address address : in) {
                	 addressTo = address.toString();
                 }
                 Multipart mp = (Multipart) msg.getContent();
                 BodyPart bp = mp.getBodyPart(0);
                 
                 
                 String subject = msg.getSubject();
                 Date sentDate = RuleEngine.parseSubject(subject);
                 
                 Task task = new Task(addressTo, sentDate , subject, bp.getContent());
                 TaskQueue.addTask(task);
                 
             }
             
         } catch (Exception mex) {
            System.out.println(mex);
         }
     }
}
