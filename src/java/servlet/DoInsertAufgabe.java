/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import SQL.DbTools;
import SQL.InBetween;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hsqldb.result.Result;

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
            HttpSession session = request.getSession();
            response.setContentType("text/html;charset=UTF-8");

            Connection con = DbTools.connect();
            Statement stmt = con.createStatement();
            String autor = (String) session.getAttribute("username");
            
            String datentyp = request.getParameter("datentyp");
                String methodennamen = request.getParameter("methodenname");

            if ("null".equals(autor) || autor == null) {

                RequestDispatcher rd = request.getRequestDispatcher("neueAufgabe.jsp");

                rd.forward(request, response);
                
            } else {


                String kategorieId = request.getParameter("kategorieId");

                String parametertyp1 = request.getParameter("parameter1");
                String parametername1 = request.getParameter("parametername1");

                String parametertyp2 = request.getParameter("parameter2");
                String parametername2 = request.getParameter("parametername2");

                String parametertyp3 = request.getParameter("parameter3");
                String parametername3 = request.getParameter("parametername3");

                String parametertyp4 = request.getParameter("parameter4");
                String parametername4 = request.getParameter("parametername4");

                String parametertyp5 = request.getParameter("parameter5");
                String parametername5 = request.getParameter("parametername5");

                String parametertyp6 = request.getParameter("parameter6");
                String parametername6 = request.getParameter("parametername6");

                int level = Integer.parseInt((String) request.getParameter("level"));
                int exp = Integer.parseInt((String) request.getParameter("exp"));

                int aufgabenid = 0;

                String beschreibung = request.getParameter("beschreibung");
                if (beschreibung == null || beschreibung.isEmpty()) {
                    beschreibung = "Es ist keine Beschreibung vorhanden.";
                }

                InBetween.InsertAufgabe(stmt, beschreibung, kategorieId, autor, methodennamen, datentyp, level, exp);
                aufgabenid = InBetween.getAufgabenId(stmt, methodennamen);
                int testId = 1;

                InBetween.InsertMethodenParameter(stmt, aufgabenid, parametername1, parametertyp1);
                InBetween.InsertTestParameter(stmt, aufgabenid, testId, parametername1, parametertyp1);
                InBetween.InsertTests(stmt, aufgabenid, testId);

                if (parametertyp2 != null && !"FRESHDUMMYDORE".equals(parametertyp2)) {
                    InBetween.InsertMethodenParameter(stmt, aufgabenid, parametername2, parametertyp2);
                    InBetween.InsertTestParameter(stmt, aufgabenid, testId, parametername2, parametertyp2);
                }
                if (parametertyp3 != null && !"FRESHDUMMYDORE".equals(parametertyp3)) {
                    InBetween.InsertMethodenParameter(stmt, aufgabenid, parametername3, parametertyp3);
                    InBetween.InsertTestParameter(stmt, aufgabenid, testId, parametername3, parametertyp3);
                }
                if (parametertyp4 != null && !"FRESHDUMMYDORE".equals(parametertyp4)) {
                    InBetween.InsertMethodenParameter(stmt, aufgabenid, parametername4, parametertyp4);
                    InBetween.InsertTestParameter(stmt, aufgabenid, testId, parametername4, parametertyp4);
                }
                if (parametertyp5 != null && !"FRESHDUMMYDORE".equals(parametertyp5)) {
                    InBetween.InsertMethodenParameter(stmt, aufgabenid, parametername5, parametertyp5);
                    InBetween.InsertTestParameter(stmt, aufgabenid, testId, parametername5, parametertyp5);
                }
                if (parametertyp6 != null && !"FRESHDUMMYDORE".equals(parametertyp6)) {
                    InBetween.InsertMethodenParameter(stmt, aufgabenid, parametername6, parametertyp6);
                    InBetween.InsertTestParameter(stmt, aufgabenid, testId, parametername6, parametertyp6);
                }

                ///////////////////////////////////////////////////////////////////
                //
                // // // Aufgabe Hinzufügen 
                //
                ///////////////////////////////////////////////////////////////////
                request.setAttribute("methodennamen", methodennamen);

                RequestDispatcher rd = request.getRequestDispatcher("neuerTest.jsp");

                rd.forward(request, response);

            }
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
