package com.manivchuk.board.transport.mapper.board;

import com.manivchuk.board.persistence.entity.board.Board;
import com.manivchuk.board.transport.dto.board.BoardCreateDto;
import com.manivchuk.board.transport.dto.board.BoardOutcomeDto;
import com.manivchuk.board.transport.dto.board.BoardUpdateDto;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

@Mapper(imports = Collections.class)
@Setter(onMethod_ = @Autowired)
public abstract class BoardMapper {

    public abstract Board toEntity(BoardCreateDto dto);

    @Mapping(target = "user", source = "board.createdBy")
    public abstract BoardOutcomeDto toDto(Board board);

    public abstract Board toEntity(BoardUpdateDto dto, @MappingTarget Board board);

}
