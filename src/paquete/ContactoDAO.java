package paquete;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

public class ContactoDAO {


	public void enviarCorreo(HttpServletRequest request) {
		String nombre=request.getParameter("nombre");
		String asunto=request.getParameter("asunto");
		String email=request.getParameter("email");
		String mensaje=request.getParameter("mensaje");
		
		final String username = "vetustainformatica@gmail.com";
		final String password = "rogaldorn";
 
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
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(username));
				message.setSubject(asunto);
				message.setText("El nombre de la persona que ha hecho contacto es: "+nombre+"\nSu email es: "+email+"\n\n"+mensaje);
				Transport.send(message);
				
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Vetusta Informática");
			message.setText("Su mensaje ha sido enviado correctamente.\nLe contestarémos lo antes posible.\nUn saludo\nGabriel");
			Transport.send(message);

		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
