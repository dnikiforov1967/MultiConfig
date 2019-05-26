/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.multiconfig.ejb;

import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author dima
 */
@Local
public interface IntermediatorInterface {
    
    Set<Class<?>> getRelevaltClasses();
    
}
