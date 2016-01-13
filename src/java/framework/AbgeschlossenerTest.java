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
 *
 * @author kiedrowski
 */
public class AbgeschlossenerTest {

    Object expResult;
    Object result;
    boolean success;
    List<Diagnostic<? extends JavaFileObject>> diagnostics;
    boolean notcompiled;

    public AbgeschlossenerTest(Object expResult, Object result, boolean success) {
        this.expResult = expResult;
        this.result = result;
        this.success = success;
    }

    public Object getExpResult() {
        return expResult;
    }

    public Object getResult() {
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isNotcompiled() {
        return notcompiled;
    }

    public void setNotcompiled(boolean notcompiled) {
        this.notcompiled = notcompiled;
    }

    public List<Diagnostic<? extends JavaFileObject>> getDiagnostics() {
        return diagnostics;
    }

    public void setDiagnostics(List<Diagnostic<? extends JavaFileObject>> diagnostics) {
        this.diagnostics = diagnostics;
    }

    @Override
    public String toString() {
        return result + " " + success;
    }

}
