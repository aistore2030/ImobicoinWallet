/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.System;

import com.login.Util;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mala singh
 */
public class LoginServlet extends HttpServlet {

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
        Statement st = null;

        try {
            con = Util.getConnection();
            st = con.createStatement();
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            String query = "select * from register    where    email='" + email + "' and password='" + password + "' and status='Verified' ";
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("username", rs.getString("username"));
                session.setAttribute("email", rs.getString("email"));
                session.setAttribute("roll", rs.getString("roll"));
                response.sendRedirect("profile.jsp");
            } else {
                // response.sendRedirect("index.jsp");

                request.setAttribute("msg", "Email and Password are Invalid!!");
                RequestDispatcher rq = request.getRequestDispatcher("index.jsp");
                rq.forward(request, response);
            }

        } catch (Exception e) {

            e.printStackTrace();
            request.setAttribute("msg", "Email and Password are Invalid!!");
            RequestDispatcher rq = request.getRequestDispatcher("index.jsp");
            rq.forward(request, response);
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
