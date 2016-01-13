/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kiedrowski
 */
public class ParameterWert {

    private String parameter;
    private String datentyp;
    private Class klasse;
    private Object wert;

    public ParameterWert(String parameter, String datentyp, Class klasse, Object wert) {
        this.parameter = parameter;
        this.datentyp = datentyp;
        this.klasse = Integer.class;
    
      
    }

    public ParameterWert(String parameter, String datentyp) {
        this.parameter = parameter;
        this.datentyp = datentyp;
        try {
            
            this.klasse = Class.forName("[[Ljava.lang.Integer;");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Parameter.class.getName()).log(Level.SEVERE, null, ex);
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
