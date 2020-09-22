package com.manivchuk.board.configuration.security.filter;

import com.manivchuk.board.configuration.security.token.TokenAuthorizationService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

public class AuthorizationFilter extends GenericFilterBean {

    private final TokenAuthorizationService tokenAuthorizationService;

    public AuthorizationFilter(@NotNull TokenAuthorizationService tokenAuthorizationService) {
        this.tokenAuthorizationService = tokenAuthorizationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SecurityContextHolder.getContext()
                .setAuthentication(tokenAuthorizationService.getAuthentication((HttpServletRequest) request, (HttpServletResponse) response)
                        .orElse(null)
                );
        chain.doFilter(request, response);
    }
}
