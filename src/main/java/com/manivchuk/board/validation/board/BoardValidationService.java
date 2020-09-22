package com.manivchuk.board.validation.board;

import com.manivchuk.board.persistence.entity.board.Board;
import com.manivchuk.board.service.actor.ActorService;

public interface BoardValidationService extends ActorService {

    void validateCreation(Board board);

    void validateDeleting(Board board);

    void validateUpdating(Board board);
}
