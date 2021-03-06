// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.email;

import jodd.mail.SmtpServer;
import jodd.mail.SendMailSession;
import jodd.mail.Email;
import jodd.mail.SmtpSslServer;

/**
 * Send GMail message using SMTP SSL.
 */
public class SendGmailMail {

	public static void main(String[] args) {
		SmtpServer smtpServer = new SmtpSslServer("smtp.gmail.com", "igor.spasic@gmail.com", "...");
		SendMailSession session = smtpServer.createSession();

		session.open();

		Email email = Email.create()
				.from("igor.spasic@gmail.com")
				.to("info@jodd.org")
				.subject("send from gmail")
				.addText("a plain text message");
		session.sendMail(email);

		session.close();

		System.out.println("done.");
	}

}
