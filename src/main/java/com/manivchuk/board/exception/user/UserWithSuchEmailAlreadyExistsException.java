package com.manivchuk.board.exception.user;


import com.manivchuk.board.exception.standard.BadRequestException;

public class UserWithSuchEmailAlreadyExistsException extends BadRequestException {

    public UserWithSuchEmailAlreadyExistsException() {
        super("User with such email already exists");
    }

    public UserWithSuchEmailAlreadyExistsException(String message) {
        super(message);
    }
}
