package com.manivchuk.board.configuration.security.token;

import com.manivchuk.board.configuration.security.properties.JWTProperties;
import com.manivchuk.board.persistence.entity.user.User;
import com.manivchuk.board.persistence.entity.user.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Configuration
@PropertySource("classpath:application.yml")
public class TokenHandler {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    public Optional<Long> getUserId(@NotNull String token){
        try{
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(JWTProperties.SECRET)
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();

            return Optional.of(Long.valueOf((Integer) body.get("userId")));

        }catch (RuntimeException e){
            return Optional.empty();
        }
    }

    public String generateAccessToken(User user){
        return Jwts.builder()
                .claim("role", takeRole(user))
                .claim("create", Instant.now().toString())
                .claim("expiration", Instant.now().plus(JWTProperties.EXPIRATION_TIME, ChronoUnit.HOURS).toEpochMilli())
                .claim("userId", user.getId())
                .signWith(SignatureAlgorithm.HS512, JWTProperties.SECRET)
                .compact();
    }

    @SuppressWarnings("unchecked")
    private UserRole takeRole(User user) {
        return ((List<UserRole>) user.getAuthorities()).get(0);
    }
}
