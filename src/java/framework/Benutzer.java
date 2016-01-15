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
 *
 * @author feadare
 */
public class Benutzer {

    /**
     * Traegt die eingegebenen Nutzerdaten in die Datenbank ein SQL-Tabellen:
     * User, UserLevel
     *
     * @param u_ID UserID
     * @param pass Passwort
     * @param email E-Mail-Adresse
     * @param stmt SQL-Statement
     * @return Erfolgreich angemeledet?
     */
    public static boolean registrieren(String u_ID, String pass, String email, Statement stmt) {
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
     *
     * @param user Username
     * @param passwort Passwort
     * @return Passwort in gehashter Form
     */
    public static int getHash(String user, char[] passwort) {
        return (user + "squids" + Arrays.toString(passwort)).hashCode();
    }

    /**
     * Gibt den Hashcode des Passworts zurueck fuer einen User
     *
     * @param user Der Name des Users
     * @param con Connection zur Datenbank
     * @return Passwort in gehashter Form
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
     * Ueberprueft das eingegebene Passwort und das gespeicherte in der
     * Datenbank auf Gleichheit
     *
     * @param user Benutzername
     * @param password eingegebenes Passwort
     * @param con SQL-Connection
     * @return true falls Passwort richtig ist
     */
    public static boolean checkPassword(String user, char[] password, Connection con) {
        int dbPwHash = getPassword(user, con);
        int entPwHash = getHash(user, password);

        return dbPwHash == entPwHash;
    }
    /**
     * Liest alle Usernamen aus der Datenbank aus
     * @param con SQL-Connection
     * @return ArrayList mit allen Usernamen
     */
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
    /**
     * Ueberprueft ob ein Username bereits vergeben ist
     * @param con SQL-Connection
     * @param username der eingegebene Username
     * @return true falls Name frei ist
     */
    public static boolean checkUsername(String username, Connection con) {
        ArrayList<String> userListe = getNameliste(con);
        if (userListe.contains(username)) {
            return false;
        } else {
            return true;
        }
    }
   /**
     * Uebergibt die ID eines Users anhand seines Usernamens
     * @param con SQL-Connection
     * @param username der eingegebene Username
     * @return ID des Users
     */
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
   /**
     * Aendert das Passwort des Users
     * @param con SQL-Connection
     * @param id ID des Users
     * @param eingabe Passwort eingabe des Users
     * @param newPswd1 neues Passwort
     * @param newPswd2 neues Passwort(Überprüfung)
     * @return true, falls erfolgreich
     */
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
   /**
     * Aendert die E-Mail eines Users 
     * @param con SQL-Connection
     * @param id ID des Users dessen Email geaendert werden soll
     * @param newmail die neue E-mail
     * 
     */
    public static void emailAendern(int id, Connection con, String newmail) {

        String sql = "Update user Set email = '" + newmail + "' where ID = " + id;

        try (Statement stm = con.createStatement()) {
            stm.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("getResult: " + e);
            System.err.println("SQL: " + sql);
        }
    }

    /**
     * Fuer das Levelsystem<br>
     * Gibt das aktuelle Level eines Users zurueck
     *
     * @param stmt SQL-Statement
     * @param u_ID UserID
     * @return aktuelles Level
     */
    public static int getUserLevel(Statement stmt, int u_ID) {
        String sql = "SELECT * from UserLevel where UserID = " + u_ID;
        int lvl = 0;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            lvl = rs.getInt("aktLvl");
        } catch (SQLException ex) {
            System.err.println("Fehler: " + ex);
        }
        return lvl;
    }

    /**
     * Fuer das Levelsystem<br>
     * Gibt die Erfahrungspunkte(EXP) eines Users zurueck.
     *
     * @param stmt Das Statement dass fuer die Query benutzt wird
     * @param u_ID Die User ID
     * @return Aktuelle Exp
     */
    public static int getUserExp(Statement stmt, int u_ID) {
        String sql = "SELECT * from UserLevel where UserID = " + u_ID;
        int exp = 0;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            exp = rs.getInt("aktExp");
        } catch (SQLException ex) {
            System.err.println("Fehler: " + ex);
        }

        return exp;
    }

    /**
     * Fuer die erfolgreiche Absolvierung einer Aufgabe werden ihm
     * Erfahrungspunkte(EXP) gutgeschrieben<br>
     * Bei 100 Punkten wird er um einen Rang erhöht
     *
     * @param stmt SQL-Statement
     * @param u_ID UserID
     * @param a_ID AufgabenID
     */
    public static void addExp(Statement stmt, int u_ID, int a_ID) {
        String sql = "SELECT * from UserLevel where UserID = " + u_ID;
        int exp, nexp = 0;

        try {
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            exp = rs.getInt("aktExp");
            nexp = exp + Aufgabe.getEXP(stmt, a_ID);
            String sqlUpdate = "";
            if (nexp >= 100) { // Bei 100 steigt der Nutzer auf
                rs = stmt.executeQuery("SELECT aktLvl from UserLevel where UserID = " + u_ID);
                rs.next();
                int nLvl = rs.getInt(1);
                nLvl++;
                if (nLvl >= 30) {
                    stmt.executeQuery("UPDATE UserLevel SET AKTLvl = 30 WHERE UserID = " + u_ID);
                    stmt.executeQuery("UPDATE UserLevel SET AktExp = 100 WHERE UserID = " + u_ID);

                } else {
                    sqlUpdate = "UPDATE UserLevel SET AKTEXP = 0 WHERE UserID = " + u_ID;
                    String sqlNewlvl = "UPDATE UserLevel SET AKTLvl = " + nLvl + " WHERE UserID = " + u_ID;
                    stmt.executeQuery(sqlNewlvl);
                }
            } else {
                sqlUpdate = "UPDATE UserLevel SET AKTEXP = " + nexp + " WHERE UserID = " + u_ID;
            }
            stmt.executeQuery(sqlUpdate);
        } catch (SQLException ex) {
            System.err.println("Fehler: " + ex);
        }
    }
}
