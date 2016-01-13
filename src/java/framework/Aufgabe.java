/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author wilmanm
 */
public class Aufgabe {

    /**
     * Gibt den Erfahrungswert zurueck bei erfolgreicher Absolvierung
     *
     * @param stmt SQL-Statement
     * @param a_ID AufgabenID
     * @return Erfahrungspunkte
     */
    public static int getEXP(Statement stmt, int a_ID) {
        int exp = 0;
        String sql = "SELECT GEGEXP FROM AUFGABEN WHERE ID =" + a_ID + ";";

        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                exp = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println("FEHLER: " + ex);
        }

        return exp;
    }

    /**
     * Gibt den Autor einer Aufgabe zurueck
     *
     * @param stmt SQL-Statement
     * @param a_ID AufgabenID
     * @return
     */
    public static String getAutor(Statement stmt, int a_ID) {
        String autor = "";
        String sql = "SELECT AUTOR FROM AUFGABEN WHERE ID =" + a_ID + ";";

        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                autor = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.err.println("FEHLER: " + ex);
        }

        return autor;
    }

    /**
     * Gibt das Mindest-Level zurueck
     *
     * @param stmt SQL-Statement
     * @param a_ID AufgabenID
     * @return
     */
    public static int getMinLevel(Statement stmt, int a_ID) {
        int minLevel = 0;
        String sql = "SELECT MindestLevel FROM AUFGABEN WHERE ID =" + a_ID + ";";

        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                minLevel = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println("FEHLER: " + ex);
        }
        return minLevel;
    }

    public static String getHinweis(Statement stmt, int a_ID) {
        String sql = "SELECT Hinweis FROM Hinweise WHERE AufgabenID =" + a_ID + ";";
        String hinweis = "";
        
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                hinweis = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.err.println("FEHLER: " + ex);
        }
        return hinweis;
    }
}
