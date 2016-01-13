/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import SQL.InBetween;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Get und Set Methoden fuer die USER-SQL-Tabelle
 * @author feadare
 */
public class Benutzer {

    /**
     * Traegt die eingegebenen Nutzerdaten in die Datenbank ein
     * SQL-Tabellen: User, UserLevel
     * 
     * @param u_ID UserID
     * @param pass Passwort
     * @param email E-Mail-Adresse
     * @param stmt SQL-Statement
     * @return Erfolgreich angemeledet?
     */
    public static boolean registrieren(String u_ID, String pass, String email, Statement stmt){
        boolean erfolg = false;

        try {
            String sqlUser = "INSERT INTO USER VALUES (DEFAULT,'" + u_ID
                    + "','" + getHash(u_ID, pass.toCharArray()) + "','" + email + "', DEFAULT)";
            String sqlLevel = "INSERT INTO USERLEVEL (aktLvl,aktExp) VALUES (1,0)";
            stmt.executeQuery(sqlUser);

            stmt.executeQuery(sqlLevel);

            erfolg = true;

        } catch (SQLException e) {
            System.err.println("FEHLER: " + e);
        }
        return erfolg;
    }

    /**
     * Erzeugt einen Hashcode aus einem usernamen und einer passwort Eingabe
     * @param user Username
     * @param passwort Passwort
     * @return 
     */
    public static int getHash(String user, char[] passwort) {
        return (user + "squids" + Arrays.toString(passwort)).hashCode();
    }

    /**
     * Gibt den Hashcode des Passworts zurueck fuer einen User
     * @param user
     * @param con
     * @return 
     */
    public static int getPassword(String user, Connection con) {
        String sql = "SELECT passwort FROM user WHERE username='" + user + "'";
        int passwd = 0;
        Statement stmt = null;

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            passwd = rs.getInt("passwort");
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Kein Zugriff");
        }

        return passwd;
    }

    /**
     * Ueberprueft das eingegebene Passwort und das gespeicherte in der Datenbank auf Gleichheit
     * @param user Benutzername
     * @param password eingegebenes Passwort
     * @param con SQL-Connection
     * @return 
     */
    public static boolean checkPassword(String user, char[] password, Connection con) {
        int dbPwHash = getPassword(user, con);
        int entPwHash = getHash(user, password);

        return dbPwHash == entPwHash;
    }

    public static ArrayList<String> getNameliste(Connection con) {

        String sql = "SELECT username FROM user";
        ResultSet rs = null;
        ArrayList<String> userlist = new ArrayList<>();
        try (Statement stm = con.createStatement()) {
            rs = stm.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("getResult: ");
            System.err.println("SQL: " + sql);
        }
        
        try {
            while (rs.next()) {
                String username = rs.getString(1);
                userlist.add(username);
            }
        } catch (SQLException ex) {
            System.err.println("getResult: ");
            System.err.println("SQL: " + sql);
        }

        return userlist;

    }

    public static boolean checkUsername(String username, Connection con) {
        ArrayList<String> userListe = getNameliste(con);
        if (userListe.contains(username)) {
            return false;
        } else {
            return true;
        }
    }

    public static int getIDbyName(String username, Connection con) {
        String sql = "SELECT ID FROM user WHERE username='" + username + "'";
        int id = 0;
        Statement stmt = null;

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            id = rs.getInt("ID");
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Kein Zugriff");
        }

        return id;
    }

    public static boolean pswdAendern(String eingabe, String newPswd1, String newPswd2, int id, Connection con) {
        if (newPswd1 == null || newPswd1.isEmpty() || newPswd2 == null || newPswd2.isEmpty()) {
            return false;
        }
        String sql = "";
        try (Statement stm = con.createStatement()) {
            String user = InBetween.myName(stm, id);
            int dbpswd = InBetween.myHashpswd(stm, id);
            int inpswd1 = getHash(user, newPswd1.toCharArray());
            int ieingabe = getHash(user, eingabe.toCharArray());
            int inpswd2 = getHash(user, newPswd2.toCharArray());

            if (ieingabe == dbpswd) {

                if (inpswd1 == inpswd2) {
                    sql = "Update user Set passwort = " + inpswd1 + "where ID = " + id;

                    stm.executeQuery(sql);
                    return true;

                }
            }
        } catch (SQLException e) {
            System.err.println("getResult: ");
            System.err.println("SQL: " + sql);
        }
        return false;
    }

    public static void emailAendern(int id, Connection con, String newmail) {

        String sql = "Update user Set email = '" + newmail + "' where ID = " + id;

        try (Statement stm = con.createStatement()) {
            stm.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("getResult: " + e);
            System.err.println("SQL: " + sql);
        }

    }
}
