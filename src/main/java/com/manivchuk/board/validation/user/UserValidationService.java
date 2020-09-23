package com.manivchuk.board.validation.user;

import com.manivchuk.board.persistence.entity.user.User;
import com.manivchuk.board.service.actor.ActorService;
import com.manivchuk.board.transport.dto.user.UserUpdateEmailDto;
import com.manivchuk.board.transport.dto.user.UserUpdatePasswordDto;

public interface UserValidationService extends ActorService {

    void validateCreation(User user);

    void validateDeleting(User user);

    void validateUpdating(User user);

    void validateUpdatingEmail(User user, UserUpdateEmailDto dto);

    void validateRecovering(User user);

    void validatePassword(User user, UserUpdatePasswordDto dto);
}
