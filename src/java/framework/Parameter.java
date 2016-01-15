/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

/**
 *
 * @author kiedrowski
 */
public class Parameter {

    private String parameter;
    private String datentyp;
    private Class klasse;

    public Parameter(String parameter) {
        this.parameter = parameter;
    }

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

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getDatentyp() {
        return datentyp;
    }

    public void setDatentyp(String datentyp) {
        this.datentyp = datentyp;
    }

    public Class getKlasse() {
        return klasse;
    }

    public void setKlasse(Class klasse) {
        this.klasse = klasse;
    }

    @Override
    public String toString() {
        return datentyp + " " + parameter;

    }
}
