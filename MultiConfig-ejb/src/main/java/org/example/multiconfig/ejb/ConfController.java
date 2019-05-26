/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.multiconfig.ejb;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.example.multiconfig.ejb.ann.ConfigValue;

/**
 *
 * @author dima
 */
public final class ConfController {

    private static ConfController instance;

    private ConfController() {
    }

    public static ConfController getInstance() {
        if (instance == null) {
            synchronized (ConfController.class) {
                if (instance == null) {
                    instance = new ConfController();
                }
            }
        }
        return instance;
    }

    public Map<String, String> getConfMap() {
        final Map<String, String> map = new HashMap<>();
        try {
            Context ctx = new InitialContext();
            final IntermediatorInterface it = (IntermediatorInterface) ctx.lookup("java:global/Intermediator");
            final Set<Class<?>> relevaltClasses = it.getRelevaltClasses();
            for (final Class<?> aClass : relevaltClasses) {
                final Field[] declaredFields = aClass.getDeclaredFields();
                Arrays.stream(declaredFields).filter((t) -> {
                    return t.isAnnotationPresent(ConfigValue.class);
                }).forEach((t) -> {
                    final ConfigValue annotation = t.getAnnotation(ConfigValue.class);
                    final String value = annotation.value();
                    String key = null;
                    try {
                        key = (String) t.get(null);
                        map.put(key, value);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(ConfController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(ConfController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            return map;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
