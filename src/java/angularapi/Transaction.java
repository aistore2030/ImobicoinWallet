/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angularapi;

import com.System.Wget;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.login.Util;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Transaction extends HttpServlet {

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
        String Ether_Address = null;
        String SendTransaction;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        try {
            con = Util.getConnection();
            st = con.createStatement();

            String query = "";
            query = "select * from register where username='" + username + "'";

            System.out.println(query);
            rs = st.executeQuery(query);
            Transaction a1 = new Transaction();
            while (rs.next()) {
                a1.imobicash_address = rs.getString("imobicash_address");
                a1.username = rs.getString("username");
                //a1.balance=a1;
            }
            Gson gson = new GsonBuilder().create();
            String jsonArray = gson.toJson(a1);
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
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        Connection con = null;
        try {

            System.out.println(38888);
            System.out.println(41);
            ResultSet rs;
            int i = 0;
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(request.getInputStream()));
            String json = "";
            if (br != null) {
                json = br.readLine();
            }
            System.out.println(json);

            JSONObject jsonObj = new JSONObject(json);
            con = Util.getConnection();
            Statement st = con.createStatement();
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            int gas_price = 0;
            try {
                gas_price = jsonObj.getInt("gas_price");
            } catch (JSONException ex) {
                Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.print(gas_price);
            String ether_receiver = jsonObj.getString("imobicash_receiver");
            
                        //String ether_sender = "FYpNGhBAKXwPVQiAgdzu1N1Nd9vLBNNEh6AWrpkP53MT2izxLjM9MRD7PGqabKuonZgC37mtfHp3D8nXrDYzDfLEHehQb6x";//jsonObj.getString("imobicash_address");

          //  String ether_sender = "FXfsRXC3VM3iWkTTiCqRtWV4vqF1zEuzUVxLcGKHQDTwYwUteBZsd2HbUpuSBTA2jTcmudvaRgU3JTSSpGWt3DG6UavZj1e";
           String ether_sender = jsonObj.getString("imobicash_address");
            int cr = jsonObj.getInt("cr");
            Wget w = new Wget();
            String a = w.wget("http://localhost:8089/sendTransactionbytecoin?fromadd=" + ether_sender + "&amount=" + cr + "&to=" + ether_receiver + "");
            System.out.println(a);
            JSONObject jsonObj1 = new JSONObject(a);
            try {
                JSONObject ja_data = jsonObj1.getJSONObject("result");
                System.out.println(ja_data);
                String a1 = ja_data.getString("transactionHash");
                System.out.println(66);

                System.out.println(a1);
                int length = a.length();
                for (int i1 = 0; i1 < length; i1++) {
                }
                if (a1 != null) {
                    String q = "insert into transactions(username,ether_sender,ether_receiver,gas_price,cr,TransactionHash) values ('" + username + "','" + ether_sender + "','" + ether_receiver + "','" + gas_price + "','" + cr + "','" + a1 + "' )";
                    System.out.println(q);

                    i = st.executeUpdate(q, Statement.RETURN_GENERATED_KEYS);
                    out.println("{\"Error\": \"False\" ,\"Message\": \"success\" }");
                } else {
                    out.print("{\"Error\": \"True\" ,\"Message\": \"failed\" }");
                }
            } catch (Exception e) {
                out.print("{\"Error\": \"True\" ,\"Message\": \"" + jsonObj1.getJSONObject("error") + "\" }");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
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

    public String username, imobicash_address;
}
