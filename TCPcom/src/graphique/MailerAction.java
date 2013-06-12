package graphique;
/*

import java.sql.Date;

import javax.mail.Session;
import javax.naming.Context;
import javax.naming.InitialContext;


public class MailerAction 
{
	private static final String mailer = "Intranet de Gestion";
	private String mailSessionName_ ;
	private String text_ ;
	private String subject_;
	private String recipient_;
	private String from_;
	public MailerAction(String mailSessionName, String text, String subject, String recipient, String from) 
	{
		this.mailSessionName_ = mailSessionName;
		this.text_ = text;
		this.subject_ = subject;
		this.from_ = from;
	}
	public void setText(String theText) 
	{ 
		text_= theText; 
	}
	public void setSubject(String theSubject) 
	{ 
		subject_= theSubject; 
	}
	public void setRecipient(String theRecipient) 
	{ 
		recipient_ = theRecipient; 
	}
	public void setFrom(String theFrom) 
	{ 
		from_ = theFrom; 
	}
	public void setMailSessionName(String newMailSessionName) 
	{ 
		mailSessionName_ = newMailSessionName; 
	}
	public String getText() 
	{ 
		return text_; 
	}
	public String getSubject() 
	{ 
		return subject_; 
	}
	public String getRecipient() 
	{ 
		return recipient_; 
	}
	public String getFrom() 
	{
		return from_; 
	}
	public String getMailSessionName() 
	{ 
		return mailSessionName_; 
	}
	public boolean sendMessage() 
	{
	try 
	{
		Context initial = new InitialContext();
		Session session = (Session) initial.lookup(mailSessionName_);
		DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);
		Date timeStamp = new Date();
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(this.getFrom()));
		msg.setHeader("Mail récapitulatif", mailer);
		msg.setSentDate(timeStamp);
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.getRecipient() , false));
		msg.setSubject(this.getSubject());
		msg.setText(this.getText() );
		Transport.send(msg);
		System.out.println("Mail sent");
		// Effacer le print après avoir testé !
		return true;
	} 	
	catch(Exception e) 
	{
		System.out.println("Exception occured in mailer bean : " + e.getMessage() );
		return false;
	}
}	
	
	public static void main(String[] args)
	{
		MailerAction mail = new MailerAction("TEST", "TestText", "TestSubject", "gbada_yaovi@yahoo.fr", "gbada_yaovi@yahoo.fr");
		mail.sendMessage();
	}
}


*/