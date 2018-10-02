/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author JP Infotech
 */

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
  
public class MailUtil {  
 
	
    public boolean sendMail(String[] recipients, String[] bccRecipients, String subject, String message1) {  
       
    	try {  
        	
    		mailsend(message1,recipients[0],subject);
    		
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	
        	return false;
        }
    	
    	return true;
    }
    
    public static void mailsend(String key, String email,String subject) throws MessagingException
	{
    	
    	System.out.println("email is :\t"+email);
    	System.out.println("key is :\t"+key);
    	System.out.println("subject is :\t"+subject);
    	
		String host = "smtp.gmail.com";
		String from = "maheshwarin.coign@gmail.com";
		String pass = "coign@123";

		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com"); // added this line
		props.put("mail.smtp.socketFactory.port","465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");

		String[] to = {email}; // added this line

		Session session = Session.getDefaultInstance(props,  new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("maheshwarin.coign@gmail.com", "coign@123");
				
			}
		});
		try{
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		message.setSubject("Subject");
		message.setText(key);
		
		InternetAddress[] toAddress = new InternetAddress[to.length];

		// To get the array of addresses

		for( int i=0; i < to.length; i++ ) 
		{ 
			// changed from a while loop
			toAddress[i] = new InternetAddress(to[i]);
		}
		
		for( int i=0; i < toAddress.length; i++)
		{ 
			// changed from a while loop
			message.addRecipient(Message.RecipientType.TO, toAddress[i]);
		}

		message.setSubject(subject);
		message.setText("Signature\t:"+key);

		Transport transport = session.getTransport("smtp");

		transport.connect(host, from, pass);
		transport.sendMessage(message, message.getAllRecipients());

		transport.close();
	}catch(MessagingException e) {
		System.out.println(e);
		e.printStackTrace();
	}
  
} 
}