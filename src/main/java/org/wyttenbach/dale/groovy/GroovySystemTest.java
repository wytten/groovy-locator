package org.wyttenbach.dale.groovy;

import groovy.lang.Script;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

@RunWith(Parameterized.class)
public class GroovySystemTest {

  private final static Logger LOG = LoggerFactory.getLogger(GroovySystemTest.class);

  private Class<Script> cls;

  private static String targetScript;

  private static String args[] = new String[0];

  public GroovySystemTest(Class<Script> cls) {
    this.cls = cls;
  }

  @Test
  public void test() throws Exception {
    new GroovyScriptRunner(cls, args).run();
  }

  @Parameters(name="{0}")
  public static Collection<Object[]> data() {
    Collection<Object[]> data = new ArrayList<Object[]>();

    ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
    provider.addIncludeFilter(new AssignableTypeFilter(Script.class));

    Set<BeanDefinition> components = provider.findCandidateComponents("org.wyttenbach.dale.groovy");
    LOG.debug("set size="+components.size());
    for (BeanDefinition component : components) {
      try {
        if (targetScript == null || component.getBeanClassName().endsWith(targetScript)) {
          @SuppressWarnings("unchecked")
          Class<Script> cls = (Class<Script>) Class.forName(component.getBeanClassName());
          data.add(new Object[] { cls });
        }
      } catch (ClassNotFoundException e) {
        LOG.error(e.getLocalizedMessage(), e);
      }
    }

    LOG.debug("data size="+data.size());
    return data;
  }
  
  public static void main(String[] args) throws Exception {                    
    if (args.length > 0) {
      // run a single script
      targetScript = args[0];
      // pass additional arguments to the script
      GroovySystemTest.args = new String[args.length - 1];
      System.arraycopy(args, 1, GroovySystemTest.args, 0, args.length - 1);
    }
    JUnitCore.main(
      "org.wyttenbach.dale.groovy.GroovySystemTest");            
  }
}
