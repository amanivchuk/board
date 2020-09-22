package com.manivchuk.board.persistence.repository;

import com.manivchuk.board.persistence.entity.token.Token;
import com.manivchuk.board.persistence.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long> {

    Token findByToken(String token);

    Optional<Token> findByUser(User user);
}
