/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import SQL.DbTools;
import SQL.InBetween;
import compiler.EingabenCompiler;
import framework.AbgeschlossenerTest;
import framework.Benutzer;
import framework.Hilfsmethoden;
import framework.Parameter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Ist das Compiler Servlet fuer das pruefen eines Tests auf der neuen Test Seite.
 */
public class DoTestCompiler extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            Connection con = DbTools.connect();
            Statement stmt = con.createStatement();

            String methodenname = request.getParameter("methodenname");
            String parameterUebersicht = request.getParameter("parameterUebersicht");

            int aufgabenid = InBetween.getAufgabenId(stmt, methodenname);

            String datentyp = InBetween.getDatentyp(stmt, aufgabenid);

            ArrayList<Parameter> aufgabenParameter = InBetween.getAufgabenParameter(stmt, aufgabenid);
            String parameterString = "";
            for (Parameter parameter1 : aufgabenParameter) {
                parameterString += parameter1;
                parameterString += ", ";
            }

            parameterString = parameterString.substring(0, parameterString.length() - 2);

            String eingabe = request.getParameter("code");
            ArrayList<AbgeschlossenerTest> testsDoneList = EingabenCompiler.getErgebnis(eingabe, datentyp, methodenname, parameterString, aufgabenid);
            String uErgebnis = "";
            if (testsDoneList.get(0).isNotcompiled()) {
                uErgebnis = Hilfsmethoden.getDiagnosticsTabelle(testsDoneList.get(0).getDiagnostics());
            } else {
                uErgebnis = Hilfsmethoden.getTestErgebnisTabelle(testsDoneList);
            }

            boolean alleErfolgreich = true;
            for (AbgeschlossenerTest test : testsDoneList) {
                if (!test.isSuccess()) {
                    alleErfolgreich = false;
                }
            }

            request.setAttribute("methodennamen", methodenname);
            request.setAttribute("parameterUebersicht", parameterUebersicht);
            request.setAttribute("uErgebnis", uErgebnis);

            RequestDispatcher rd = request.getRequestDispatcher("neuerTest.jsp");
            rd.forward(request, response);

        } catch (SQLException ex) {
            System.err.println("SQL-Fehler " + ex);
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
