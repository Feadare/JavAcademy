package framework;

/**
 * Eine Hilfsklasse in der Tests abgespeichert werden.
 * 
 *
 */
public class Test {


    Object expResult;
    Object[] testParameter;
    Object[][] testParameterArrays;
   /**
     * Erzeugt ein Test Object (Konstruktor) 
     * @param hashedExpResult das erwartete Ergebnis gehasht
     * @param testParameter ein Array mit allen Test Parametern
     */
    public Test(Object hashedExpResult, Object[] testParameter) {
        this.expResult = hashedExpResult;
        this.testParameter = testParameter;
    }
  /**
     * Erzeugt ein Test Object (Konstruktor) wird verwendet wenn in den Test Paramtern Arrays enthalten sind
     * @param hashedExpResult das erwartete Ergebnis gehasht
     * @param testParameter ein Array mit allen Test Parametern
     */
    public Test(Object hashedExpResult, Object[][] testParameter) {
        this.expResult = hashedExpResult;
        Object[][] testParameterArr = {testParameter};
        this.testParameter = testParameterArr;
    }
/**
     * Gibt das erwartete Ergebnis aus
     * @return erwartetes Ergebnis als Object
     */
    public Object getExpResult() {
        return expResult;
    }
/**
     * Gibt den Test Parameter Array als Object[] aus
     * @return Object[] mit allen TestParametern
     */
    public Object[] getTestParameter() {
        return testParameter;
    }
/**
     * Gibt den Test Parameter Array als Object[][] aus <br>
     * ist nötig wenn Arrays in den Testwerten sind
     * @return Object[] mit allen TestParametern
     */
    public Object[][] getTestParameterArrays() {
        return testParameterArrays;
    }

}
