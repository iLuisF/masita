/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.web;
import com.kaab.proyecto.db.Usuario;
import com.kaab.proyecto.db.controller.UsuarioJpaController;
import java.io.Serializable;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author Ernesto Palacios
 */
@ManagedBean
@ViewScoped
public class EnviaMail implements Serializable {
    private final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("MiProyectoPU");
     private final UsuarioJpaController controlador =
             new UsuarioJpaController(emf);
     public void envia(String correo){
       // La direcci�n de env�o (to)
       String para = correo;

       // La direcci�n de la cuenta de env�o (from)
       String de = "kaabcompany@gmail.com";
       // El servidor (host). En este caso usamos localhost
       String host = "localhost";

       // Obtenemos las propiedades del sistema
       Properties propiedades = System.getProperties();
       // Configuramos el servidor de correo
       propiedades.setProperty("mail.smtp.host", host);
       propiedades.put("mail.smtp.auth", "true");
       propiedades.put("mail.smtp.starttls.enable", "true");
       propiedades.put("mail.smtp.host", "smtp.gmail.com");
       propiedades.put("mail.smtp.port", "587");

       // Obtenemos la sesi�n por defecto
        Session sesion = Session.getInstance(propiedades,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(de, "seguridadImportante");
            }
          });
       try {
         // Creamos un objeto mensaje tipo MimeMessage por defecto.
         MimeMessage mensaje = new MimeMessage(sesion);

         // Asignamos el �de o from� al header del correo.
         mensaje.setFrom(new InternetAddress(de));

         // Asignamos el �para o to� al header del correo.
         mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));

         // Asignamos el asunto
         mensaje.setSubject("Validacion de cuenta");
         String aux = "<h1>presione el link para proceder a validar su cuenta</h1>"
                             + "<a href=http://localhost:8084/masita/CuentaValidadaIH.xhtml?idUsuario=" + buscaid(para) + "> presione aquí para ir a la página<a/>";
         System.out.println(aux);
         // Asignamos el mensaje como tal
          mensaje.setContent(aux ,"text/html" );

         // Enviamos el correo
         Transport.send(mensaje);
         System.out.println("Mensaje enviado");
       } catch (MessagingException e) {
            e.printStackTrace();
       }
    }
     
     private long buscaid (String correo){
         List<Usuario> usuarios = controlador.findUsuarioEntities();
         for(Usuario x : usuarios ){
             if(x.getCorreo().equals(correo)){
                 return x.getIdUsuario();
             }
         }
         return -1;
     }
}
