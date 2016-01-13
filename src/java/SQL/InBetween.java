package SQL;

import framework.Aufgabe;
import framework.Parameter;
import framework.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Das Bindeglied zwischen der Datenbank und der Ausgabe<br>
 * Hier werden beispielsweise Daten aus der Datenbank in HTML-Tabellen
 * ausgegeben.
 */
public class InBetween {

    /**
     * Gibt die Aufgabenbeschreibung aus der Datenbank zurueck<br>
     * Tabelle: AUFGABEN
     *
     * @param stmt SQL-Statement
     * @param id AufgabenID
     * @return
     */
    public static String getAufgabenbeschreibung(Statement stmt, int id) {
        String aufgabenstellung = "AUFGABE NICHT GEFUNDEN!";

        String sql = "SELECT beschreibung from Aufgaben where ID = " + id + ";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                aufgabenstellung = rs.getString(1);

            }
        } catch (SQLException e) {
            System.out.println("SQL-FEHLER: " + e);
        }
        return aufgabenstellung;
    }

    public static ArrayList<Test> getTests(Statement stmt, int id) {
        Object loesungswert = "AUFGABE NICHT GEFUNDEN!";
        ArrayList<Test> testListe = new ArrayList();
        String sql = "SELECT * FROM Tests WHERE aufgabenID = " + id + ";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            int i = 1;
            while (rs.next()) {
                loesungswert = rs.getString("ErwtErg");
                ArrayList<Object> werteListe = getTestWerte(stmt, id, i);
                Test test1 = new Test(loesungswert, werteListe.toArray());
                testListe.add(test1);
                i++;
            }
        } catch (SQLException e) {
            System.out.println("SQL-FEHLER: " + e);
        }
        return testListe;
    }

    public static ArrayList<Object> getTestWerte(Statement stmt, int id, int testid) {
        ArrayList<Object> werteListe = new ArrayList();

        String sql = "SELECT typ "
                + "FROM Testparam where testID= " + testid + " AND aufgabenid = " + id + ";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            int i = 1;
            while (rs.next()) {
                //  Object wert = rs.getString("Wert");
                Object wert = null;
                String typ = rs.getString("Typ");
//               int test;
//                test = Integer.parseInt(wert);
                wert = getParsedObject(stmt, typ, id, testid, i);
                werteListe.add(wert);
                i++;
            }
        } catch (SQLException e) {
            System.err.println("SQL-FEHLER: " + e);

        }

        return werteListe;
    }

    public static Object getParsedObject(Statement stmt, String typ, int id, int testid, int i) {
        Object wert = null;
        if (!typ.contains("[]")) {
            if (typ.equals("String")) {
                typ = "VARCHAR(200)";
            }
            String sql = "SELECT  CAST (WERT AS " + typ + ") AS pwert FROM testparam WHERE aufgabenID = " + id + " AND testid = " + testid;

            ResultSet rs;
            int j = 0;
            try {
                rs = stmt.executeQuery(sql);
                while (j < i) {
                    rs.next();
                    j++;
                }

                wert = rs.getObject(1);

            } catch (SQLException ex) {

            }

            return wert;
        } else {
            String sql = "SELECT  WERT FROM testparam WHERE aufGID = " + id + " AND testid = " + testid;

            ResultSet rs;
            int j = 0;
            try {
                rs = stmt.executeQuery(sql);
                while (j < i) {
                    rs.next();
                    j++;
                }
                if (typ.contains("String")) {
                    wert = getStringArrayByString(rs.getString(1));
                }
                if (typ.contains("Integer")) {
                    wert = getIntegerArrayByString(rs.getString(1));
                }
                if (typ.contains("Double")) {
                    wert = getDoubleArrayByString(rs.getString(1));
                }

            } catch (SQLException ex) {

            }

        }
        return wert;
    }

    public static Object getStringArrayByString(String arr) {

        String[] results = arr.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
        return results;

    }

    public static Object getIntegerArrayByString(String arr) {

        String[] items = arr.replaceAll("\\[", "").replaceAll("\\]", "").split(",");

        Integer[] results = new Integer[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException nfe) {
            };
        }
        return results;
    }

    public static Object getDoubleArrayByString(String arr) {

        String[] items = arr.replaceAll("\\[", "").replaceAll("\\]", "").split(",");

        Double[] results = new Double[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Double.parseDouble(items[i]);
            } catch (NumberFormatException nfe) {
            };
        }
        return results;
    }

    public static ArrayList<Parameter> getAufgabenParameter(Statement stmt, int id) {
        ArrayList<Parameter> parameterListe = new ArrayList();

        String sql = "SELECT paraname, datentyp FROM methodenparameter WHERE AufgabenID = " + id + ";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String paraname = rs.getString("paraname");
                String datentyp = rs.getString("datentyp");
                Parameter parameter1 = null;

                parameter1 = new Parameter(paraname, datentyp);

                parameterListe.add(parameter1);
            }
        } catch (SQLException e) {
            System.err.println("SQL-FEHLER: " + e);

        }
//        catch (ClassNotFoundException ex) {
//            System.out.println("Class not found:  " + ex);
//        }
        return parameterListe;

    }

    public static String getMethodennamen(Statement stmt, int id) {
        String methodenNamen = "METHODE NICHT GEFUNDEN";

        String sql = "SELECT methodenname from Aufgaben where ID = " + id + ";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                methodenNamen = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL-FEHLER: " + e);
        }
        return methodenNamen;
    }

    public static String getDatentyp(Statement stmt, int id) {
        String datentyp = "DATENTYP NICHT GEFUNDEN  ";

        String sql = "SELECT datentyp from Aufgaben where ID = " + id + ";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                datentyp = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL-FEHLER: " + e);
        }
        return datentyp;
    }

    /**
     * Generiert eine HTML-Tabelle fuer die 'besten' 10 Mitspieler
     *
     * @param stmt SQL-Statement
     * @return HTML-Tabellencode
     */
    public static String getTop10(Statement stmt) {

        String top10 = "KEINE";
        String sql = "SELECT username,Rang.rangname,aktLvl "
                + "from USER,rang,UserLevel "
                + "WHERE AKTLVL = RANG.LEVEL "
                + "AND USER.ID = USERID "
                + "AND AKTLVL > 1"
                + "ORDER BY aktLvl DESC "
                + "LIMIT 10;";
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);

            top10 = SQL.DbTools.htmlTableHead("BenutzerName", "Rangname", "Level");
            top10 += SQL.DbTools.htmlRows(rs, "username", "Rang.rangname", "aktLvl");
            top10 += SQL.DbTools.htmlTableFoot();
        } catch (SQLException e) {
            System.err.println("RESULTSET FEHLER: " + e);
        }

        return top10;
    }

    /**
     * Holt sich die Profildaten<br>
     * (UserID,Benutzername,Email,Gruppe,Level,Rangname) aus der Datenbank<br>
     * Diese Daten werden in einer HTML-Tabelle ausgegeben<br>
     * Tabellen:User,Userlevel,Rang
     *
     * @param stmt SQL-Statement
     * @param id UserID
     * @return HMTL-Tabellencode
     */
    public static String myProfile(Statement stmt, int id) {
        String profile = "KEINE";
        String sql = "SELECT ID,USERNAME,EMAIL,USERGRUPPE,AKTLVL,RANGNAME "
                + "FROM USER,USERLEVEL,RANG "
                + "WHERE USERID = USER.ID "
                + "AND RANG.LEVEL = USERLEVEL.AKTLVL "
                + "AND ID = " + id;
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);

            profile = SQL.DbTools.htmlTableHead("ID", "Benutzername", "Email", "Gruppe", "Aktuelles Level", "Rangtitel");
            profile += SQL.DbTools.htmlRows(rs, "ID", "USERNAME", "EMAIL", "USERGRUPPE", "AKTLVL", "RANGNAME");
            profile += SQL.DbTools.htmlTableFoot();
        } catch (SQLException e) {
            System.err.println("RESULTSET FEHLER: " + e);
        }

        return profile;
    }

    /**
     * Gibt den Nuternamen fuer eine ID zurueck
     *
     * @param stmt SQL-Statement
     * @param id UserID
     * @return Nutzernamen
     */
    public static String myName(Statement stmt, int id) {
        String sql = "SELECT USERNAME "
                + "FROM USER "
                + "Where ID = " + id;
        ResultSet rs = null;
        String name = "";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("RESULTSET FEHLER: " + e);
        }
        try {
            rs.next();
            name = rs.getString(1);
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return name;
    }

    /**
     * Gibt die Emailadresse fuer eine ID zurueck
     *
     * @param stmt SQL-Statement
     * @param id UserID
     * @return Emailadresse
     */
    public static String myEmail(Statement stmt, int id) {
        String sql = "SELECT email "
                + "FROM USER "
                + "Where ID = " + id;
        ResultSet rs = null;
        String email = "";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("RESULTSET FEHLER: " + e);
        }
        try {
            rs.next();
            email = rs.getString(1);
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return email;
    }

    /**
     * Gibt den Hashcode des Passworts fuer eine ID zurueck
     *
     * @param stmt SQL-Statement
     * @param id UserID
     * @return Hashcode
     */
    public static int myHashpswd(Statement stmt, int id) {
        String sql = "SELECT passwort "
                + "FROM USER "
                + "Where ID = " + id;
        ResultSet rs = null;
        int pswd = 0;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("RESULTSET FEHLER: " + e);
        }
        try {
            rs.next();
            pswd = rs.getInt(1);
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return pswd;
    }

    /**
     * Generiert eine ArrayList fuer jede AufgabenID einer Kategorie<br><br>
     *
     * Beispiel:Kategorie Arrayaufgaben hat 2 Aufgaben mit Namen:<br>
     * additionsArray und subTraktionsFeld<br><br>
     *
     * Wird als Vorbereitung fuer die Methode 'aufgabelisteAusgabe'
     * verwendet<br>
     * Verknuepfung mit der 'aufgabenIDliste()'
     *
     * @param k_ID Kategorie ID
     * @param stmt SQL-Statement
     * @return HTML-Code zum anhaengen an die Aufgabenliste
     */
    public static ArrayList aufgabenliste(int k_ID, Statement stmt) {

        ArrayList aufgabenliste = new ArrayList<>();
        String sql = "SELECT methodenname from aufgaben where kategorieID = " + k_ID;
        try {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                aufgabenliste.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            System.err.println("FEHLER: " + ex);
        }
        return aufgabenliste;
    }

    /**
     * Generiert den Kopf für die dynamische Aufgabenliste
     *
     * @return Kopf der Aufgabenliste
     */
    public static String aufgabenlisteHead() {
        String head = "<div id=\"aufgabenuebersicht\" class=\"col-md-4\">\n"
                + "            <ul class=\"list-group\">\n"
                + "                <form name=\"frm2\" method=\"post\" action=\"../aufgabenVorlage.jsp\">";

        return head;
    }

    /**
     * Generiert eine ArrayList fuer jede AufgabenID einer Kategorie<br><br>
     *
     * Beispiel:Kategorie Arrayaufgaben hat 2 Aufgaben mit ID 2,4 <br>
     *
     * Wird als Vorbereitung fuer die Methode 'aufgabelisteAusgabe' verwendet
     * <br>
     * Verknuepfung mit der 'aufgabenliste()'
     *
     * @param k_ID Kategorie ID
     * @param stmt SQL-Statement
     * @return HTML-Code zum anhaengen an die Aufgabenliste
     */
    public static ArrayList aufgabenIDliste(int k_ID, Statement stmt) {

        ArrayList aufgabenIDliste = new ArrayList<>();
        String sql = "SELECT id from aufgaben where kategorieID = " + k_ID;
        try {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                aufgabenIDliste.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            System.err.println("FEHLER: " + ex);
        }
        return aufgabenIDliste;
    }

    /**
     *
     *
     * Generiert fuer jede Aufgabe einer Kategorie einen Button-input <br>
     * <pre>
     * {@code
     * <input type="button" onclick="submitForm="('A_ID')"; value = 'M_Name' />
     * }
     * </pre>
     *
     * @param aufList aufgabenliste
     * @param aufIDListe aufgabenIDliste
     * @param stmt SQL-Statement
     * @return
     */
    public static String aufgabelisteAusgabe(ArrayList aufList, ArrayList aufIDListe, Statement stmt) {

        String uebersicht = "";
        int i = 1;
        for (Object aufgabe : aufList) {
            uebersicht += "\n<input type=\"button\" class=\"btn btn-primary\" onClick=\"submitForm('" + aufIDListe.get(i - 1) + "');\" value = \"" + aufgabe + "\" />"
                    + "<br>Gegebene EXP: " + Aufgabe.getEXP(stmt, i)
                    + "<br>Author: " + Aufgabe.getAutor(stmt, i)
                    + "<br><br>";
            i++;
        }
        return uebersicht;
    }

    /**
     * Generiert das Ende der dynamischen Aufgabenliste
     *
     * @return Snippet fuer das Ende der Aufgabenliste
     */
    public static String aufgabenlisteFoot() {
        return "\n<input type=\"hidden\" id=\"btnPressed\" name=\"btnPressed\" value=\"\"/>\n"
                + "                </form>\n"
                + "\n"
                + "            </ul>\n"
                + "        </div>";
    }

    /**
     * Speichert die Javacode-Eingabe in der Datenbank<br>
     * Die SQL-Anweisung wird universell, je nachdem ob UPDATE oder INSERT
     * gebraucht wird, generiert<br>
     *
     * @param eingabe eingegebener Javacode
     * @param u_ID UserID
     * @param a_ID AufgabenID
     * @param stmt SQL-Statement
     * @param testSucess alle Tests erfolgreich?
     * @param update Ist eine Eingabe schon vorhanden in der Datenbank?
     */
    public static void saveCode(String eingabe, int u_ID, int a_ID, Statement stmt, boolean testSucess, boolean update) {
        //nop
        System.out.println("nop");

        String sql, sql2;
        sql2 = "";

        if (!update) {
            sql = "INSERT INTO EINGABEN "
                    + "VALUES (" + a_ID + "," + u_ID + ",'" + eingabe + "',";
            if (testSucess) {
                sql += "true);";
            } else {
                sql += "false);";
            }
        } else {
            sql = "update EINGABEN "
                    + "SET TEXT = '" + eingabe + "'"
                    + " WHERE USERID = " + u_ID
                    + " AND AUFGABENID = " + a_ID + ";";
            if (testSucess) {
                sql2 = "update EINGABEN SET "
                        + "Geloest = true "
                        + "WHERE USERID = " + u_ID
                        + " AND AUFGABENID = " + a_ID + ";";
            } else {
                sql2 = "update EINGABEN SET "
                        + "Geloest = false "
                        + "WHERE USERID = " + u_ID
                        + " AND AUFGABENID = " + a_ID + ";";
            }
        }

        try {
            stmt.execute(sql);
            if (!sql2.isEmpty()) {
                stmt.executeQuery(sql2);
            }
        } catch (SQLException ex) {
            System.err.println("FEHLER: " + ex);
        }
    }

    /**
     * Prueft ob eine Eingabe fuer die Aufgabe gemacht wurde<br>
     * Wird beispielsweise verwendet um statt einem INSERT, ein UPDATE
     * auszufuehren
     *
     * @param stmt SQL-Statement
     * @param u_ID User-ID
     * @param a_ID AufgabenID
     * @return Eingabe vorhanden = true
     */
    public static boolean isCodeUpdate(Statement stmt, int u_ID, int a_ID) {
        boolean isupdate = false;
        String sql = "SELECT text from eingaben where userID = " + u_ID + " AND aufgabenID = " + a_ID + ";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                isupdate = true;
            }
        } catch (SQLException ex) {
            System.err.println("FEHLER: " + ex);
        }

        return isupdate;

    }

    /**
     * Falls eine Eingabe gemacht wurde, wird diese zurueck gegeben<br>
     * Sonst bleibts leer.
     *
     * @param stmt SQL-Statement
     * @param u_ID UserID
     * @param a_ID AufgabenID
     * @return Eingabe aus der Datenbank oder leer
     */
    public static String getEingabe(Statement stmt, int u_ID, int a_ID) {
        String eingabe = "";
        String sql = "SELECT text from eingaben where userID = " + u_ID + " AND aufgabenID = " + a_ID + ";";

        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                eingabe = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.err.println("FEHLER: " + ex);
        }

        return eingabe;
    }

    public static int getAufgabenId(Statement stmt, String methodenname) {
        String sql = "SELECT id FROM aufgaben WHERE methodenname = '" + methodenname + "'";
        int aufgabenId = 0;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                aufgabenId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("SQL-FEHLER: " + e);
        }
        return aufgabenId;
    }
    //neu

    public static boolean InsertAufgabe(Statement stmt, String beschreibung, String kategorieId, String autor, String methodennamen, String datentyp, int lv, int exp) {
        boolean erfolg = false;
        try {
            String sqlAufgabe = "INSERT INTO Aufgaben VALUES "
                    + "(DEFAULT, '" + beschreibung + "', '" + kategorieId + "', '" + autor + "', '" + methodennamen + "',"
                    + "'" + datentyp + "', '" + lv + "', '" + exp + "')";

            stmt.executeQuery(sqlAufgabe);

            erfolg = true;
        } catch (SQLException e) {
            System.err.println("FEHLER: " + e);
        }
        return erfolg;
    }
    //neu

    public static boolean InsertMethodenParameter(Statement stmt, int id, String parametername, String datentyp) {
        boolean erfolg = false;
        try {
            String sqlAufgabe = "INSERT INTO MethodenParameter VALUES ('" + id + "', '" + parametername + "', '" + datentyp + "');";

            stmt.executeQuery(sqlAufgabe);

            erfolg = true;
        } catch (SQLException e) {
            System.err.println("FEHLER: " + e);
        }
        return erfolg;
    }

    public static String testParameterListe(ArrayList aufList) {

        String uebersicht = "";
        int i = 1;
        for (Object aufgabe : aufList) {
            uebersicht += "<div class=\"input-group\">\n"
                    + "                            <label class=\"input-group-addon\">" + aufgabe + " </label>\n"
                    + "                            <input required type=\"text\" class=\"form-control\" id=\"appendedInput\" name=\"parameter\">\n"
                    + "                            <span class=\"input-group-btn btn-group\"> </span>\n"
                    + "                        </div>";
            i++;
        }
        return uebersicht;
    }
}
