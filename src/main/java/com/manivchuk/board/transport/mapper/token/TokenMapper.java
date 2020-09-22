package com.manivchuk.board.transport.mapper.token;

import com.manivchuk.board.persistence.entity.token.Token;
import com.manivchuk.board.transport.dto.token.TokenOutcomeDto;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
@Setter(onMethod_ = @Autowired)
public abstract class TokenMapper {

    @Mapping(target = "expiration", expression = "java(token.getExpiration())")
    public abstract TokenOutcomeDto toDto(Token token);
}
