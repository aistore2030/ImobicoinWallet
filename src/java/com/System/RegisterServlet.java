/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.System;

import com.login.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import com.System.sms;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.json.JSONArray;

/**
 *
 * @author mala singh
 */
public class RegisterServlet extends HttpServlet {

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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Connection con = null;
        RequestDispatcher rd1;
        Statement st = null;

        try {

            con = Util.getConnection();
            st = con.createStatement();
            String name = String.valueOf(request.getParameter("fullname"));
            String username = String.valueOf(request.getParameter("email"));
            String password = String.valueOf(request.getParameter("password"));
            String mobile = String.valueOf(request.getParameter("mobile"));
            String email = String.valueOf(request.getParameter("email"));
            String ref = String.valueOf(request.getParameter("ref"));

         String a1="";
	/*try{
            String a = wget("http://localhost:8089/createAddress");
            System.out.println(a);
            JSONObject jsonObj = new JSONObject(a);
//Mnemonic = wget("http://localhost:84/GetMnemonic");
            JSONObject ja_data = jsonObj.getJSONObject("result");
            System.out.println(ja_data);
             a1 = ja_data.getString("address");
            System.out.println(a1);
            out.println(84);
            int length = a.length();
            for (int i = 0; i < length; i++) {
                out.println(86);
             }
	} catch(Exception e){
		System.out.println(e.getMessage());
		 a1="";
	}*/
 
            String query = "insert into register(name,username,password,mobile,email,imobicash_address ,status ) values ('" + name + "','" + email + "','" + password + "','" + mobile + "','" + email + "','" + a1 + "','Verified')";
            System.out.println(query);
            int i1 = st.executeUpdate(query);
            if (i1 > 0) {

               HttpSession session = request.getSession();
                session.setAttribute("username",email);
                session.setAttribute("email", email);
              //  session.setAttribute("roll", 3);
             //   session.setAttribute("parent", rs.getString("parent"));
                //session.setAttribute("call_back_url",call_back_url);
                 response.sendRedirect("profile.jsp");

            }

        } catch (SQLException e) {
            String message = e.getMessage();

            e.printStackTrace();
            request.setAttribute("msg", "Some technical errors in the entered values :'" + message + "'");
            //out.println(message);

            rd1 = request.getRequestDispatcher("message.jsp");
            rd1.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
    }

    protected String wget(String u)
            throws MalformedURLException, ProtocolException, SQLException, JSONException, IOException {

        URL url = new URL(u);
        java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        return br.readLine();
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
