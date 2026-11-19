package org.wyttenbach.dale.groovy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Does not work in Eclipse
 * 
 * @author dwyttenb
 *
 */
public class GroovyScriptRunner {

  private final static Logger LOG = LoggerFactory.getLogger(GroovyScriptRunner.class);

  private Class<?> clazz;
  
  private String args[];
  
  public GroovyScriptRunner(Class<?> clazz) {
    this.clazz = clazz;
  }
  
  public GroovyScriptRunner(Class<?> clazz, String args[]) {
    this.clazz = clazz;
    this.args = args;
  }

  public static void main(String args[]) throws Throwable {
    new GroovyScriptRunner(GroovySystemTest.class).run();
  }

  /**
   * Invokes the class's main method, rethrowing whatever it throws so that
   * callers (e.g. JUnit) see the script's own failure.
   */
  public void run() throws Throwable {
    Method method = clazz.getMethod("main", String[].class);
    LOG.debug("method="+method);
    try {
      Object retval = method.invoke(null, (Object) args);
      LOG.debug("retval="+retval);
    } catch (InvocationTargetException e) {
      throw e.getCause();
    }
  }
}
