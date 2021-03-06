package SQL;

import framework.Aufgabe;
import framework.Benutzer;
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
 *
 * @author JavAcademy
 */
public class InBetween {

    /**
     * Gibt die Aufgabenbeschreibung aus der Datenbank zurueck<br>
     * Tabelle: Aufgaben
     *
     * @param stmt SQL-Statement
     * @param id AufgabenID
     * @return String mit der Aufgabenstellung
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

    /**
     * Liest alle Tests zur Aufgabe mit der gegeben ID aus und gibt sie in einer
     * ArrayList weiter <br>
     * Tabelle: Tests
     *
     * @see framework.Test
     * @param stmt SQL-Statement
     * @param id AufgabenID
     * @return ArrayListe mit Tests
     */
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

    /**
     * Liest alle Testwerte zu einem Test einer Aufgabe mit der gegeben ID aus
     * <br> und gibt sie in einer Object ArrayList weiter <br>
     * Tabelle: Tests
     *
     * @see framework.Test
     * @param stmt SQL-Statement
     * @param id AufgabenID
     * @param testid ID des Tests
     * @return ArrayListe mit Testwerten
     */
    public static ArrayList<Object> getTestWerte(Statement stmt, int id, int testid) {
        ArrayList<Object> werteListe = new ArrayList();

        String sql = "SELECT typ, paraname "
                + "FROM Testparam where testID= " + testid + " AND aufgabenid = " + id + ";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            int i = 1;
            while (rs.next()) {
                //  Object wert = rs.getString("Wert");
                Object wert = null;
                String typ = rs.getString("Typ");
                String name = rs.getString("paraname");
//               int test;
//                test = Integer.parseInt(wert);
                wert = getParsedObject(stmt, typ, name, id, testid, i);
                werteListe.add(wert);
                i++;
            }
        } catch (SQLException e) {
            System.err.println("SQL-FEHLER: " + e);

        }

        return werteListe;
    }

    /**
     * Parsed ein Object aus der Datenbank zum richtigen Datentyp
     *
     *
     *
     * @param stmt SQL-Statement
     * @param id AufgabenID
     * @param typ Datentyp
     * @param testid ID des Tests
     * @param paraname Name des Parameters
     * @param i Index im Testwerte Array
     * @return Object mit dem korrekten Datentyp
     */
    public static Object getParsedObject(Statement stmt, String typ, String paraname, int id, int testid, int i) {
        Object wert = null;
        if (!typ.contains("[]")) {
            if (typ.equals("String")) {
                typ = "VARCHAR(200)";
            }
            String sql = "SELECT  CAST (WERT AS " + typ + ") AS pwert,ROW_NUMBER() OVER() rn  FROM testparam WHERE aufgabenID = " + id + " AND testid = " + testid + " AND paraname =  '" + paraname + "'";

            ResultSet rs;
            int j = 0;
            try {
                rs = stmt.executeQuery(sql);
                rs.next();
                j++;

                wert = rs.getObject(1);

            } catch (SQLException ex) {

            }

            return wert;
        } else {
            String sql = "SELECT  WERT FROM testparam WHERE aufgabenID = " + id + " AND testid = " + testid;

            ResultSet rs;
            int j = 0;
            try {
                rs = stmt.executeQuery(sql);
                while (j < i) {
                    rs.next();
                    j++;
                }
                if (typ.contains("String")) {
                    wert = getStringArrayByString(rs.getString(1).replaceAll("\\s+", ""));
                }
                if (typ.contains("Integer")) {
                    wert = getIntegerArrayByString(rs.getString(1).replaceAll("\\s+", ""));
                }
                if (typ.contains("Double")) {
                    wert = getDoubleArrayByString(rs.getString(1).replaceAll("\\s+", ""));
                }
                if (typ.contains("Boolean")) {
                    wert = getBooleanArrayByString(rs.getString(1).replaceAll("\\s+", ""));
                }

            } catch (SQLException ex) {

            }

        }
        return wert;
    }

    /**
     * Wandelt einen String der Form ["Te", "St"] in einen String Array
     *
     * @param arr der umzuwandelnde String
     * @return String[] Object
     */
    public static Object getStringArrayByString(String arr) {

        String[] results = arr.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
        return results;

    }

    /**
     * Wandelt einen String der Form ["Te", "St"] in einen String Array
     *
     * @param arr der umzuwandelnde String
     * @return String[] Object
     */
    public static Object getBooleanArrayByString(String arr) {

        String[] items = arr.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
        Boolean[] results = new Boolean[items.length];
        for (int i = 0; i < items.length; i++) {

            results[i] = Boolean.parseBoolean(items[i]);
        }
        return results;
    }

    /**
     * Wandelt einen String der Form [1, 2] in einen Integer Array
     *
     * @param arr der umzuwandelnde String
     * @return Integer[] Object
     */
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

    /**
     * Wandelt einen String der Form [1.2, 3.4] in einen Double Array
     *
     * @param arr der umzuwandelnde String
     * @return Double[] Object
     */
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

    /**
     * Liest alle Parameter zu einer Aufgabe aus und gibt sie in einer ArrayList
     * aus
     *
     * @see framework.Parameter
     * @param stmt SQL-Statement
     * @param id AufgabenID
     * @return ArrayList mit Parametern
     */
    public static ArrayList<Parameter> getAufgabenParameter(Statement stmt, int id) {
        ArrayList<Parameter> parameterListe = new ArrayList();

        String sql = "SELECT paraname, datentyp FROM methodenparameter WHERE AufgabenID = " + id + ";";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String paraname = rs.getString("paraname");
                String datentyp = rs.getString("datentyp");

                Parameter parameter1 = new Parameter(paraname, datentyp);

                parameterListe.add(parameter1);
            }
        } catch (SQLException e) {
            System.err.println("SQL-FEHLER: " + e);

        }
        return parameterListe;
    }

    /**
     * Liest den Namen der Methode der gegebenen Aufgabe aus
     *
     *
     * @param stmt SQL-Statement
     * @param id AufgabenID
     * @return String mit dem Namen
     */
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

    /**
     * Liest den Datentyp der Methode der gegebenen Aufgabe als String aus
     *
     *
     * @param stmt SQL-Statement
     * @param id AufgabenID
     * @return String mit dem Datentyp
     */
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
        String sql = "SELECT ID,USERNAME,EMAIL,USERGRUPPE,AktExp,AKTLVL,RANGNAME "
                + "FROM USER,USERLEVEL,RANG "
                + "WHERE USERID = USER.ID "
                + "AND RANG.LEVEL = USERLEVEL.AKTLVL "
                + "AND ID = " + id;
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);

            profile = SQL.DbTools.htmlTableHead("ID", "Benutzername", "Email", "Gruppe", "Level", "Erfahrungspunkte", "Rangtitel");
            profile += SQL.DbTools.htmlRows(rs, "ID", "USERNAME", "EMAIL", "USERGRUPPE", "AKTLVL", "AKTEXP", "RANGNAME");
            profile += SQL.DbTools.htmlTableFoot();
        } catch (SQLException e) {
            System.err.println("RESULTSET FEHLER: " + e);
        }

        return profile;
    }

    /**
     * Gibt den Nutzernamen fuer eine ID zurueck
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
     * @return HTML code button
     */
    public static String aufgabelisteAusgabe(ArrayList aufList, ArrayList aufIDListe, Statement stmt) {

        String uebersicht = "";
        int i = 1;
        for (Object aufgabe : aufList) {
            String a_ID = (String) aufIDListe.get(i - 1);
            int intA_ID = Integer.parseInt(a_ID);
            uebersicht += "\n<input type=\"button\" class=\"btn btn-primary\" onClick=\"submitForm('" + aufIDListe.get(i - 1) + "');\" value = \"" + getMethodennamen(stmt, intA_ID) + "\" />"
                    + "<br>Gegebene EXP: " + Aufgabe.getEXP(stmt, intA_ID)
                    + "<br>Autor: " + getAutor(stmt, intA_ID)
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
     */
    public static void saveCodeDefault(String eingabe, int u_ID, int a_ID, Statement stmt) {
        //nop
        System.out.println("nop");

        String sql = null;

        sql = "INSERT INTO EINGABEN "
                + "VALUES (" + a_ID + "," + u_ID + ",'" + eingabe + "',false);";

        try {
            stmt.execute(sql);
        } catch (SQLException ex) {
            System.err.println("FEHLER: " + ex);
        }
    }

    /**
     * Aktualisiert in der Datenbank die Eingabe für die Aufgabe<br>
     * Je nachdem ob alles Erfolgreich ist wird GELOEST auf true/false gesetzt
     * und Erfahrungspunkte vergeben
     *
     * @param eingabe Javacode vom Benutzer
     * @param u_ID BenutzerID
     * @param a_ID AufgabenID
     * @param stmt SQl-Statement
     * @param testSucess true falls der test erfolgreich war
     */
    public static void saveCodeUpdate(String eingabe, int u_ID, int a_ID, Statement stmt, boolean testSucess) {
        String sql;
        String sql2;

        sql = "update EINGABEN "
                + "SET TEXT = '" + eingabe + "'"
                + " WHERE USERID = " + u_ID
                + " AND AUFGABENID = " + a_ID + ";";
        if (testSucess) {
            sql2 = "update EINGABEN SET "
                    + "Geloest = true "
                    + "WHERE USERID = " + u_ID
                    + " AND AUFGABENID = " + a_ID + ";";
            Benutzer.addExp(stmt, u_ID, a_ID);
        } else {
            sql2 = "update EINGABEN SET "
                    + "Geloest = false "
                    + "WHERE USERID = " + u_ID
                    + " AND AUFGABENID = " + a_ID + ";";
        }

        try {
            stmt.execute(sql);
            stmt.executeQuery(sql2);
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

    /**
     * Gibt die AufgabenID einer Methode mit dem gegebenen Namen zurueck
     *
     * @param stmt SQL-Statement
     * @param methodenname Name der Methode
     * @return Aufgaben ID
     */
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

    /**
     * Fuegt die Aufgabe der Datenbank hinzu
     *
     * @param stmt SQL-Statement
     * @param beschreibung Beschreibung der Aufgabe
     * @param kategorieId ID der zugehörigen Kategorie
     * @param autor Autor der Aufgabe
     * @param methodennamen Name der Methode
     * @param datentyp return Datentyp
     * @param lv Empfohlenes Level
     * @param exp Erfahrung die als Belohnung ausgezahlt wird
     * @return true, wenn die Aufgabe hinzugefuegt werden konnte
     */
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

    /**
     * Fuegt die Methodenparameter der Aufgabe der Datenbank hinzu
     *
     * @param stmt SQL-Statement
     * @param id Aufgaben ID
     * @param parametername Name des Parameters
     * @param datentyp return Datentyp
     * @return true, wenn der Parameter hinzugefuegt werden konnte
     */
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

    /**
     * Fügt Testparameter , mit dummy Werten hinzu <br>
     *
     * @param stmt SQL-Statement
     * @param id AufgabenID
     * @param testId TestID
     * @param parametername TestID
     * @param datentyp TestID
     * @return true wenn es nicht Fehlgeschlagen ist
     */
    public static boolean InsertTestParameter(Statement stmt, int id, int testId, String parametername, String datentyp) {
        boolean result = false;
        try {
            String sqlTest = "INSERT INTO TestParam VALUES ('" + id + "', '" + testId + "', '" + parametername + "', '" + datentyp + "', 'FRESHDUMMYDORE');";

            stmt.executeQuery(sqlTest);

            result = true;
        } catch (SQLException e) {
            System.err.println("FEHLER: " + e);
        }
        return result;
    }

    /**
     * Fügt einen Test, mit dummy Werten hinzu <br>
     *
     * @param stmt SQL-Statement
     * @param id AufgabenID
     * @param testId TestID
     * @return true wenn nicht Fehlgeschlagen ist
     */
    public static boolean InsertTests(Statement stmt, int id, int testId) {
        boolean result = false;
        try {
            String sqlTest = "INSERT INTO Tests VALUES ('" + id + "', '" + testId + "', 'FRESHDUMMYDORE', '0');";

            stmt.executeQuery(sqlTest);

            result = true;
        } catch (SQLException e) {
            System.err.println("FEHLER: " + e);
        }
        return result;
    }

    /**
     * Generiert eine html Struktur zum anzeigen aller Testparameter <br>
     *
     * @param aufList Alle Test parameter
     * @return eine Html Struktur zum Anzeigen der Parameter
     */
    public static String testParameterListe(ArrayList aufList) {

        String uebersicht = "";
        int i = 1;
        for (Object parametertyp : aufList) {
            uebersicht += "<div class=\"input-group\">\n"
                    + "                            <label class=\"input-group-addon\">" + parametertyp + " </label>\n"
                    + "                            <input required type=\"text\" class=\"form-control\" id=\"appendedInput\" name=\"parameter" + i + "\">\n"
                    + "                            <span class=\"input-group-btn btn-group\"> </span>\n"
                    + "                        </div> <br>";
            i++;
        }
        return uebersicht;
    }

    /**
     * Gibt die anzahl der Parameter an <br>
     * Tabelle: methodenparameter
     *
     * @param stmt SQL-Statement
     * @param id AufgabenID
     * @return Anzahl der Parameter
     */
    public static int getTestParameterAnzahl(Statement stmt, int id) {
        String sql = "SELECT count(aufgabenid) \n"
                + "FROM methodenparameter WHERE Aufgabenid = " + id + "";

        int aufgabenId = 0;

        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                aufgabenId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL-FEHLER: " + e);
        }
        return aufgabenId;
    }

    /**
     * Überschreibt das Erwartene Ergebnis in der Datenbank <br>
     * Tabelle: tests
     *
     * @param stmt SQL-Statement
     * @param aufgabenid AufgabenID
     * @param testid TestID
     * @param ergebnis neuer Wert zum überschreiben
     * @return true wenn es nicht Fehlgeschlagen ist
     */
    public static boolean updateTests(Statement stmt, int aufgabenid, int testid, String ergebnis) {
        boolean result = false;
        int hash = ergebnis.hashCode();
        String sql = "";
        if (testid == 1) {

            sql = "UPDATE tests SET erwterg = '" + ergebnis + "' ,  erghash = " + hash + " WHERE aufgabenid = " + aufgabenid + " AND testId = " + testid + ";";
        } else {
            sql = "INSERT INTO tests VALUES ('" + aufgabenid + "','" + testid + "','" + ergebnis + "','" + hash + "');";
        }

        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException ex) {
            System.err.println("FEHLER: " + ex);
        }
        return result;
    }

    /**
     * Überschreibt die Test parameter in der Datenbank <br>
     * Tabelle: testparam
     *
     * @param stmt SQL-Statement
     * @param aufgabenId AufgabenID
     * @param newWert neuer Wert zum überschreiben
     * @param parameter gibt an welcher Wert neu gesetzt werden soll
     * @param typ gibt den Datentyp an
     * @param testId gibt die Test ID an
     * @return true wenn es nicht Fehlgeschlagen ist
     */
    public static boolean updateTestparameter(Statement stmt, int aufgabenId, int testId, String newWert, String parameter, String typ) {
        boolean result = false;
        String sql = "";
        int newTestid = testId;
        if (testId == 1) {

            sql = "UPDATE testparam SET wert = '" + newWert + "' WHERE  paraname = '" + parameter + "' AND aufgabenid = " + aufgabenId + " AND testid = " + testId + ";";
        } else {
            sql = "INSERT INTO TestParam VALUES ('" + aufgabenId + "','" + newTestid + "','" + parameter + "','" + typ + "','" + newWert + "');";
        }
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException ex) {
            System.err.println("FEHLER: " + ex);
        }
        return result;
    }

    /**
     * Gibt den Autoren aus der Datenbank zurueck<br>
     * Tabelle: aufgaben
     *
     * @param stmt SQL-Statement
     * @param id AufgabenID
     * @return Autor
     */
    public static String getAutor(Statement stmt, int id) {
        String sql = "  SELECT autor FROM aufgaben WHERE id = " + id + ";";
        String autor = "";

        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                autor = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL-FEHLER: " + e);
        }
        return autor;
    }

    /**
     * Gibt die anzahl der Tests aus der Datenbank zurueck<br>
     * Tabelle: Tests
     *
     * @param stmt SQL-Statement
     * @param id AufgabenID
     * @return Anzahl vorhandener Tests
     */
    public static int getAnzahlTests(Statement stmt, int id) {
        String sql = "SELECT count(aufgabenid) FROM tests WHERE Aufgabenid = " + id + ";";
        int anzahlTests = 0;

        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                anzahlTests = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL-FEHLER: " + e);
        }
        return anzahlTests;
    }

    /**
     * Gibt die Id eines Tests aus der Datenbank zurueck<br>
     * Tabelle: Tests
     *
     * @param stmt SQL-Statement
     * @param id AufgabenID
     * @return TestId von bestimmten Tests
     */
    public static int getTestId(Statement stmt, int id) {
        String sql = "SELECT max(testid) FROM tests WHERE aufgabenid = " + id + ";";
        int testid = 0;

        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                testid = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL-FEHLER: " + e);
        }
        return testid;
    }

    /**
     * Überprüft ob ein Test einen Dummywert enthält <br>
     * Tabelle: Tests
     *
     * @param stmt SQL-Statement
     * @param id AufgabenID
     * @return false wenn kein Dummywert enthalten ist
     */
    public static boolean getTestDummy(Statement stmt, int id) {
        String sql = "SELECT wert FROM testparam WHERE Aufgabenid = " + id + ";";
        String testDummy = "";
        boolean result = false;

        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                testDummy = rs.getString(1);
                if (testDummy.equals("FRESHDUMMYDORE")) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL-FEHLER: " + e);
        }
        return result;
    }
}
