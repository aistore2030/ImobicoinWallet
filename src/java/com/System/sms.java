/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.System;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mala singh
 */
public class sms extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

    }

    public void sendemail(String message, String email, String subject)
            throws ServletException, IOException {
        Email from = new Email("susheelhbti@outlook.com");
        // String subject = "Sending with SendGrid is Fun";
        Email to = new Email(email);
        Content content = new Content("text/html", message);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }

    }

    public void smssend(String message, String mobiles)
            throws ServletException, IOException {

//Your authentication key
        String authkey = "f3f4ee5749b3851ee698afcbabd3ea96";
//Multiple mobiles numbers separated by comma
//String mobiles = "9999999";
//Sender ID,While using route4 sender id should be 6 characters long.
        String senderId = "sakshm";
//Your message to send, Add URL encoding here.
//String message = "Test message";
//define route
        String route = "1";
//Prepare Url
        URLConnection myURLConnection = null;
        URL myURL = null;
        BufferedReader reader = null;
//encoding message 
        String encoded_message = URLEncoder.encode(message);
//Send SMS API
        String mainUrl = "http://sms.sakshamapp.com/api/send_http.php?";
//Prepare parameter string 
        StringBuilder sbPostData = new StringBuilder(mainUrl);
        sbPostData.append("authkey=" + authkey);
        sbPostData.append("&mobiles=" + mobiles);
        sbPostData.append("&message=" + encoded_message);
        sbPostData.append("&route=" + route);
        sbPostData.append("&sender=" + senderId);
//final string
        mainUrl = sbPostData.toString();
        try {
            //prepare connection
            myURL = new URL(mainUrl);
            myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            //reading response 
            String response;
            while ((response = reader.readLine()) != null) //print response 
            {
                System.out.println(response);
            }

            //finally close connection
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendemailtoself(String message, String subject)
            throws ServletException, IOException {
        Email from = new Email("susheelhbti@outlook.com");
        // String subject = "Sending with SendGrid is Fun";
        Email to = new Email("susheel3010@gmail.com");
        Content content = new Content("text/html", message);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(121);
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
