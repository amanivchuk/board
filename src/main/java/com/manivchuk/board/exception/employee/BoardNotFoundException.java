package com.manivchuk.board.exception.employee;

import com.manivchuk.board.exception.standard.BadRequestException;

public class BoardNotFoundException extends BadRequestException {

    public BoardNotFoundException() {
        super("Board not found");
    }

    public BoardNotFoundException(String message) {
        super(message);
    }
}
