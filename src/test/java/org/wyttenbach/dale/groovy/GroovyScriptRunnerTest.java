package org.wyttenbach.dale.groovy;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class GroovyScriptRunnerTest {

  public static class Boom {
    public static void main(String args[]) {
      throw new IllegalStateException("boom");
    }
  }

  @Test(expected = IllegalStateException.class)
  public void runRethrowsScriptFailure() throws Throwable {
    new GroovyScriptRunner(Boom.class, new String[0]).run();
  }

  @Test
  public void discoversGroovyScripts() {
    List<String> names = new ArrayList<String>();
    for (Object[] params : GroovySystemTest.data()) {
      names.add(((Class<?>) params[0]).getSimpleName());
    }
    assertTrue(names.toString(), names.contains("Funky"));
    assertTrue(names.toString(), names.contains("Winkerbean"));
  }

  @Test
  public void systemTestRunsAllScripts() {
    Result result = JUnitCore.runClasses(GroovySystemTest.class);
    assertTrue(result.getFailures().toString(), result.wasSuccessful());
  }
}
