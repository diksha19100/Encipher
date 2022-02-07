package encipher;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendingMail {

	Session newSession = null;
	MimeMessage mimeMessage = null;
	String emailid ;
	SendingMail(String id) throws AddressException, MessagingException, IOException
	{
		emailid = id;
		setupServerProperties();
		draftEmail();
		sendEmail();
	}
	public static void main(String args[]) throws AddressException, MessagingException, IOException
	{
		new SendingMail("");
	}

	private void sendEmail() throws MessagingException {
		String fromUser = "***";  //Enter sender email id
		String fromUserPassword = "";  //Enter sender gmail password , this will be authenticated by gmail smtp server
		String emailHost = "smtp.gmail.com";
		Transport transport = newSession.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserPassword);
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		transport.close();
		System.out.println("Email successfully sent!!!");
	}

	private MimeMessage draftEmail() throws AddressException, MessagingException, IOException {
		String[] emailReceipients = {emailid};  //Enter list of email recepients
		String emailSubject = "Test Mail";

		mimeMessage = new MimeMessage(newSession);
		
		for (int i =0 ;i<emailReceipients.length;i++)
		{
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients[i]));
		}
		mimeMessage.setSubject(emailSubject);
	   
      // CREATE MIMEMESSAGE 
	    // CREATE MESSAGE BODY PARTS 
	    // CREATE MESSAGE MULTIPART 
	    // ADD MESSAGE BODY PARTS ----> MULTIPART 
	    // FINALLY ADD MULTIPART TO MESSAGECONTENT i.e. mimeMessage object 
	    
	    
		 MimeBodyPart bodyPart = new MimeBodyPart();
		 
		 MimeBodyPart fileMime = new MimeBodyPart();
		 
		 File file=new File("Output.txt");
		 fileMime.attachFile(file);
		 
		 bodyPart.setText(" Hiii ");	
		 MimeMultipart multiPart = new MimeMultipart();
		 multiPart.addBodyPart(bodyPart);
		 multiPart.addBodyPart(fileMime);
		 mimeMessage.setContent(multiPart);
		 return mimeMessage;
	}

	private void setupServerProperties() throws  MessagingException {
		
		Properties properties = System.getProperties();
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.transport.protocol", "smtp");

		newSession = Session.getDefaultInstance(properties,null);
	}
}