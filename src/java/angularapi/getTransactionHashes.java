/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angularapi;

import com.System.Wget;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author panel2
 */
public class getTransactionHashes extends HttpServlet {

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
        try {
            Wget w = new Wget();
            String h = w.wget("http://localhost:8089/getTransactionHashes");
            System.out.println(h);
            JSONObject jsonObj1 = new JSONObject(h);
            JSONObject ja_data1 = jsonObj1.getJSONObject("result");
            System.out.println(ja_data1);
            JSONArray ja_data2 = ja_data1.getJSONArray("blockHashes");

            ArrayList<getTransactionHashes> m;
            m = new ArrayList<>();
            for (int j = 0; j < ja_data2.length(); j++) {
                getTransactionHashes a1 = new getTransactionHashes();
                a1.addr = ja_data2.getString(j);
                m.add(a1);
            }
            Gson gson = new GsonBuilder().create();
            String jsonArray = gson.toJson(m);
            //out.println(messages); 
            out.write(jsonArray);
        } catch (Exception e) {
            out.println("{\"Error\": \"True\" ,\"Message\": \"Failed\" }");
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

    public String m, addr;
}
