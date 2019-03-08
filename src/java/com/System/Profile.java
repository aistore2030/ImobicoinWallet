/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.System;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.login.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author mala singh
 */
public class Profile extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection con = null;
        Statement st = null;
        ResultSet rs;
        //   String username = String.valueOf(request.getParameter("username"));
        //   String Response = String.valueOf(request.getParameter("Response"));
        System.out.println(43);
        try {
            con = Util.getConnection();
            st = con.createStatement();
            HttpSession session = request.getSession();
            String username = String.valueOf(session.getAttribute("username")).trim();

            Wget w = new Wget();

            //out.print(a1);
            String query = "";

            query = "select * from register where username='" + username + "'";

            System.out.println(query);
            rs = st.executeQuery(query);
            Profile r1 = new Profile();
            while (rs.next()) {

                r1.name = rs.getString("name");
                r1.email = rs.getString("email");
                r1.phone = rs.getString("mobile");
                r1.imobicash_address = rs.getString("imobicash_address");

                //System.out.println(Ether_Address);
                String a = w.wget("http://localhost:8089/getBalance?address=" + rs.getString("imobicash_address") + "");
                System.out.println(a);
                //System.out.println("http://localhost:8081/getBalance?address="+rs.getString("Ether_Address")+"");
                JSONObject jsonObj = new JSONObject(a);
//Mnemonic = wget("http://localhost:84/GetMnemonic");
                JSONObject ja_data = jsonObj.getJSONObject("result");
                System.out.println(ja_data);
                long a1 = ja_data.getLong("availableBalance")/1000000;
                 a1 = a1/1000000;
                System.out.println(66);
                System.out.println(a1);
                r1.balance = a1;
                //r.add(r1);

            }

            Gson gson = new GsonBuilder().create();
            String jsonArray = gson.toJson(r1);
            //out.println(messages); 
            out.write(jsonArray);

            //["Hi","Hello","How r u?"]
            //   out.println(messages);
            con.close();
        } catch (Exception e) {
            out.println(e);
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

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Connection con = null;
        System.out.println(118);
        Statement st = null;
        PrintWriter out = response.getWriter();
        try {

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(request.getInputStream()));
            String json = "";
            if (br != null) {
                json = br.readLine();
            }
            System.out.println(json);
            JSONObject jsonObj = new JSONObject(json);
            System.out.println(jsonObj);
            con = Util.getConnection();
            st = con.createStatement();
            System.out.println(132);
            HttpSession session = request.getSession();
            String username = String.valueOf(session.getAttribute("username"));
            System.out.println(username);
            String mobile = jsonObj.getString("phone");
            String name = jsonObj.getString("name");

            System.out.println(143);
            //String query = " INSERT INTO shoutbox (username,message) VALUES ('"+username+"','"+message+"')";
            String query = " update register set name='" + name + "'  , mobile='" + mobile + "'    where username='" + username + "'  ";
            System.out.println(query);
            int i = st.executeUpdate(query);
            if (i > 0) {

//email_func en=new email_func();
                // en.SendEmail("",username+"Send Message to you.:=> "+message,"FullommTrade");
                out.println("{\"Error\": \"False\" ,\"Message\": \"success\" }");

            }

        } catch (Exception e) {
            out.println("{\"Error\": \"True\" ,\"Message\": \"Failed\" }");

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }

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

    public String name, email, phone, imobicash_address;
    public long balance;
}
