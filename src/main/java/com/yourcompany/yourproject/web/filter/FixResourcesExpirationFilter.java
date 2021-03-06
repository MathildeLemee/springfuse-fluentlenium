/*
 * (c) Copyright 2005-2011 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-primefaces:src/main/java/FixResourcesExpirationFilter.p.vm.java
 */
package com.yourcompany.yourproject.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Extend resources expiration. Note: we still have weird behavior with Chrome which seems to do not take into account our directive.
 */
public final class FixResourcesExpirationFilter implements Filter {

    private static final Logger logger = Logger.getLogger(FixResourcesExpirationFilter.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String uri = httpRequest.getRequestURI();
        if (uri.contains("/javax.faces.resource/")) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            logger.info("fixing expires header for " + uri);
            httpResponse.setDateHeader("Expires", System.currentTimeMillis() + 86400000000l); // in 1000 days            
            httpResponse.setHeader("Cache-Control", "public, max-age=86400000"); // 1000 days
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}