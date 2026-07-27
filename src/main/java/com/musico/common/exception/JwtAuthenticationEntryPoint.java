package com.musico.common.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint
        implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException)
            throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        String path = (String) request.getAttribute("jakarta.servlet.forward.request_uri");

        if (path == null) {
            path = request.getRequestURI();
        }

        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Unauthorized")
                .message("Authentication is required to access this resource.")
                .path(path)
                .status(HttpServletResponse.SC_UNAUTHORIZED)
                .timestamp(Instant.now())
                .validationErrors(null)
                .build();

        response.getWriter().write(
                objectMapper.writeValueAsString(errorResponse)
        );

        System.out.println("RequestURI : " + request.getRequestURI());
        System.out.println("ServletPath: " + request.getServletPath());
        System.out.println("Forward URI: " +
                request.getAttribute("jakarta.servlet.forward.request_uri"));
        System.out.println("Error URI  : " +
                request.getAttribute("jakarta.servlet.error.request_uri"));
    }
}
