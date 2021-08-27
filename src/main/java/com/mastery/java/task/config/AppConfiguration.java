package com.mastery.java.task.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    //getServletConfigClasses() configures the dispatcher servlet and transfers the handler to dispatcher servlet java file WebConfig.class.
    // WebConfig.java file is used in place of dispatcher servlet for java based configuration.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    // getServletMappings() function receive all the requests corresponding to the ‘/ ’ URL mapping.
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
