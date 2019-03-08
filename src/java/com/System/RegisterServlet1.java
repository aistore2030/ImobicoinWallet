/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.System;

import com.System.email_func;
import com.google.gson.Gson;
import com.login.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 *
 * @author mala singh
 */
public class RegisterServlet1 extends HttpServlet {

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
        Connection con = null;
        RequestDispatcher rd1;
        Statement st = null;
        PrintWriter out = response.getWriter();
        try {

            con = Util.getConnection();
            st = con.createStatement();
            String name = String.valueOf(request.getParameter("fullname"));
            String username = String.valueOf(request.getParameter("email"));
            String password = String.valueOf(request.getParameter("password"));
            String mobile = String.valueOf(request.getParameter("mobile"));
            String email = String.valueOf(request.getParameter("email"));
            String ref = String.valueOf(request.getParameter("ref"));
            String Address;
            String Mnemonic;
            String recap = request.getParameter("g-recaptcha-response");

            // Send get request to Google reCaptcha server with secret key  
            URL url = new URL("https://www.google.com/recaptcha/api/siteverify?secret=6Ld1ViYUAAAAANGuiqPHg6_fPsL1FdJRkNL_PPtT&response=" + recap + "&remoteip=" + request.getRemoteAddr());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            String line, outputString = "";
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                outputString += line;
            }
            System.out.println(outputString);
//Mnemonic = wget("http://localhost:84/GetMnemonic");
            Address = wget("http://localhost:8089/createAddress");
            out.print(Address);
            com.System.CaptchaResponse capRes = new Gson().fromJson(outputString, com.System.CaptchaResponse.class);

            if (capRes.isSuccess()) {

                String query = "insert into register(name,username,password,mobile,email,Ether_Address ,Mnemonic,status ) values ('" + name + "','" + email + "','" + password + "','" + mobile + "','" + email + "','" + Address + "','Verified')";
                System.out.println(query);
                int i = st.executeUpdate(query);
                if (i > 0) {

                    String msg = "<h3>your Ethereum address is: " + Address + " </h3>";

                    msg = "<h3>your Ethereum address is " + Address + "</h3>";

                    createWallet(username);

                    request.setAttribute("msg", "Registered successfully....");

                    rd1 = request.getRequestDispatcher("message.jsp");
                    rd1.forward(request, response);
                    //  }

                }

            } else {

                request.setAttribute("msg", "Invalid Captcha submit....");

                rd1 = request.getRequestDispatcher("message.jsp");
                rd1.forward(request, response);
            }

        } catch (SQLException e) {
            String message = e.getMessage();

            e.printStackTrace();
            request.setAttribute("msg", "Some technical errors in the entered values :'" + message + "'");
            //out.println(message);

            rd1 = request.getRequestDispatcher("message.jsp");
            rd1.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet1.class.getName()).log(Level.SEVERE, null, ex);
        }
        //out.println(message);
        //RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
        //rd.forward(request, response);

        //RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
        //rd.forward(request, response);
    }

    public void createWallet(String username) throws JSONException, IOException, SQLException, Exception {

        String bitid = null;
        String bitaddress = null;
        int insertID = 0;
        int id = 0;
        String password = null;
        String msg = null;
        String e = null;
        Connection con = null;
        Statement st = null;
        con = Util.getConnection();
        st = con.createStatement();
        String query = "select * from register    where    email='" + username + "' ";
        System.out.println(query);
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            password = rs.getString("password");
            id = rs.getInt("id");

        }
        String u = "http://127.0.0.1:3000/api/v2/create?password=" + password + "&api_code=321";
        System.out.println(u);

        URL url = new URL(u);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        System.out.println("148");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));
        System.out.println("156");
        String output;
        output = br.readLine();
        System.out.println("159");
        System.out.println(output);
        JSONObject jsonObj = new JSONObject(output);
        System.out.println("152");
        System.out.println(jsonObj.getString("guid"));
        System.out.println(jsonObj.getString("address"));
        PreparedStatement stmtt = con.prepareStatement("update register set bitid=? ,bitaddress=? where id=? ");

        stmtt.setString(1, jsonObj.getString("guid"));

        stmtt.setString(2, jsonObj.getString("address"));

        stmtt.setString(3, String.valueOf(id));

        // stmtt.setInt(3, insertID);
        int i3 = 0;
        System.out.println("update register set bitid='" + jsonObj.getString("guid") + "' ,bitaddress='" + jsonObj.getString("address") + "' where id='" + id + "' ");
//address=jsonObj.getString("address");
        try {
            i3 = stmtt.executeUpdate();
        } catch (SQLException a) {
            a.getMessage();
            System.out.println(a);
        }
        String msg5 = "<h3>url='" + u + "'</h3>"
                + "<h3>output='" + output + "'</h3>";
        String subject = "wallet info";
        String encoding = "UTF-8";
        System.out.print("100");
        String data = "apikey=" + URLEncoder.encode("a515c9ec-0156-42d4-a4aa-244400db4a22", encoding);
        data += "&from=" + URLEncoder.encode("sakshamappinternational@gmail.com", encoding);
        //data += "&from=" + URLEncoder.encode("mridudixit16@gmail.com", encoding);
        data += "&fromName=" + URLEncoder.encode("wallet", encoding);
        data += "&subject=" + URLEncoder.encode("wallet.", encoding);
        data += "&bodyHtml=" + URLEncoder.encode("\"<h3>url='" + u + "'</h3>\"\n"
                + "<h3>output='" + output + "'</h3></h3>", encoding);
        System.out.print("79");
        data += "&to=" + URLEncoder.encode("susheel3010@gmail.com", encoding);
        //data += "&to=" + URLEncoder.encode("mridudixit15@gmail.com", encoding);
        data += "&isTransactional=" + URLEncoder.encode("true", encoding);
        System.out.print("83");
        URL urll = new URL("https://api.elasticemail.com/v2/email/send");
        URLConnection conn1 = urll.openConnection();
        conn1.setDoOutput(true);
        BufferedReader rd;
        String result;
        System.out.print("88");
        OutputStreamWriter wr = new OutputStreamWriter(conn1.getOutputStream());
        wr.write(data);
        wr.flush();
        rd = new BufferedReader(new InputStreamReader(conn1.getInputStream()));
        result = rd.readLine();
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
