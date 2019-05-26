/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.multiconfig.ejb;

import javax.enterprise.context.ApplicationScoped;
import org.example.multiconfig.ejb.ann.ConfigValue;

/**
 *
 * @author dima
 */
@ApplicationScoped
public class CfgB implements Configurable {

    @ConfigValue("Value B")
    public static final String X = "value.b";

    @Override
    public Class<?> getClassInfo() {
        return CfgB.class;
    }
}
