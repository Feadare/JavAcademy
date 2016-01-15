/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

/**
 * Hilfsklasse in der das Ergebnis zwischengespeichert wird
 * @author feadare
 */
public class ResultObj {
    Object resultObj;
/**
 * Gibt das Ergebnis zurück
 * @return Das Ergebnis
 */
    public Object getResultObj() {
        return resultObj;
    }
/**
 * Setzt das Ergebnis
 * @param resultObj Wert auf den das Ergebnis gesetzt werden soll
 */
    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }
    
    
}
