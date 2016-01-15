package servlet;

import SQL.DbTools;
import SQL.InBetween;
import framework.Parameter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet fuer das hinzufuegen eines neuen Tests.
 */
public class DoInsertTest extends HttpServlet {

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
            String paraname = "";
            String typ = "";

            String aufgabenid1 = request.getParameter("aufgabenid"); //(int) session.getAttribute("aufgabenid");
            int aufgabenid = Integer.parseInt(aufgabenid1);

            String anzahlParameter1 = request.getParameter("anzahlParameter"); //(int) session.getAttribute("aufgabenid");
            int anzahlParameter = Integer.parseInt(anzahlParameter1);

            String ergebnis = request.getParameter("result");
            String methodenname = request.getParameter("methodenname");

            String testidString = request.getParameter("testid");
            int testId = Integer.parseInt(testidString);

            String parameter1, parameter2, parameter3, parameter4, parameter5, parameter6 = null;
           
            ArrayList<Parameter> aufgabenParameter = InBetween.getAufgabenParameter(stmt, aufgabenid);

            InBetween.updateTests(stmt, aufgabenid, testId, ergebnis);

            if (anzahlParameter == 1) {
                parameter1 = request.getParameter("parameter1");
                paraname = aufgabenParameter.get(0).getParameter();
                typ = aufgabenParameter.get(0).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter1, paraname, typ);
            }

            if (anzahlParameter == 2) {

                parameter1 = request.getParameter("parameter1");
                paraname = aufgabenParameter.get(0).getParameter();
                typ = aufgabenParameter.get(0).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter1, paraname,typ);

                parameter2 = request.getParameter("parameter2");
                paraname = aufgabenParameter.get(1).getParameter();
                typ = aufgabenParameter.get(1).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter2, paraname,typ);
            }
            if (anzahlParameter == 3) {
                parameter1 = request.getParameter("parameter1");
                paraname = aufgabenParameter.get(0).getParameter();
                typ = aufgabenParameter.get(0).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter1, paraname, typ);
                parameter2 = request.getParameter("parameter2");
                paraname = aufgabenParameter.get(1).getParameter();
                typ = aufgabenParameter.get(1).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter2, paraname,typ);
                parameter3 = request.getParameter("parameter3");
                paraname = aufgabenParameter.get(2).getParameter();
                typ = aufgabenParameter.get(2).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter3, paraname,typ);
            }
            if (anzahlParameter == 4) {
                parameter1 = request.getParameter("parameter1");
                paraname = aufgabenParameter.get(0).getParameter();
                typ = aufgabenParameter.get(0).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter1, paraname, typ);
                parameter2 = request.getParameter("parameter2");
                paraname = aufgabenParameter.get(1).getParameter();
                typ = aufgabenParameter.get(1).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter2, paraname,typ);
                parameter3 = request.getParameter("parameter3");
                paraname = aufgabenParameter.get(2).getParameter();
                typ = aufgabenParameter.get(2).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter3, paraname,typ);
                parameter4 = request.getParameter("parameter4");
                paraname = aufgabenParameter.get(3).getParameter();
                typ = aufgabenParameter.get(3).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter4, paraname,typ);
            }
            if (anzahlParameter == 5) {
                parameter1 = request.getParameter("parameter1");
                paraname = aufgabenParameter.get(0).getParameter();
                typ = aufgabenParameter.get(0).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter1, paraname, typ);
                parameter2 = request.getParameter("parameter2");
                paraname = aufgabenParameter.get(1).getParameter();
                typ = aufgabenParameter.get(1).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter2, paraname,typ);
                parameter3 = request.getParameter("parameter3");
                paraname = aufgabenParameter.get(2).getParameter();
                typ = aufgabenParameter.get(2).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter3, paraname,typ);
                parameter4 = request.getParameter("parameter4");
                paraname = aufgabenParameter.get(3).getParameter();
                typ = aufgabenParameter.get(3).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter4, paraname,typ);
                parameter5 = request.getParameter("parameter5");
                paraname = aufgabenParameter.get(4).getParameter();
                typ = aufgabenParameter.get(4).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter5, paraname,typ);
            }
            if (anzahlParameter == 6) {
                parameter1 = request.getParameter("parameter1");
                paraname = aufgabenParameter.get(0).getParameter();
                typ = aufgabenParameter.get(0).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter1, paraname,typ);
                parameter2 = request.getParameter("parameter2");
                paraname = aufgabenParameter.get(1).getParameter();
                typ = aufgabenParameter.get(1).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter2, paraname,typ);
                parameter3 = request.getParameter("parameter3");
                paraname = aufgabenParameter.get(2).getParameter();
                typ = aufgabenParameter.get(2).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter3, paraname,typ);
                parameter4 = request.getParameter("parameter4");
                paraname = aufgabenParameter.get(3).getParameter();
                typ = aufgabenParameter.get(3).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter4, paraname,typ);
                parameter5 = request.getParameter("parameter5");
                paraname = aufgabenParameter.get(4).getParameter();
                typ = aufgabenParameter.get(4).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter5, paraname,typ);
                parameter6 = request.getParameter("parameter6");
                paraname = aufgabenParameter.get(5).getParameter();
                typ = aufgabenParameter.get(5).getDatentyp();
                InBetween.updateTestparameter(stmt, aufgabenid, testId, parameter6, paraname,typ);
            }

            request.setAttribute("methodennamen", methodenname);
            RequestDispatcher rd = request.getRequestDispatcher("neuerTest.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DoInsertTest.class.getName()).log(Level.SEVERE, null, ex);
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
