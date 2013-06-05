/**
 * 
 */
package org.srluren.web.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.srluren.web.beans.hb.Parametro;
import org.srluren.web.resources.Parameters;
import org.srluren.web.services.impl.ParametroServiceImpl;

/**
 * @author AETOS PERU S.A.C
 * 
 */
public class ServerMail {

	public String MAIL_HOST = "";
	public String MAIL_TRANSPORT = "";
	public String MAIL_SCF_PORT = "";
	public String MAIL_SMTP_GOOGLE_PORT = "";
	public String MAIL_SCF_CLASS = "";
	public String MAIL_AUTH = "";
	public String MAIL_USER_CX = "";
	public String MAIL_PWD_CX = "";
	public String MAIL_USE_GMAIL = "";
	public String MAIL_FROM = "";
	public String MAIL_TO = "";
	public String MAIL_CC = "";
	public String MAIL_SUBJ = "";
	public String MAIL_TO_INTERNO="";
	public Parameters parameters;
	public static String MAIL_MSJ_RET = "";
	public static String MAIL_COD_RET = "";
	public static String MAIL_MSJ_NOK = "";
	public static String MAIL_CO_NOK = "";
	
	public ParametroServiceImpl parametroServiceImpl = new ParametroServiceImpl();

	public ServerMail() {

		/* OBTIENE UNA INSTANCIA DEL ARCHIVO DE PARAMETROS*/
		parameters = Parameters.getInstance();

		/*CARGA PARAMETROS PARA ENVIO DE CORREO*/
		this.MAIL_HOST 				= parameters.getKey("MAIL_HOST");
		this.MAIL_TRANSPORT 		= parameters.getKey("MAIL_TRANSPORT");
		this.MAIL_SCF_PORT 			= parameters.getKey("MAIL_SCF_PORT");
		this.MAIL_SMTP_GOOGLE_PORT  = parameters.getKey("MAIL_SMTP_GOOGLE_PORT");
		this.MAIL_SCF_CLASS 		= parameters.getKey("MAIL_SCF_CLASS");
		this.MAIL_AUTH 				= parameters.getKey("MAIL_AUTH");
		this.MAIL_USER_CX 			= parameters.getKey("MAIL_USER_CX");
		this.MAIL_PWD_CX 			= parameters.getKey("MAIL_PWD_CX");
		this.MAIL_USE_GMAIL 		= parameters.getKey("MAIL_USE_GMAIL");
		this.MAIL_FROM 				= parameters.getKey("MAIL_FROM");
		this.MAIL_CC 				= parameters.getKey("MAIL_CC");
		this.MAIL_SUBJ 				= parameters.getKey("MAIL_SUBJ");

	}

	/**
	 * @param pNombreProducto
	 * @param pNombreSubProducto
	 * @param pNombreApellido
	 * @param pCorreoCliente
	 * @param pTelefonoCliente
	 */
	public boolean enviarCorreo(String pNombreProducto, String pNombreSubProducto,
			String pNombreApellido, String pCorreoCliente,
			String pTelefonoCliente) {

		// Objeto propiedades
		Properties props = new Properties();
		this.MAIL_TO 			= pCorreoCliente;
		this.MAIL_TO_INTERNO 	= parameters.getKey("MAIL_TO_INTERNO");

		try {

			// Valida si se usará el servidor de correo de GMAIL o no
			if (MAIL_USE_GMAIL.equals("S")) {

				props.put("mail.smtp.host", MAIL_HOST);
				props.put("mail.smtp.socketFactory.port", MAIL_SCF_PORT);
				props.put("mail.smtp.socketFactory.class", MAIL_SCF_CLASS);
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", MAIL_SMTP_GOOGLE_PORT);

			} else { // Utiliza otro servidor de correos por defecto
				
				props.put("mail.smtp.host", MAIL_HOST);
				props.put("mail.smtp.socketFactory.port", MAIL_SCF_PORT);
				props.put("mail.smtp.socketFactory.class", MAIL_SCF_CLASS);
				props.put("mail.smtp.auth", "false");
			}

			/* INSTANCIA SESION*/
			Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(MAIL_USER_CX,
									MAIL_PWD_CX);
						}
					});
			
			//Session session = Session.getDefaultInstance(props);
			
			/*OBTIENE SECUENCIAL*/
			Parametro parametro = parametroServiceImpl.buscarParametro("1");
			int secuencial_actual = Integer.parseInt(parametro.getValorPar());
			int nuevo_secuencial = secuencial_actual + 1;
			
			/*CONSTRUYE MENSAJE A ENVIAR AL USUARIO INTERNO*/
			Message messageInterno = new MimeMessage(session);
			messageInterno.setFrom(new InternetAddress(MAIL_FROM));
			messageInterno.setRecipients(Message.RecipientType.TO,InternetAddress.parse(MAIL_TO_INTERNO));
			messageInterno.setSubject(MAIL_SUBJ);
			messageInterno.setText(generarMensajeCorreoInterno(pNombreProducto,pNombreSubProducto, pNombreApellido, pCorreoCliente,pTelefonoCliente,nuevo_secuencial));

			/*ENVIA CORREO AL USUARIO INTERNO*/
			Transport.send(messageInterno);
			
			/*CONSTRUYE MENSAJE A ENVIAR AL CLIENTE*/
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(MAIL_FROM));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(MAIL_TO));
			message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(MAIL_CC));
			message.setSubject(MAIL_SUBJ);
			message.setText(generarMensajeCorreoExterno(pNombreProducto,pNombreSubProducto, pNombreApellido, pCorreoCliente,pTelefonoCliente, nuevo_secuencial));

			/*ENVIA CORREO AL CLIENTE*/
			Transport.send(message);
			
			/*ACTUALIZA SECUENCIAL EN BASE DE DATOS*/
			parametro.setValorPar(String.valueOf(nuevo_secuencial));
			parametroServiceImpl.actualizarParametro(parametro);
			
			return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * @param pNombreProducto
	 * @param pNombreSubProducto
	 * @param pNombreApellido
	 * @param pCorreoCliente
	 * @param pTelefonoCliente
	 * @return
	 */
	public String generarMensajeCorreoInterno(String pNombreProducto,
			String pNombreSubProducto, String pNombreApellido,
			String pCorreoAdmSrLuren, String pTelefonoCliente, int pSecuencial) {

		String mensaje = "Tiene una solicitud de visita con el N° "+ pSecuencial + ", con los siguientes datos:\n"
						+"\nProducto: "+ pNombreProducto
						+"\nSub Producto:  "+ pNombreSubProducto
						+"\nNombres y Apellidos: "+ pNombreApellido
						+"\nCorreo: "+ pCorreoAdmSrLuren
						+"\nTeléfono: "+ pTelefonoCliente
						+"\n\n\nEste Buzón es de envío automático, por favor no responder.";

		return mensaje;

	}
	
	
	/**
	 * @param pNombreProducto
	 * @param pNombreSubProducto
	 * @param pNombreApellido
	 * @param pCorreoCliente
	 * @param pTelefonoCliente
	 * @return
	 */
	public String generarMensajeCorreoExterno(String pNombreProducto,
			String pNombreSubProducto, String pNombreApellido,
			String pCorreoCliente, String pTelefonoCliente, int pSecuencial) {

		String mensaje = "Se ha generado una solicitud de visita con el N° "+ pSecuencial + ", con los siguientes datos:\n"
						+"\nProducto: "+ pNombreProducto
						+"\nSub Producto:  "+ pNombreSubProducto
						+"\nNombres y Apellidos: "+ pNombreApellido
						+"\nCorreo: "+ pCorreoCliente
						+"\nTeléfono: "+ pTelefonoCliente
						+"\n\n\nEste Buzón es de envío automático, por favor no responder.";

		return mensaje;

	}

}
