package com.monitor.security.jwt;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by grusz on 02.01.2017.
 */

public class JWTAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = new TokenAuthenticationService().getAuthentication((HttpServletRequest)request);

        System.out.println(((HttpServletRequest) request).getRequestURI());
        System.out.println(((HttpServletRequest) request).getRequestURI().contains("/view/systems"));

       if(((HttpServletRequest) request).getRequestURI().contains("/favicon.ico")) {

       }
       else{
           SecurityContextHolder.getContext().setAuthentication(authentication);
           filterChain.doFilter(request,response);
       }
    }

}