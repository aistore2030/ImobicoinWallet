/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angularapi;

import com.google.gson.Gson;
import com.login.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author panel2
 */
public class Transactionhistory extends HttpServlet {

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
        //  Recharge r=new Recharge();

        Connection con = null;
        Statement st = null;

        try {
            PrintWriter out = response.getWriter();
            con = Util.getConnection();
            st = con.createStatement();
            String query = "";
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");

            query = "select * from transactions  where username='" + username + "' ";
            System.out.println(query);
            ArrayList<Transactionhistory> a = new ArrayList<>();

            int i = 0;
            //  out.println(query);
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                Transactionhistory a1 = new Transactionhistory();
                a1.id = rs.getString("id");
                a1.username = rs.getString("username");
                a1.imobicash_sender = rs.getString("imobicash_sender");
                a1.imobicash_receiver = rs.getString("imobicash_receiver");
                a1.cr = rs.getString("cr");
                a1.gas_price = rs.getString("gas_price");
                a1.TransactionHash = rs.getString("TransactionHash");

                a.add(a1);
            }

            Gson gson = new Gson();
            String messages = gson.toJson(a);
            out.println(messages);

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
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

    public String id, username, imobicash_sender, imobicash_receiver, cr, gas_price, TransactionHash;
}
