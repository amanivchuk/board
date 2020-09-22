package com.manivchuk.board.configuration.security.properties;

public class JWTProperties {

    public static final String SECRET = "SECURITY";
    public static final Long EXPIRATION_TIME = 864L;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
