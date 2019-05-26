/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.multiconfig.ejb;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 *
 * @author dima
 */
@Singleton
@Local(IntermediatorInterface.class)
@EJB(name="java:global/Intermediator", beanInterface = IntermediatorInterface.class)
public class Intermediator implements IntermediatorInterface {

    @Inject
    private Instance<Configurable> confInstance;
    
    final private Set<Class<?>> classSet = new HashSet<>();
    
    @PostConstruct
    private void init() {
        final Iterator<Configurable> iterator = confInstance.iterator();
        iterator.forEachRemaining(t->{
            final Class<?> classInfo = t.getClassInfo();
            classSet.add(classInfo);
        });
    }

    @Override
    public Set<Class<?>> getRelevaltClasses() {
        return classSet;
    }
    
}
