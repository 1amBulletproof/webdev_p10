/** 
 * ApplicationConfig Class
 * - Configure rest service
 * 
 * @author Brandon Tarney
 * @since 08/11/2017
 */
package com.brandontarney.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Tarney
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.brandontarney.service.Service.class);
    }
    
}
