package org.wyttenbach.dale.groovy;

import groovy.lang.Script;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

public class GroovyScriptLocator {

  private final static Logger LOG = LoggerFactory.getLogger(GroovyScriptLocator.class);

  public static void main(String args[]) {
    ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
    provider.addIncludeFilter(new AssignableTypeFilter(Script.class));

    Set<BeanDefinition> components = provider.findCandidateComponents("org.wyttenbach.dale.groovy");
    for (BeanDefinition component : components)
    {
        try {
          Class<?> cls = Class.forName(component.getBeanClassName());
          new GroovyScriptRunner(cls).run();
        } catch (ClassNotFoundException e) {
          LOG.error(e.getLocalizedMessage(), e);
        }
    }
  }
}
