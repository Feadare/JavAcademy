package SQL;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Erzeugung von HTML-Konstrukten fuer den Dialog mit Datenbanken.
 *
 * @author origin: rla
 */
public class DbTools {

    /**
     * Verbindung zu einer Datenbank aufbauen. Beim Auftreten von Fehlern
     * erfolgt kein Ausloesen einer Exception. Es wird eine Fehlermeldung auf
     * die Konsole ausgegeben.
     *
     * @return Connection zur Datenbank oder null, wenn ein Fehler auftritt
     */
    public static Connection connect() {
        String url = "jdbc:hsqldb:hsql://localhost/";
        String usr = "SA";
        String passwd = "";
        Connection con = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Keine Treiber-Klasse!");

        }
        try {
            con = DriverManager.getConnection(url, usr, passwd);
        } catch (SQLException ex) {
            System.err.println("SQL Exception");
        }
        return con;
    }

    /**
     * Liefert das Ergebnis einer Abfrage nach allen Attributen einer Tabelle
     * fuer alle Zeilen die eine Suchanfrage erfuellen. Bei Fehlern wird eine
     * Fehlermeldung an die Konsole ausgegeben und null zurueckgegeben.
     *
     * @param con
     * @param table
     * @param attribut
     * @param value
     * @return
     */
    public static ResultSet getResult(Connection con,
            String table,
            String attribut,
            String value) {
        String sql = "SELECT * FROM " + table + " WHERE " + attribut + " LIKE '" + value + "'";
        ResultSet rs = null;
        try (Statement stm = con.createStatement()) {
            rs = stm.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("getResult: ");
            System.err.println("SQL: " + sql);
        }
        return rs;
    }

    public static ResultSet getResultAll(Connection con, String table) {
        String sql = "SELECT * FROM " + table;
        ResultSet rs = null;
        try (Statement stm = con.createStatement()) {
            rs = stm.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("getResult: ");
            System.err.println("SQL: " + sql);
        }
        return rs;
    }

    public static ResultSet getResult(Connection con,
            String table,
            String idName,
            String[] ids) {
        if (con == null || table == null || idName == null || ids == null || ids.length == 0) {
            return null;
        }

        String sql = "SELECT * FROM " + table + " WHERE " + idName + " IN( ";
        for (String id : ids) {
            sql += id + ", ";
        }
        sql = sql.substring(0, sql.length() - 2);
        sql += ")";

        ResultSet rs = null;
        try (Statement stm = con.createStatement()) {
            rs = stm.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("getResult: ");
            System.err.println("SQL: " + sql);
        }
        return rs;
    }

    /**
     * Erzeugt html-Tabellenzeilen mit den Werten der Attribute einer
     * Tabellenzeilen.
     *
     * @param rs SQL-ResultSet
     * @param args Feld fuer Zeichenketten mit variabler Laenge
     * @return
     */
    public static String htmlRows(ResultSet rs, String... args) {
        if (rs == null) {
            return "<tr><td>ResultSet = null</td></tr>\n";
        }
        String table = "";
        try {
            while (rs.next()) { // Alle Ergebniszeilen bearbeiten
                table += "  <tr>";
                for (String arg : args) { // alle gesuchten Attribute
                    String zelle = rs.getString(arg);
                    table += "<td>" + zelle + "</td>";
                }
                table += "</tr>\n";
            }
        } catch (SQLException e) {
            table = "  <tr><td>htmlRows: " + e + "</td></tr>\n";
        }
        return table;
    }

    public static String htmlRowsCheckText(ResultSet rs, String... args) {
        if (rs == null) {
            return "<tr><td>ResultSet = null</td></tr>\n";
        }
        String table = "";
        try {
            while (rs.next()) { // Alle Ergebniszeilen bearbeiten
                table += "  <tr>";
                for (int i = 0; i < args.length; i++) {
                    String arg = args[i];
                    String zelle = rs.getString(arg);
                    if (i == 0) { //Checkbox
                        table += "<td><input type='checkbox' name='Auswahl' value='" + zelle + "'/>";
                    } else { //Attributanzeige
                        table += "<td>" + zelle + "</td>";
                    }
                }
                table += "<td><input type='text' name='Anzahl' value  = '0' size = '2' /></td></tr>\n";
            }
        } catch (SQLException e) {
            table = " <tr><td>htmlRows: " + e + "</td></tr>\n";
        }
        return table;
    }

    /**
     * Erzeugt den Kopf einer HTML-Tabelle<br>
     * z.B htmlTableHead( "firstname","lastname")
     *
     *
     * @param args Feld fuer Zeichenketten mit variabler Laenge
     * @return
     */
    public static String htmlTableHead(String... args) {
        String table = "<table class=\"table\" border='1'>\n  <tr><b>";
        for (String arg : args) {
            table += "<td>" + arg + "</td>";
        }
        return table + "</b></tr>\n";
    }

    public static String htmlTableFoot() {
        return "</table>\n";
    }

    public static String exportDb(Connection con) {
        String sqlBefehle = "DROP TABLE HINWEISE if exists;\n"
                + "\n"
                + "DROP TABLE Userkurse IF EXISTS\n"
                + "DROP TABLE Kurs IF EXISTS\n"
                + "DROP TABLE TestParam IF EXISTS\n"
                + "DROP TABLE Tests IF EXISTS\n"
                + "DROP TABLE Userlevel IF EXISTS\n"
                + "\n"
                + "DROP TABLE Eingaben IF EXISTS\n"
                + "DROP TABLE user IF EXISTS\n"
                + "DROP TABLE Methodenparameter IF EXISTS\n"
                + "DROP TABLE Aufgaben IF EXISTS\n"
                + "DROP TABLE Kategorien IF EXISTS\n"
                + "DROP TABLE Rang IF EXISTS\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "CREATE TABLE user( \n"
                + "ID integer GENERATED BY DEFAULT AS IDENTITY,\n"
                + "username VARCHAR(40),\n"
                + "passwort INTEGER,\n"
                + "email VARCHAR(100),\n"
                + "usergruppe VARCHAR(50) DEFAULT 'schueler',\n"
                + "PRIMARY KEY(ID));\n"
                + "\n"
                + "CREATE TABLE Rang(\n"
                + "level integer,\n"
                + "rangname VARCHAR(50),\n"
                + "PRIMARY KEY(level));\n"
                + "\n"
                + "CREATE TABLE userlevel(\n"
                + "userID integer GENERATED BY DEFAULT AS IDENTITY,\n"
                + "aktLvl integer,\n"
                + "aktExp integer,\n"
                + "PRIMARY KEY(userid),\n"
                + "FOREIGN KEY (userid) REFERENCES user(ID),\n"
                + "FOREIGN KEY (aktLvl) REFERENCES Rang(level));\n"
                + "\n"
                + "\n"
                + "CREATE TABLE Kategorien(\n"
                + "ID INTEGER GENERATED BY DEFAULT AS IDENTITY,\n"
                + "kategoriename VARCHAR(100),\n"
                + "PRIMARY KEY(id))\n"
                + "\n"
                + "CREATE TABLE Aufgaben(\n"
                + "ID INTEGER GENERATED BY DEFAULT AS IDENTITY,\n"
                + "beschreibung VARCHAR(20000),\n"
                + "kategorieID INTEGER,\n"
                + "autor VARCHAR(100),\n"
                + "methodenname VARCHAR(100),\n"
                + "Datentyp VARCHAR(30),\n"
                + "mindestlevel INTEGER,\n"
                + "gegEXP INTEGER,\n"
                + "PRIMARY KEY(id),\n"
                + "FOREIGN KEY (kategorieID) REFERENCES Kategorien(ID),\n"
                + "FOREIGN KEY (mindestlevel) REFERENCES Rang(level));\n"
                + "\n"
                + "CREATE TABLE Eingaben(\n"
                + "AufgabenID INTEGER,\n"
                + "UserID INTEGER,\n"
                + "text VARCHAR(20000),\n"
                + "geloest BOOLEAN DEFAULT 'FALSE',\n"
                + "PRIMARY KEY(aufgabenid, userid),\n"
                + "FOREIGN KEY (UserID) REFERENCES user(ID),\n"
                + "FOREIGN KEY (AufgabenID) REFERENCES Aufgaben(ID));\n"
                + "\n"
                + "\n"
                + "CREATE TABLE MethodenParameter(\n"
                + "aufgabenID Integer,\n"
                + "paraname VARCHAR(30),\n"
                + "Datentyp VARCHAR(30),\n"
                + "PRIMARY KEY(aufgabenID, paraname),\n"
                + "FOREIGN KEY (aufgabenID) REFERENCES Aufgaben(ID));\n"
                + "\n"
                + "CREATE TABLE Tests(\n"
                + "AufgabenID INTEGER,\n"
                + "testID INTEGER,\n"
                + "erwtErg VARCHAR(20000),\n"
                + "ergHash INTEGER,\n"
                + "PRIMARY KEY(testID, aufgabenID),\n"
                + "FOREIGN KEY (AufgabenID) REFERENCES Aufgaben(ID));\n"
                + "\n"
                + "CREATE TABLE TestParam(\n"
                + "AufgabenID INTEGER,\n"
                + "testID INTEGER,\n"
                + "paraname VARCHAR(200),\n"
                + "typ VARCHAR(200),\n"
                + "wert VARCHAR(20000),\n"
                + "PRIMARY KEY (AufgabenID, testID, paraname),\n"
                + "FOREIGN KEY (paraname, AufgabenID) REFERENCES MethodenParameter(paraname, AufgabenID));\n"
                + "\n"
                + "CREATE TABLE Hinweise(\n"
                + "Hinweis VarChar(200) DEFAULT '',\n"
                + "AufgabenID INTEGER,\n"
                + "PRIMARY KEY (Hinweis),\n"
                + "FOREIGN KEY (AufgabenID) REFERENCES Aufgaben(ID)\n"
                + ");\n"
                + "\n";
        sqlBefehle += exportUserTabelle(con) + "\n";
        sqlBefehle += "INSERT INTO Rang VALUES ('1', 'Neuling');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('2', 'Copy-Pasteter');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('3', 'Neuling Abstract Class');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('4', 'Neuling First Class');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('5', 'Lehrling I');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('6', 'Lehrling II');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('7', 'Lehrling III');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('8', 'Lehrling IIII');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('9', 'Wichtel Code');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('10', 'Variabel Fee');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('11', 'Klassen Raider');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('12', 'Quellcode Prädiger');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('13', 'Code Beschwörer' );\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('14', 'Bit Priester');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('15', 'Error Schamane');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('16', 'Dunkler Coder');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('17', 'JavAkrobat');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('18', 'Daten Jongleur');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('19', 'Konstruktor');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('20', 'Mächtiger Java Meister');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('21', 'Überlegener Server Admin');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('22', 'Programier Naturtalent');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('23', 'Übermenschlicher Coder');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('24', '0101011101100101011100110110010101101110');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('25', 'Binäres Wesen');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('26', 'Infernaler Daten Dämon');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('27', 'Lucifer des Codes');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('28', 'Heiliger Code Engel');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('29', 'Erz-Engel des Programierens');\n"
                + "\n"
                + "INSERT INTO Rang VALUES ('30', 'Programmier Gottheit');";
        sqlBefehle += exportUserlevelTabelle(con) + "\n";
        sqlBefehle += "INSERT INTO Kategorien VALUES ('1', 'Basics');"
                + "INSERT INTO Kategorien VALUES ('2', 'Fallunterscheidungen');\n"
                + "\n"
                + "INSERT INTO Kategorien VALUES ('3', 'Schleifen');\n"
                + "\n"
                + "INSERT INTO Kategorien VALUES ('4', 'Strings');\n"
                + "\n"
                + "INSERT INTO Kategorien VALUES ('5', 'Logik');\n"
                + "\n"
                + "INSERT INTO Kategorien VALUES ('6', 'Zahlen');\n"
                + "\n"
                + "INSERT INTO Kategorien VALUES ('7', 'Arrays');\n"
                + "\n"
                + "INSERT INTO Kategorien VALUES ('8', 'Felder');\n"
                + "\n"
                + "INSERT INTO Kategorien VALUES ('9', 'Fortgeschrittene');\n"
                + "\n"
                + "INSERT INTO Kategorien VALUES ('10', 'Andere');" + "\n";
        sqlBefehle += exportAufgTabelle(con) + "\n";
        sqlBefehle += exportEingTabelle(con) + "\n";
        sqlBefehle += exportMethodenparamTabelle(con) + "\n";
        sqlBefehle += exportTestsTabelle(con) + "\n";
        sqlBefehle += exportTestparamTabelle(con) + "\n";
        try {
            PrintStream out = new PrintStream(new FileOutputStream("javaacademydbbackup.sql"));
            out.println(sqlBefehle);
            out.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        
        return sqlBefehle;
    }

    public static String exportAufgTabelle(Connection con) {
        String sql = "SELECT * FROM aufgaben";
        String sqlExp = "";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                sqlExp += "INSERT INTO Aufgaben VALUES ('" + rs.getInt(1) + "',"
                        + "'" + rs.getString(2) + "',"
                        + "'" + rs.getInt(3) + "',"
                        + "'" + rs.getString(4) + "',"
                        + "'" + rs.getString(5) + "',"
                        + "'" + rs.getString(6) + "',"
                        + "'" + rs.getString(7) + "',"
                        + "'" + rs.getString(8) + "');";
            }
        } catch (SQLException e) {
            System.out.println("SQL-FEHLER: " + e);
        }
        return sqlExp;
    }

    public static String exportEingTabelle(Connection con) {
        String sql = "SELECT * FROM Eingaben";
        String sqlExp = "";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                sqlExp += "INSERT INTO Eingaben VALUES ('" + rs.getInt(1) + "',"
                        + "'" + rs.getInt(2) + "',"
                        + "'" + rs.getString(3) + "',"
                        + "'" + rs.getBoolean(4) + "');";
            }
        } catch (SQLException e) {
            System.out.println("SQL-FEHLER: " + e);
        }
        return sqlExp;
    }

    public static String exportMethodenparamTabelle(Connection con) {
        String sql = "SELECT * FROM Methodenparameter";
        String sqlExp = "";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                sqlExp += "INSERT INTO Methodenparameter VALUES ('" + rs.getInt(1) + "',"
                        + "'" + rs.getString(2) + "',"
                        + "'" + rs.getString(3) + "');";
            }
        } catch (SQLException e) {
            System.out.println("SQL-FEHLER: " + e);
        }
        return sqlExp;
    }

    public static String exportTestparamTabelle(Connection con) {
        String sql = "SELECT * FROM Testparam";
        String sqlExp = "";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                sqlExp += "INSERT INTO TestParam VALUES ('" + rs.getInt(1) + "',"
                        + "'" + rs.getInt(2) + "',"
                        + "'" + rs.getString(3) + "',"
                        + "'" + rs.getString(4) + "',"
                        + "'" + rs.getString(5) + "');";
            }
        } catch (SQLException e) {
            System.out.println("SQL-FEHLER: " + e);
        }
        return sqlExp;
    }

    public static String exportTestsTabelle(Connection con) {
        String sql = "SELECT * FROM Tests";
        String sqlExp = "";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                sqlExp += "INSERT INTO Tests VALUES ('" + rs.getInt(1) + "',"
                        + "'" + rs.getInt(2) + "',"
                        + "'" + rs.getString(3) + "',"
                        + "'" + rs.getInt(4) + "');";
            }
        } catch (SQLException e) {
            System.out.println("SQL-FEHLER: " + e);
        }
        return sqlExp;
    }

    public static String exportUserTabelle(Connection con) {
        String sql = "SELECT * FROM user";
        String sqlExp = "";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                sqlExp += "INSERT INTO user VALUES ('" + rs.getInt(1) + "',"
                        + "'" + rs.getString(2) + "',"
                        + "'" + rs.getInt(3) + "',"
                        + "'" + rs.getString(4) + "',"
                        + "'" + rs.getString(5) + "');";
            }
        } catch (SQLException e) {
            System.out.println("SQL-FEHLER: " + e);
        }
        return sqlExp;
    }

    public static String exportUserlevelTabelle(Connection con) {
        String sql = "SELECT * FROM userlevel";
        String sqlExp = "";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                sqlExp += "INSERT INTO userlevel VALUES ('" + rs.getInt(1) + "',"
                        + "'" + rs.getInt(2) + "',"
                        + "'" + rs.getInt(3) + "');";
            }
        } catch (SQLException e) {
            System.out.println("SQL-FEHLER: " + e);
        }
        return sqlExp;
    }

    /**
     * Beendet eine Verbindung zur Datenbank falls das moeglich und notwendig
     * ist.
     *
     * @param con Verbindung zur Datenbank (darf auch null oder closed sein)
     * @return true, wenn die Verbindung erst durch diese Methode geschlossen
     * wurde
     */
    public static boolean disconnect(Connection con) {
        boolean done = false;
        if (con != null) {
            try {
                if (!con.isClosed()) {
                    con.close();
                    done = true;
                }
            } catch (SQLException e) {
                System.err.println("Exception in disconnect: " + e);
            }
        }
        return done;
    }

    //LABER?
}
