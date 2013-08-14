package org.wyttenbach.dale.groovy;

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

  public static void main(String args[]) {
    new GroovyScriptRunner(GroovyScriptLocator.class).run();
  }
  
  public void run() {
    try {
      Method method = clazz.getMethod("main", String[].class);
      LOG.debug("method="+method);
      Object retval = method.invoke(null, (Object) args); 
      LOG.debug("retval="+retval);
    } catch (Exception e) {
      LOG.error(e.getLocalizedMessage(), e);
    }
  }
}
