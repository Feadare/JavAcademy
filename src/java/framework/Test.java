package framework;

/**
 *
 * @author kiedrowski
 */
public class Test {

    Object expResult;
    Object[] testParameter;
    Object[][] testParameterArrays;

    public Test(Object hashedExpResult, Object[] testParameter) {
        this.expResult = hashedExpResult;
        this.testParameter = testParameter;
    }

    public Test(Object hashedExpResult, Object[][] testParameter) {
        this.expResult = hashedExpResult;
        Object[][] testParameterArr = {testParameter};
        this.testParameter = testParameterArr;
    }

    public Object getExpResult() {
        return expResult;
    }

    public Object[] getTestParameter() {
        return testParameter;
    }

    public Object[][] getTestParameterArrays() {
        return testParameterArrays;
    }

}
