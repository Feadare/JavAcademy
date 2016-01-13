/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 *
 * @author wilmanm
 */
public class Hilfsmethoden {

    public static String getTestErgebnisTabelle(ArrayList<AbgeschlossenerTest> abTest) {
        String tabelle = "<table class=\"table\" border='1' ";
        tabelle += "<tr><th>Erw. Ergebnis</th>"
                + " <th>Dein Ergebnis</th>"
                + " <th>Richtig</th></tr>";
        for (AbgeschlossenerTest abTest1 : abTest) {
            String expResult = (String) abTest1.getExpResult();
            if (expResult.length() > 16) {
                expResult = expResult.substring(0, 16) + "[...]";
            }
            String result = (String) abTest1.getResult();
            if (result.length() > 16) {
                result = result.substring(0, 16) + "[...]";
            }
            tabelle += "<tr> <td>" + expResult + "</td>"
                    + "<td>" + result + "</td>";
            if (abTest1.isSuccess()) {
                tabelle += "<td bgcolor=\"#66ff66\">Ja!</td>";
            } else {
                tabelle += "<td bgcolor=\"#ff0000\">Nein!</td>";
            }
            tabelle += "</tr>";
        }
        tabelle += "</table>";
        return tabelle;
    }

    public static String getDiagnosticsTabelle(List<Diagnostic<? extends JavaFileObject>> diagnostics) {
        String tabelle = "<table class=\"table\" border='1' ";
        tabelle += "<tr><th>Fehler beim Compilieren: </th></tr>";
        for (Diagnostic diagnostic : diagnostics) {
            tabelle += "<tr> <td> Zeile: " + (diagnostic.getLineNumber() - 2) + "<br> Fehler: " + diagnostic.getMessage(Locale.GERMAN) + "</td></tr>";
        }
        tabelle += "</table>";
        tabelle += "<table class=\"table\" border='1'>";
        tabelle += "<tr><th>Häufige Fehler</th><th>Mögliche Lösung</th></tr> ";
        tabelle += "<tr><td> ';' expected </td><td>An einer Stelle wurde ein ; vergessen.<br> "
                + "nach jeder Aktion(Variable verändern, Variable initialisiert, etc.)<br>"
                + "muss ein ; gesetzt werden. "
                + "</th></tr>";
        tabelle += "<tr><td> cannot find symbol symbol: Variablenname</td>"
                + "<td> Der Compiler konnte die Variable mit dem angegegebenen Namen nicht finden. <br>"
                + "Vielleicht wurde die Variable nicht initialisiert oder falsch benannt"
                + "</td></tr>";
        tabelle += "<tr><td> illegal start of type</td>"
                + "<td> Die Methode wurde zu früh geschlossen. Vielleicht wurde eine } zuviel gesetzt."
                + "</td></tr>";
        tabelle += "<tr><td> missing return statement</td>"
                + "<td> Das Ergebnis wurde nicht per return zurückgegeben."
                + "</td></tr>";
        tabelle += "<tr><td> incompatible types</td>"
                + "<td> Beim Variablen setzen wurden Datentyp vermischt <br> "
                + "oder falscher Datentyp wurde zurückgegeben."
                + "</td></tr>";
        tabelle += "<tr><td> unreachable statement</td>"
                + "<td> return ist nur in einem if/else oder einer Schleife <br> "
                + " oder es gibt eine Endlosschleife. "
                + "</td></tr>";
        tabelle += "</table>";

        return tabelle;
    }

    /**
     * Hilfetext zu haeufigen Compilerfehlermeldungen und Bedeutungen
     *
     * @return
     */
    public static String helpCompiler() {
        String tabelle = "<table class=\"table\" border='1'>";
        tabelle += "<tr><th>Häufige Fehler</th><th>Mögliche Lösung</th></tr> ";
        tabelle += "<tr><td> ';' expected </td><td>An einer Stelle wurde ein ; vergessen.<br> "
                + "nach jeder Aktion(Variable verändern, Variable initialisiert, etc.)<br>"
                + "muss ein ; gesetzt werden. "
                + "</th></tr>";
        tabelle += "<tr><td> cannot find symbol symbol: Variablenname</td>"
                + "<td> Der Compiler konnte die Variable mit dem angegegebenen Namen nicht finden. <br>"
                + "Vielleicht wurde die Variable nicht initialisiert oder falsch benannt"
                + "</td></tr>";
        tabelle += "<tr><td> illegal start of type</td>"
                + "<td> Die Methode wurde zu früh geschlossen. Vielleicht wurde eine } zuviel gesetzt."
                + "</td></tr>";
        tabelle += "<tr><td> missing return statement</td>"
                + "<td> Das Ergebnis wurde nicht per return zurückgegeben."
                + "</td></tr>";
        tabelle += "<tr><td> incompatible types</td>"
                + "<td> Beim Variablen setzen wurden Datentyp vermischt <br> "
                + "oder falscher Datentyp wurde zurückgegeben."
                + "</td></tr>";
        tabelle += "<tr><td> unreachable statement</td>"
                + "<td> return ist nur in einem if/else oder einer Schleife <br> "
                + " oder es gibt eine Endlosschleife. "
                + "</td></tr>";
        tabelle += "</table>";

        return tabelle;
    }

    public static String helpDatentypen() {
        return "<table class=\"table\" border='1'>\n"
                + "  <tr>\n"
                + "    <th>String</th>\n"
                + "    <th>int<br></th>\n"
                + "    <th>double</th>\n"
                + "    <th>boolean</th>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td>Zeichenkette (\"Hallo!\")<br></td>\n"
                + "    <td>ganze Zahl (5)<br></td>\n"
                + "    <td>Kommazahl (3,1415)<br></td>\n"
                + "    <td>WAHR/FALSCH (true/false)<br></td>\n"
                + "  </tr>\n"
                + "</table>";
    }

    public static String helpSchleifen() {
        return "<table class=\"table\" border='1'>\n"
                + "  <tr>\n"
                + "    <th>for-Schleife</th>\n"
                + "    <th>while-schleife<br></th>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td>meistens Zählscheifen</td>\n"
                + "    <td>Während ein bestimmter Zustand ist</td>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td>for(int i = 0; i&lt;=10;i++){..programmcode...}</td>\n"
                + "    <td>while(code.nextLine()){..System.out.print...}</td>\n"
                + "  </tr>"
                + "</table>";
    }

    public static String helpObjektePerson() {
        return "<table class=\"table\" border='1'>\n"
                + "  <tr>\n"
                + "    <th>String</th>\n"
                + "    <th>int</th>\n"
                + "    <th>String<br></th>\n"
                + "    <th>int</th>\n"
                + "    <th>String</th>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td>Name</td>\n"
                + "    <td>Alter</td>\n"
                + "    <td>Strasse</td>\n"
                + "    <td>Hausnummer</td>\n"
                + "    <td>Ort</td>\n"
                + "  </tr>\n"
                + "</table>";
    }
}
