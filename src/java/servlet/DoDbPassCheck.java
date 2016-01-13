package servlet;

import framework.Benutzer;
import SQL.DbTools;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet fuer die Passwortaenderung.
 * 
 */
public class DoDbPassCheck extends HttpServlet {

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

        HttpSession session = request.getSession();

        Connection con = DbTools.connect();
        String konsole = "";
        String username = (String) session.getAttribute("username");

        int id = framework.Benutzer.getIDbyName(username, con);

        String newpswd = request.getParameter("newpswd");
        String newpswdok = request.getParameter("newpswdok");
        String altPass = request.getParameter("altpswd");

        char[] caltPass = request.getParameter("altpswd").toCharArray();
        boolean dbPass = Benutzer.checkPassword(username, caltPass, con);
        RequestDispatcher rd;

        if (dbPass) {
            if (Benutzer.pswdAendern(altPass, newpswd, newpswdok, id, con)) {
                konsole = "<div class=\"alert alert-success\" role=\"alert\"> "
                        + "Security++;</div>";
            } else {
                konsole = "<div class=\"alert alert-danger\" role=\"alert\">FEHLGESCHLAGEN " + "</div>";
            }
            request.setAttribute("konsole", konsole);
            rd = request.getRequestDispatcher("profilbearbeiten.jsp");
        } else {
            request.setAttribute("dbCheck", "false");
            rd = request.getRequestDispatcher("profilbearbeiten.jsp");
        }
        request.setAttribute("konsole", konsole);
        rd.forward(request, response);

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
