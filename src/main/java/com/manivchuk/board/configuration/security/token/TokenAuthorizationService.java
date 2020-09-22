package com.manivchuk.board.configuration.security.token;

import com.manivchuk.board.configuration.security.authentication.UserAuthentication;
import com.manivchuk.board.configuration.security.properties.JWTProperties;
import com.manivchuk.board.service.token.TokenService;
import com.manivchuk.board.service.user.UserService;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Optional;

@Service
@Setter(onMethod_ = @Autowired)
public class TokenAuthorizationService {

    private TokenService tokenService;
    private TokenHandler tokenHandler;
    private UserService userService;

    public Optional<Authentication> getAuthentication(@NonNull HttpServletRequest request, HttpServletResponse response) {
        String tokenFromRequest = getTokenFromRequest(request);
        if (tokenFromRequest != null) {
            return Optional
                    .of(tokenFromRequest)
                    .flatMap(tokenHandler::getUserId)
                    .flatMap(userService::findById)
                    .filter(user -> !user.getDeleted())
                    .map(user -> {
                        tokenService.extendExpiration(user.getToken());
                        return new UserAuthentication(user, true);
                    });
        }
        if (
                request.getRequestURI().equals("/authentication/login")
                        || request.getRequestURI().equals("/swagger-ui.html")
                        || request.getRequestURI().startsWith("/webjars")
                        || request.getRequestURI().startsWith("/swagger-resources")
                        || request.getRequestURI().equals("/v2/api-docs")
                        || request.getRequestURI().equals("/")
                        || request.getRequestURI().equals("/error")
                        || request.getRequestURI().equals("/csrf")
        ) {
            return Optional.of(new UserAuthentication(null, true));
        }
        return Optional.empty();
    }

    private String getTokenFromRequest(@NonNull HttpServletRequest request) {
        String authorizationHeader = request.getHeader(JWTProperties.HEADER_STRING);
        if (authorizationHeader != null && authorizationHeader.startsWith(JWTProperties.TOKEN_PREFIX)) {
            if (tokenService.getByToken(authorizationHeader.substring(7)) != null
                    && tokenService.getByToken(
                    authorizationHeader.substring(7)).getExpiration().toEpochMilli() > Instant.now().toEpochMilli()
            ) {
                return authorizationHeader.substring(7);
            }
        }
        return null;
    }
}
