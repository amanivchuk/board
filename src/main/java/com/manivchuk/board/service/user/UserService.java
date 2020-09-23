package com.manivchuk.board.service.user;


import com.manivchuk.board.persistence.entity.user.User;
import com.manivchuk.board.service.actor.ActorService;
import com.manivchuk.board.transport.dto.user.*;

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

    UserOutcomeDto get(Long id);

    boolean existsByEmail(String email);

    Long updatePassword(Long id, UserUpdatePasswordDto dto);
}
