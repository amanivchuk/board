package com.manivchuk.board.service.board;

import com.manivchuk.board.persistence.entity.board.Board;
import com.manivchuk.board.service.actor.ActorService;
import com.manivchuk.board.transport.dto.board.BoardCreateDto;
import com.manivchuk.board.transport.dto.board.BoardOutcomeDto;
import com.manivchuk.board.transport.dto.board.BoardUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface BoardService extends ActorService {

    Board create(BoardCreateDto dto);

    Optional<Board> findById(Long id);

    Board findByIdUnsafe(Long id);

    void delete(Long id);

    Long update(BoardUpdateDto dto);

    List<BoardOutcomeDto> getAll();

    Page findByParams(String lastName, PageRequest pageRequest);
}
