package framework;

/**
 *
 * @author kiedrowski
 */
public class Test {

    Object expResult;
    Object[] testParameter;

    public Test(Object hashedExpResult, Object[] testParameter) {
        this.expResult = hashedExpResult;
        this.testParameter = testParameter;
    }

    public Object getExpResult() {
        return expResult;
    }

    public Object[] getTestParameter() {
        return testParameter;
    }


}
