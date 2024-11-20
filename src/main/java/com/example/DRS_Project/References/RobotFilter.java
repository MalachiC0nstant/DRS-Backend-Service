//package com.example.DRS_Project;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.awt.*;
//import java.io.IOException;
//import java.util.Collections;
//
//public class RobotFilter extends OncePerRequestFilter {
//    private final AuthenticationManager authenticationManager;
//    private final String HEADER_NAME = "x-robot-password";
//
//    public RobotFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        // Should we execute the filter?
//        if (!Collections.list(request.getHeaderNames()).contains(HEADER_NAME)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        // Authentication decision
//        var password = request.getHeader("x-robot-password");
//        var authRequest = RobotAuthentication.unauthenticated(password);
//        try {
//            var authentication = authenticationManager.authenticate(authRequest);
//            var newContext = SecurityContextHolder.createEmptyContext();
//            newContext.setAuthentication(authentication);
//            SecurityContextHolder.setContext(newContext);
//            filterChain.doFilter(request, response);
//        }
//        catch (AuthenticationException e){
//            response.setStatus(HttpStatus.FORBIDDEN.value());
//            response.setCharacterEncoding("utf-8");
//            response.setHeader("Content-type", "text/plain);charset=utf-8");
//            response.getWriter().println(e.getMessage());
//            return;
//        }
//    }
//}
