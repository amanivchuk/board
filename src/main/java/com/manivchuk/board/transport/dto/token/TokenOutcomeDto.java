package com.manivchuk.board.transport.dto.token;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class TokenOutcomeDto {

    private String token;

    private Instant expiration;
}
