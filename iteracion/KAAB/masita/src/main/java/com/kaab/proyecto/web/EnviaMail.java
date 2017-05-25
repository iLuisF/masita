/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.web;
import java.io.Serializable;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
/**
 *
 * @author Ernesto Palacios
 */
@ManagedBean
@ViewScoped
public class EnviaMail implements Serializable {
    
     public static void envia(String correo){
       // La dirección de envío (to)
       String para = correo;

       // La dirección de la cuenta de envío (from)
       String de = "kaabcompany@gmail.com";
       System.out.println("de : " + de + "para :" + para);
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

       // Obtenemos la sesión por defecto
        Session sesion = Session.getInstance(propiedades,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(de, "seguridadImportante");
            }
          });
       System.out.println("cree algo");
       try{
         // Creamos un objeto mensaje tipo MimeMessage por defecto.
         MimeMessage mensaje = new MimeMessage(sesion);

         // Asignamos el “de o from” al header del correo.
         mensaje.setFrom(new InternetAddress(de));

         // Asignamos el “para o to” al header del correo.
         mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));

         // Asignamos el asunto
         mensaje.setSubject("Primer correo sencillo");

         // Asignamos el mensaje como tal
          mensaje.setContent("<h1>validacion de la cuenta</h1>"
                             + "<a href=http://localhost:8084/masita/CuentaValidadaIH.xhtml>pagina inicio<a/>","text/html" );

         // Enviamos el correo
         Transport.send(mensaje);
         System.out.println("Mensaje enviado");
       } catch (MessagingException e) {
            e.printStackTrace();
       }
    }
}
