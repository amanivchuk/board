package com.manivchuk.board.exception.user;


import com.manivchuk.board.exception.standard.UnauthorizedException;

public class UserBadCredentialsException extends UnauthorizedException {

    public UserBadCredentialsException() {
        super("Invalid email or password");
    }

    public UserBadCredentialsException(String message) {
        super(message);
    }
}
