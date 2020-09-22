package com.manivchuk.board.service.authorization;

import com.manivchuk.board.configuration.security.properties.JWTProperties;
import com.manivchuk.board.configuration.security.token.TokenHandler;
import com.manivchuk.board.exception.user.UserBadCredentialsException;
import com.manivchuk.board.exception.user.UserNotFoundException;
import com.manivchuk.board.persistence.entity.token.Token;
import com.manivchuk.board.persistence.entity.user.User;
import com.manivchuk.board.service.token.TokenService;
import com.manivchuk.board.service.user.UserService;
import com.manivchuk.board.transport.dto.authorization.LoginDto;
import com.manivchuk.board.transport.dto.token.TokenOutcomeDto;
import com.manivchuk.board.transport.mapper.token.TokenMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@Setter(onMethod_ = @Autowired)
@Transactional
public class AuthorizationServiceImpl implements AuthorizationService {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private TokenHandler tokenHandler;
    private TokenService tokenService;
    private TokenMapper tokenMapper;

    @Override
    public TokenOutcomeDto login(LoginDto dto) {
        User actor = userService.findByEmail(dto.getEmail());

        verification(actor);

        if(!passwordEncoder.matches(dto.getPassword(), actor.getPassword())){
            throw new UserBadCredentialsException();
        }

        Instant tokenExpiration = Instant.now()
                .plus(
                        JWTProperties.EXPIRATION_TIME, ChronoUnit.HOURS
                );

        String tokenValue =tokenHandler.generateAccessToken(actor);
        Token token = actor.getToken();

        if(token != null){
            token.setToken(tokenValue);
            token.setExpiration(tokenExpiration);
        }else{
            token = new Token(
                    tokenExpiration,
                    tokenValue,
                    actor
            );
        }
        tokenService.create(token);
        return tokenMapper.toDto(token);
    }

    @Override
    public void logout() {
        User actor = getActorFromContext();
        tokenService.delete(tokenService.findByUser(actor).orElse(null));
    }

    public void verification(User actor){
        if(actor.getDeleted()){
            throw new UserNotFoundException("User with email " + actor.getEmail() + " deleted");
        }
    }
}
