/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

/**
 * Eine Hilfsklasse in der Parameter inklusive ihrer Klasse abgespeichert werden.
 * @author kiedrowski
 */
public class Parameter {

    private String parameter;
    private String datentyp;
    private Class klasse;
    /**
     * Erzeugt ein Parameter Object (Konstruktor) 
     * @param parameter Der Parameter der abgespeichert wird
     * 
     */
    public Parameter(String parameter) {
        this.parameter = parameter;
    }
    /**
     * Erzeugt ein Parameter Object (Konstruktor) 
     * @param parameter Der Parameter der abgespeichert wird
     * @param datentyp Der Datentyp des Parameters
     */
    public Parameter(String parameter, String datentyp) {
        this.parameter = parameter;
        this.datentyp = datentyp;
        try {

            this.klasse = Class.forName("java.lang." + datentyp);
        } catch (ClassNotFoundException ex) {
            try {

                this.klasse = Class.forName("[Ljava.lang." + datentyp.substring(0, datentyp.length() - 2) + ";");
            } catch (ClassNotFoundException ex1) {
                try {

                    this.klasse = Class.forName("[[Ljava.lang." + datentyp + ";");
                } catch (ClassNotFoundException ex2) {
                    System.err.println(ex2);
                }
            }
        }

    }
 /**
     * Gibt den Parameter Wert aus
     * @return gibt den Parameter Wert aus
     */
    public String getParameter() {
        return parameter;
    }
 /**
     * Setzt den Parameter Wert
     * @param parameter der Wert des Paramters
     */
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
 /**
     * Gibt den Datentyp String aus
     * @return Datentyp
     */
    public String getDatentyp() {
        return datentyp;
    }
 /**
     * Setzt den Datentyp String
     * @param datentyp der Datentyp der gesetzt wird.
     */
    public void setDatentyp(String datentyp) {
        this.datentyp = datentyp;
    }
/**
     * Gibt das die Klasse als Class Object zurück
     * @return ein Class Object
     */
    public Class getKlasse() {
        return klasse;
    }
/**
     * Setzt die Klasse des Parameters
     * @param klasse Die Klasse die gesetzt wird
     */

    public void setKlasse(Class klasse) {
        this.klasse = klasse;
    }
/**
     * Gibt das Parameter Object als String aus 
     * @return gibt den gesamten Parameter als String aus
     */
    @Override
    public String toString() {
        return datentyp + " " + parameter;

    }
}
