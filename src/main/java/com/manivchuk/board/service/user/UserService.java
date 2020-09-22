package com.manivchuk.board.service.user;


import com.manivchuk.board.persistence.entity.user.User;
import com.manivchuk.board.service.actor.ActorService;
import com.manivchuk.board.transport.dto.user.UserCreateDto;
import com.manivchuk.board.transport.dto.user.UserOutcomeDto;
import com.manivchuk.board.transport.dto.user.UserUpdateDto;
import com.manivchuk.board.transport.dto.user.UserUpdateEmailDto;

import java.util.List;
import java.util.Optional;

public interface UserService extends ActorService {

    User create(UserCreateDto dto);

    User findByEmail(String email);

    Optional<User> findById(Long id);

    User findByIdUnsafe(Long id);

    void delete(Long id);

    Long recovery(Long id);

    Long update(Long id, UserUpdateDto dto);

    Long update(UserUpdateEmailDto dto);

    List<UserOutcomeDto> getAll();

    boolean existsByEmail(String email);

}
