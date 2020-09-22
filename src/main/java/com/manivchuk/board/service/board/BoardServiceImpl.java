package com.manivchuk.board.service.board;

import com.manivchuk.board.exception.employee.BoardNotFoundException;
import com.manivchuk.board.persistence.entity.board.Board;
import com.manivchuk.board.persistence.entity.user.User;
import com.manivchuk.board.persistence.repository.BoardRepository;
import com.manivchuk.board.transport.dto.board.BoardCreateDto;
import com.manivchuk.board.transport.dto.board.BoardOutcomeDto;
import com.manivchuk.board.transport.dto.board.BoardUpdateDto;
import com.manivchuk.board.transport.mapper.board.BoardMapper;
import com.manivchuk.board.validation.board.BoardValidationService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Setter(onMethod_ = @Autowired)
public class BoardServiceImpl implements BoardService {

    private BoardRepository boardRepository;
    private BoardMapper boardMapper;
    private BoardValidationService boardValidationService;

    @Override
    public Board create(BoardCreateDto dto) {
        Board board = boardMapper.toEntity(dto);

        User actor = getActorFromContext();
        board.setCreatedAt(Instant.now());
        board.setCreatedBy(actor);
        board.setUpdatedAt(Instant.now());
        board.setUpdatedBy(actor);
//        board.setAuthor(actor.getLastName());
        boardValidationService.validateCreation(board);

        return boardRepository.save(board);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    @Transactional
    public Board findByIdUnsafe(Long id) {
        return boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        Board board = findByIdUnsafe(id);

        boardValidationService.validateDeleting(board);

        boardRepository.delete(board);

    }

    @Override
    public Long update(BoardUpdateDto dto) {
        Board board = findByIdUnsafe(dto.getId());

        boardValidationService.validateUpdating(board);

        User actor = getActorFromContext();
        board.setUpdatedBy(actor);
        board.setUpdatedAt(Instant.now());

        return boardMapper.toEntity(dto, board).getId();
    }


    @Override
    public List<BoardOutcomeDto> getAll() {
        return boardRepository.findAll().stream()
                .map(boardMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Page findByParams(String title, PageRequest paging) {
        Page<Board> page = boardRepository.findByParams(title, paging);

        Page<BoardOutcomeDto> pageDto = page.map(boardMapper::toDto);

        return pageDto;
    }
}
