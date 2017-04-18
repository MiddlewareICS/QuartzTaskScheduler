package cn.edu.pku.quartz;
/**
 * 
 * @author zjbpoping
 *
 */

// TODO: Email job
public class Email {

	public String toEmail = null;
	public String subject = null;
	public String message = null;
	public String attachment_path = null;

	public Email(String toEmail, String subject, String message, String attachment_path) {
		this.toEmail = toEmail;
		this.subject = subject;
		this.message = message;
		this.attachment_path = attachment_path;
	}

}
