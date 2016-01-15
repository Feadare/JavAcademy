package compiler;

import framework.AbgeschlossenerTest;
import SQL.DbTools;
import SQL.InBetween;
import framework.Parameter;
import framework.Test;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

/**
 * Kompiliert eine Benutzer Eingabe und gleicht diese mit einem 
 * <br> Datenbanken Ergebnis ab 
 * <br> Erstellt eine Ergebnistabelle. <br>
 * @author kiedrowski
 */
public class EingabenCompiler {

    /**
     * Gibt einen JavaCompiler zurück<br>
     *
     * @return compiler 
     */


    public static JavaCompiler getCompiler() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        return compiler;
    }

    /**
     * Erstellt Writer welche die Methode generieren<br>
     *
     * @param eingabe Benutzer Eingabe
     * @param datentyp Datentyp der Methode
     * @param methodenname Namen der Methode
     * @param parameter Parameter der Mehtode
     *
     * @return gibt die Mehtode als String zurück
     */

    public static String methodenAufbau(String eingabe, String datentyp, String methodenname, String parameter) {

        StringWriter writer = new StringWriter();
        PrintWriter out = new PrintWriter(writer);
        out.println("public class CompilerKlasse {");
        out.println("public static " + datentyp + " " + methodenname + "(" + parameter + "){");
        out.println(eingabe);
        out.println("}");
        out.println("}");
        out.close();
        eingabe = writer.toString();
        return eingabe;
    }

     /**
     * Ruft die Methode methodenAufbau auf und erstellt eine JavaFileObjekt aus dieser<br>
     *
     * @param eingabe Benutzer Eingabe
     * @param datentyp Datentyp der Methode
     * @param methodenname Namen der Methode
     * @param parameter Parameter der Mehtode
     *
     * @return file 
     */

    public static JavaFileObject getJavaFileObject(String eingabe, String datentyp, String methodenname, String parameter) {

        String methodenEingabe = methodenAufbau(eingabe, datentyp, methodenname, parameter);
        JavaFileObject file = new JavaSourceFromString("CompilerKlasse", methodenEingabe);
        return file;
    }

   /**
     * Erstellt eine Diagnostics aus einem JavaFileObject<br>
     *
     * @return diagnostics
     */

    public static DiagnosticCollector<JavaFileObject> getDiagnosticsWert() {
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        return diagnostics;
    }
   /**
     * Gibt eine compilationUnits zurück <br>
     *
     * @param file	JavaFileObject 
     *
     * @return gibt die Mehtode als String zurück
     */

    public static Iterable<? extends JavaFileObject> getCompilationEinheit(JavaFileObject file) {
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
        return compilationUnits;
    }
    /**
     * Ruft die diverse Methoden auf um einen task auszuführen.<br>
     * der task wertet aus ob das kompilieren erfolgreich war.<br>
     *
     * @param eingabe Benutzer Eingabe
     * @param datentyp Datentyp der Methode
     * @param methodenname Namen der Methode
     * @param parameter Parameter der Mehtode
     * @param id AufgabenID
     *
     * @return ergebnis Gibt eine Tabelle zurück 
     */

    public static ArrayList<AbgeschlossenerTest> getErgebnis(String eingabe, String datentyp, String methodenname, String parameter, int id) {

        ArrayList<AbgeschlossenerTest> ergebnis = new ArrayList();
        JavaFileObject javafile = getJavaFileObject(eingabe, datentyp, methodenname, parameter);
        JavaCompiler compiler = getCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        JavaCompiler.CompilationTask task
                = compiler.getTask(null, null, diagnostics,
                        null, null, getCompilationEinheit(javafile));
        boolean success = task.call();
        System.out.println("Success: " + success);
        if (success) {
            ergebnis = successMethode(methodenname, id);
        } else {
            List<Diagnostic<? extends JavaFileObject>> diagnostics2 = diagnostics.getDiagnostics();
            int i = 0;
            ergebnis = failMethode(diagnostics2);
        }
        return ergebnis;
    }
    /**
     * Erstellt eine Method welche definiert und anschließend aufgerufen wird.<br>
     * Fragt ob der Test ein Array ist oder nicht.Ein Thread fängt Endlosschleifen ab.<br>
     *
     * @param methodenname Namen der Methode
     * @param id AufgabenID
     *
     * @return resultListe Gibt eine ErgebnisTabelle zurück.
     */


    public static ArrayList<AbgeschlossenerTest> successMethode(String methodenname, int id) {
        ArrayList<AbgeschlossenerTest> resultListe = new ArrayList();
        try {
            Connection con = DbTools.connect();
            Statement stmt = con.createStatement();

            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new File("").toURI().toURL()});
            Class klasse1 = Class.forName("CompilerKlasse", true, classLoader);

            ArrayList<Parameter> paraLis = InBetween.getAufgabenParameter(stmt, id);
            ArrayList<Test> testListe = InBetween.getTests(stmt, id);

            ArrayList<Class> classLis = new ArrayList();
            for (Parameter paraLi : paraLis) {
                classLis.add(paraLi.getKlasse());
            }
            Class[] classArr = new Class[classLis.size()];
            for (int i = 0; i < classLis.size(); i++) {
                classArr[i] = classLis.get(i);
            }

            Class[] parameter = classArr;
            Object testobj = klasse1.newInstance();
            Method test1 = klasse1.getDeclaredMethod(methodenname, parameter);
            ResultObj resultObj = new ResultObj();
            for (Test testCase1 : testListe) {
                boolean complete = false;
                String result = "";
                Thread timeoutThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            resultObj.setResultObj(test1.invoke(testobj, testCase1.getTestParameter()));
                        } catch (IllegalAccessException ex) {
                            System.out.println("IllegalAccessException " + ex);
                        } catch (IllegalArgumentException ex) {
                            System.out.println("IllegalArgumentException " + ex);
                        } catch (InvocationTargetException ex) {
                            System.out.println("InvocationTargetException " + ex);
                        }
                    }
                });

                timeoutThread.start();
                try {
                    timeoutThread.join(2200);
                } catch (InterruptedException ex) {
                    System.out.println("InterruptedException "+ ex);
                }

                if (resultObj.getClass().isArray()) {
                    result = "" + Arrays.deepToString((Object[]) resultObj.getResultObj());
                } else {
                    //result = "" + test1.invoke(testobj, testCase1.getTestParameter());
                    result = "" + resultObj.getResultObj();
                }

                String expResult = (String) testCase1.getExpResult();
//                expResult = expResult.replaceAll("\\s+", "");
//                result = result.replaceAll("\\s+", "");
                if (result.hashCode() == expResult.hashCode()) {
                    complete = true;
                }
                AbgeschlossenerTest abTest1 = new AbgeschlossenerTest(expResult, result, complete);
                resultListe.add(abTest1);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e);
        } catch (NoSuchMethodException e) {
            System.err.println("No such method: " + e);
        } catch (IllegalAccessException e) {
            System.err.println("Illegal access: " + e);
        } catch (InstantiationException e) {
            System.err.println("Instantiation target: " + e);
        } catch (MalformedURLException e) {
            System.err.println("MalformedURL: " + e);
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex);
        }
        return resultListe;
    }

   /**
     * Ruft die Diagnostics auf und fügt dieser in die resultListe <br>
     *
     * @param dias Diagnostics
     *
     * @return resultListe Gibt eine ErgebnisTabelle zurück.
     */

    public static ArrayList<AbgeschlossenerTest> failMethode(List<Diagnostic<? extends JavaFileObject>> dias) {
        ArrayList<AbgeschlossenerTest> resultListe = new ArrayList();
        AbgeschlossenerTest diaTest = new AbgeschlossenerTest("", "", false);
        diaTest.setDiagnostics(dias);
        diaTest.setNotcompiled(true);
        resultListe.add(diaTest);
        return resultListe;
    }

    /**
     * Gibt eine URLClassloader zurück<br>
     *
     * @return classLoader Gibt eine neue Instanze zurück.
     */
    public static URLClassLoader getKlassenLoader() {
        URLClassLoader classLoader = null;
        try {
            classLoader = URLClassLoader.newInstance(new URL[]{new File("").toURI().toURL()});
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        }
        return classLoader;
    }
    /**
     * Gibt eine Methode mit dem Namen methode zurück.<br>
     * Ruft weitere Methoden auf um eine Methode zu deklarieren.<br>
     *
     * @param methodenname Diagnostics
     *
     * @return methode Ist eine Methode.
     */

    public static Method getMethode(String methodenname) {
        Method methode = null;
        try {
            methode = getKlasse().getDeclaredMethod(methodenname);
        } catch (NoSuchMethodException ex) {
            System.err.println("No such method: " + ex);
        } catch (SecurityException ex) {
            System.err.println(ex);
        }
        return methode;
    }
   /**
     * Gibt eine Klasse mit dem Namen klasse zurück.<br>
     * Ruft die Methode getKlassenLoader() auf .<br>
     *
     * @return klasse Ist eine Klasse.
     */

    public static Class getKlasse() {
        Class klasse = null;
        try {
            klasse = Class.forName("CompilerKlasse", true, getKlassenLoader());
        } catch (ClassNotFoundException ex) {

        }
        return klasse;
    }
     /**
     * Gibt eine Objekt mit dem Namen testobj zurück.<br>
     * Ruft die Methode getKlasse().newInstance() auf .<br>
     *
     * @return testobj 
     */

    public static Object getObject() {
        Object testobj = null;
        try {
            testobj = getKlasse().newInstance();
        } catch (InstantiationException ex) {
            System.err.println("Instantiation target: " + ex);
        } catch (IllegalAccessException ex) {
            System.err.println("Invocation target: " + ex);
        }
        return testobj;
    }

}