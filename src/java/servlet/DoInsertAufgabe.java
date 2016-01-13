/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import SQL.DbTools;
import SQL.InBetween;
import framework.Benutzer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kiedrowski
 */
@WebServlet(name = "DoInsertAufgabe", urlPatterns = {"/DoInsertAufgabe.do"})
public class DoInsertAufgabe extends HttpServlet {

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
        try {
            response.setContentType("text/html;charset=UTF-8");

            Connection con = DbTools.connect();
            Statement stmt = con.createStatement();

            String datentyp = request.getParameter("datentyp");
            String methodennamen = request.getParameter("methodenname");

            String kategorieId = request.getParameter("kategorieId");
            String autor = request.getParameter("autor");

            String parametertyp1 = request.getParameter("parameter1");
            String parametername1 = request.getParameter("parametername1");

            String parametertyp2 = request.getParameter("parameter2");
            String parametername2 = request.getParameter("parametername2");

            String parametertyp3 = request.getParameter("parameter3");
            String parametername3 = request.getParameter("parametername3");

            int level = Integer.parseInt((String) request.getParameter("level"));
            int exp = Integer.parseInt((String) request.getParameter("exp"));

            int aufgabenid = 0;

            String beschreibung = request.getParameter("beschreibung");
            if (beschreibung == null || beschreibung.isEmpty()) {
                beschreibung = "Es ist keine Beschreibung vorhanden.";
            }

            InBetween.InsertAufgabe(stmt, beschreibung, kategorieId, autor, methodennamen, datentyp, level, exp);
            aufgabenid = InBetween.getAufgabenId(stmt, methodennamen);

            InBetween.InsertMethodenParameter(stmt, aufgabenid, parametername1, parametertyp1);
            if (parametertyp2 != null) {
                InBetween.InsertMethodenParameter(stmt, aufgabenid, parametername2, parametertyp2);
            } else {
            }
            if (parametertyp3 != null) {
                InBetween.InsertMethodenParameter(stmt, aufgabenid, parametername3, parametertyp3);
            }

            // Aufgabe Hinzufügen 
            //
            ///////////////////////////////////////////////////////////////////
            //
            // Test Hinzufügen
            
            request.setAttribute("methodennamen", methodennamen);


            RequestDispatcher rd = request.getRequestDispatcher("neuerTest.jsp");

            rd.forward(request, response);
        } catch (SQLException ex) {
            System.err.println("Fehler:" + ex);
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
