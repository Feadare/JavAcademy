/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.util.List;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * Eine Hilfsklasse in der abgeschlossene Tests abgespeichert werden
 * <br> Hier können auch Diagnostics abgespeichert werden.
 *
 * @author kiedrowski
 */
public class AbgeschlossenerTest {

    Object expResult;
    Object result;
    boolean success;
    List<Diagnostic<? extends JavaFileObject>> diagnostics;
    boolean notcompiled;

    /**
     * Erzeugt ein AbgeschlossenerTest Object (Konstruktor) 
     * @param expResult das erwartete Ergebnis
     * @param result das erhaltene Ergebnis
     * @param success true, falls results uebereinstimmen
     * 
     */
    public AbgeschlossenerTest(Object expResult, Object result, boolean success) {
        this.expResult = expResult;
        this.result = result;
        this.success = success;
    }
   /**
     * Gibt das erwartete Ergebnis zurück
     * @return das erwartete Ergebnis
     */
    public Object getExpResult() {
        return expResult;
    }
  /**
     * Gibt das User Ergebnis zurück
     * @return das User Ergebnis
     */
    public Object getResult() {
        return result;
    }
 /**
     * Gibt den Erfolg des Tests zurück
     * @return true, gibt an ob die Tests übereinstimmen
     */
    public boolean isSuccess() {
        return success;
    }
 /**
     * Gibt an ob der Testfall kompiliert werden konnte.
     * @return true, falls die Datei nicht kompiliert werden konnte
     */
    public boolean isNotcompiled() {
        return notcompiled;
    }
 /**
     * Legt die Variable fest die angibt ob der Testfall kompiliert werden konnte.
     * @param notcompiled true wenn es nicht kompiliert wurde
     */
    public void setNotcompiled(boolean notcompiled) {
        this.notcompiled = notcompiled;
    }
 /**
     * Gibt die Diagnostics im Falle einer nicht kompilierten Methode zurück 
     * @return Die Diagnostics im Falle einer nicht kompilierten Methode
     */
    public List<Diagnostic<? extends JavaFileObject>> getDiagnostics() {
        return diagnostics;
    }
 /**
     * Setzt die Diagnostics 
     * @param diagnostics Die Diagnostics im Falle einer nicht kompilierten Methode
     */
    public void setDiagnostics(List<Diagnostic<? extends JavaFileObject>> diagnostics) {
        this.diagnostics = diagnostics;
    }
 /**
     * Wandelt den Abgeschlossenen Test in eine sinnvolle String Form
     * @return Abschluss des Tests als String Form
     */
    @Override
    public String toString() {
        return result + " " + success;
    }

}
