package com.manivchuk.board.service.authorization;


import com.manivchuk.board.service.actor.ActorService;
import com.manivchuk.board.transport.dto.authorization.LoginDto;
import com.manivchuk.board.transport.dto.token.TokenOutcomeDto;

public interface AuthorizationService extends ActorService {

    TokenOutcomeDto login(LoginDto dto);

    void logout();
}
