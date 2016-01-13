package servlet;

import compiler.EingabenCompiler;
import framework.AbgeschlossenerTest;
import SQL.DbTools;
import SQL.InBetween;
import framework.Benutzer;
import framework.Hilfsmethoden;
import framework.Parameter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.concurrent.Task;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author feadare
 */
public class DoCompiler extends HttpServlet {

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

            String sAuf_ID = (String) request.getParameter("a_ID");
            int aufgabe_id = Integer.parseInt(sAuf_ID);

            //--------------------------------------------------------------------------------------------------
            String aufgabenbeschreibung = InBetween.getAufgabenbeschreibung(stmt, aufgabe_id);
            String methodenname = InBetween.getMethodennamen(stmt, aufgabe_id);
            String datentyp = InBetween.getDatentyp(stmt, aufgabe_id);

            ArrayList<Parameter> aufgabenParameter = InBetween.getAufgabenParameter(stmt, aufgabe_id);
            String parameterString = "";
            for (Parameter parameter1 : aufgabenParameter) {
                parameterString += parameter1;
                parameterString += ", ";
            }

            parameterString = parameterString.substring(0, parameterString.length() - 2);

            String eingabe = request.getParameter("code");
            ArrayList<AbgeschlossenerTest> testsDoneList = new ArrayList();
            testsDoneList = EingabenCompiler.getErgebnis(eingabe, datentyp, methodenname, parameterString, aufgabe_id);
           

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

            if (session.getAttribute("username") != null) { //wenn eingeloggt

                String username = (String) session.getAttribute("username");
                int user_id = Benutzer.getIDbyName(username, con);
                boolean isupdate = InBetween.isCodeUpdate(stmt, user_id, aufgabe_id);

                InBetween.saveCode(eingabe, user_id, aufgabe_id, stmt, alleErfolgreich, isupdate);
            }

            request.setAttribute(datentyp, datentyp);

            request.setAttribute("code", eingabe);
            request.setAttribute("uErgebnis", uErgebnis);
            request.setAttribute("aufgabenbeschreibung", aufgabenbeschreibung);
            request.setAttribute("id", aufgabe_id);

            RequestDispatcher rd = request.getRequestDispatcher("aufgabenVorlage.jsp");
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
