package com.homeo.clinic.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication
        .UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context
        .SecurityContextHolder;

import org.springframework.security.web.authentication
        .WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;

import org.springframework.web.filter
        .OncePerRequestFilter;

import java.io.IOException;

import java.util.Collections;

//@Component

public class JwtFilter
        extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(

            HttpServletRequest request,

            HttpServletResponse response,

            FilterChain filterChain

    ) throws ServletException, IOException {

        // REQUEST PATH

        String path =
                request.getServletPath();

        /*
        =========================================
        PUBLIC APIS
        =========================================
        */

        if (

                path.startsWith("/api/auth")

                        ||

                        path.startsWith("/api/patients")

        ) {

            filterChain.doFilter(
                    request,
                    response
            );

            return;
        }

        /*
        =========================================
        AUTH HEADER
        =========================================
        */

        final String authHeader =
                request.getHeader(
                        "Authorization"
                );

        String jwt = null;

        String username = null;

        /*
        =========================================
        CHECK BEARER TOKEN
        =========================================
        */

        if (

                authHeader != null

                        &&

                        authHeader.startsWith(
                                "Bearer "
                        )

        ) {

            jwt =
                    authHeader.substring(7);

            try {

                username =
                        jwtUtil.extractUsername(jwt);

            } catch (Exception e) {

                System.out.println(
                        "Invalid Token"
                );
            }
        }

        /*
        =========================================
        VALIDATE TOKEN
        =========================================
        */

        if (

                username != null

                        &&

                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                == null

        ) {

            boolean valid =
                    jwtUtil.validateToken(
                            jwt,
                            username
                    );

            if (valid) {

                UsernamePasswordAuthenticationToken authToken =

                        new UsernamePasswordAuthenticationToken(

                                username,

                                null,

                                Collections.emptyList()

                        );

                authToken.setDetails(

                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)

                );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);
            }
        }

        /*
        =========================================
        CONTINUE
        =========================================
        */

        filterChain.doFilter(
                request,
                response
        );
    }
}