package paquete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	Connection miConexion;
	PreparedStatement sentencia;
	ResultSet resultados;

	public ContactoDAO(Connection miConexion, PreparedStatement sentencia) {
		// TODO Auto-generated constructor stub
		this.miConexion = miConexion;
		this.sentencia  = sentencia;
	}
	
	
	//SE ENVIA CORREO A TRAVÉS DE LA PÁGINA DE CONTACTO

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

	public void enviarConfirmacion(HttpServletRequest request, Properties comandos) {
		// TODO Auto-generated method stub
		final String username = "vetustainformatica@gmail.com";
		final String password = "rogaldorn";
		
		String idUsuario = request.getParameter("idUs");
		String email = "";
		try {
			sentencia = miConexion.prepareStatement(comandos.getProperty("seleccionaEmailCliente"));
			sentencia.setString(1, idUsuario);
			resultados = sentencia.executeQuery();
			resultados.next();
			email = resultados.getString(1);
		} catch (SQLException e1) {
			System.out.println("Error al consultar el email antes de enviar el email "+e1.getErrorCode()+e1.getMessage());
		}
		
		
		
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
					InternetAddress.parse(email));
			message.setSubject("Vetusta Informática");
			message.setText("Se ha inscrito correctamente.\nContactaremos lo antes posible con usted para darle información del curso al que se ha apuntado");
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
