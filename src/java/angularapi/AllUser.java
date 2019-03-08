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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * @author Saksham
 */
public class AllUser extends HttpServlet {

    private String id;
    private String name;
    private String username;
    private String password;
    private String mobile;
    private String email;
    private String call_back_url;
    private String api_key;
    private String plan;
    private String status;
    private String tempstatus;
    private String imobicash_address;
    private long balance;

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
        String url = request.getRequestURL().toString();

        URL url1 = new URL(url);
        String dom = url1.getHost();
        response.setContentType("application/json;charset=UTF-8");
        Connection con = null;
        try {
            PrintWriter out = response.getWriter();
            //  bal b;
            Statement st = null;
            ResultSet rs;
            HttpSession session = request.getSession();
            String roll = (String) session.getAttribute("roll");
            String username = (String) session.getAttribute("username");

            String id = null;
            String uid = null;
            con = Util.getConnection();
            st = con.createStatement();

            System.out.println(135);
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = "";
            if (br != null) {
                json = br.readLine();
            }
            System.out.println(141);
            System.out.println(json);

            System.out.println(157);

            String q = null;
            PreparedStatement stmt;
            if (10 == Integer.parseInt(roll)) {

                q = "SELECT * FROM `register`  order by id DESC";
                //q = "SELECT * FROM `register` where parent='" + username + "'    or parent in (SELECT USERname FROM `register` where parent='" + username + "')  or parent in (SELECT USERname FROM `register` where parent='" + username + "' or parent in (SELECT USERname FROM `register` where parent='" + username + "'))";

                stmt = con.prepareStatement(q);
            } else {

                q = "  SELECT * FROM `register` where username='" + username + "'    order by id DESC ";
                stmt = con.prepareStatement(q);

            }
            System.out.println(140);
            System.out.println(q);
            Wget w = new Wget();
            //   PreparedStatement stmt = con.prepareStatement(q);
            rs = stmt.executeQuery();
            System.out.println(rs);
            System.out.println(51);
            ArrayList<AllUser> a;
            a = new ArrayList<>();
            while (rs.next()) {
                AllUser a1 = new AllUser();
                //    a1.balance = rs.getString(1);
                a1.id = rs.getString("id");
                a1.name = rs.getString("name");
                a1.username = rs.getString("username");
                a1.password = rs.getString("password");
                a1.mobile = rs.getString("mobile");
                a1.email = rs.getString("email");

                a1.status = rs.getString("status");
                a1.imobicash_address = rs.getString("imobicash_address");
                String aaa = w.wget("http://localhost:8089/getBalance?address=" + rs.getString("imobicash_address") + "");
                System.out.println(aaa);
                JSONObject jsonObj = new JSONObject(aaa);
                JSONObject ja_data = jsonObj.getJSONObject("result");
                System.out.println(ja_data);
                long a11 = ja_data.getLong("availableBalance") / 1000000;
                a11 = a11 / 1000000;
                System.out.println(66);
                System.out.println(a11);
                a1.balance = a11;

                a.add(a1);

            }
            Gson gson = new GsonBuilder().create();
            String jsonArray = gson.toJson(a);
            //out.println(messages); 
            out.write(jsonArray);
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AllUser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e) {
                out.println(e);
            }

        } catch (Exception e) {
            //  out.println(e);
        } finally {
            /*This block should be added to your code
  * You need to release the resources like connections
             */
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AllUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = null;
        try {
            System.out.println(451);
            response.setContentType("application/json;charset=UTF-8");

            Statement st = null;
            HttpSession session = request.getSession();
            String parent = String.valueOf(session.getAttribute("parent"));
            String username = String.valueOf(session.getAttribute("username"));
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = "";
            if (br != null) {
                json = br.readLine();
            }

            JSONObject jsonObj = new JSONObject(json);
            System.out.println(json + 49);
            con = Util.getConnection();
            st = con.createStatement();
            System.out.println(467);
            String user = jsonObj.getString("username");
            System.out.println(user);
            String case1 = jsonObj.getString("case");
            String status = jsonObj.getString("status");
           

                PrintWriter out = response.getWriter();
                con = Util.getConnection();
                st = con.createStatement();

                PreparedStatement stmt = con.prepareStatement("update register set status=?   where username=?");

                stmt.setString(1, case1);
                stmt.setString(2, user);

                int i = stmt.executeUpdate();

                if (i > 0) {
                    out.println("{\"Error\": \"False\" ,\"Message\": \"success\" }");
                    //notification n = new notification();
                    //n.SaveNotification(request,username, " " + username + " status is " + tempstatus + "");

                } else {
                    System.out.println("{\"Error\": \"True\" ,\"Message\": \"Some Error\" }");
                }

            
        } catch (JSONException ex) {
            Logger.getLogger(AllUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AllUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AllUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            /*This block should be added to your code
            * You need to release the resources like connections
             */
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AllUser.class.getName()).log(Level.SEVERE, null, ex);
                }
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

}
