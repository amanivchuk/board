package com.manivchuk.board.validation.user;

import com.manivchuk.board.exception.standard.BadRequestException;
import com.manivchuk.board.exception.standard.ForbiddenException;
import com.manivchuk.board.exception.user.UserBadCredentialsException;
import com.manivchuk.board.exception.user.UserWithSuchEmailAlreadyExistsException;
import com.manivchuk.board.persistence.entity.user.User;
import com.manivchuk.board.service.user.UserService;
import com.manivchuk.board.service.user.role.RoleUtils;
import com.manivchuk.board.transport.dto.user.UserUpdateEmailDto;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Setter(onMethod_ = @Autowired)
public class UserValidationServiceImpl implements UserValidationService {

    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Override
    public void validateCreation(User user) {
        validateCreationPermissions(user);
        validateUniqueEmail(user.getEmail());
    }

    @Override
    public void validateDeleting(User user) {
        validateDeletingRecoveringPermissions(user);
    }

    @Override
    public void validateUpdating(User user) {
//        validateUpdatingPermissions(user);
    }

    @Override
    public void validateUpdatingEmail(User user, UserUpdateEmailDto dto) {
//        validateUpdatingPermissions(user);
        validateOldAndNewEmailNotEqual(dto);
        validateTruePassword(dto);
        validateOldEmail(dto);
        validateUniqueEmail(dto.getNewEmail());
    }

    @Override
    public void validateRecovering(User user) {
        validateDeletingRecoveringPermissions(user);
    }

    private void validateOldAndNewEmailNotEqual(UserUpdateEmailDto dto) {
        if(dto.getNewEmail().equals(dto.getOldEmail())){
            throw new BadRequestException("New email must be different from old");
        }
    }

    private void validateTruePassword(UserUpdateEmailDto dto) {
        User actor = getActorFromContext();

        if (!passwordEncoder.matches(dto.getPassword(), actor.getPassword())) {
            throw new UserBadCredentialsException();
        }
    }

    private void validateUniqueEmail(String email) {
        if(userService.existsByEmail(email)){
            throw new UserWithSuchEmailAlreadyExistsException();
        }
    }

    private void validateOldEmail(UserUpdateEmailDto dto) {
        User actor = getActorFromContext();

        if(!dto.getOldEmail().equals(actor.getEmail())){
            throw new UserBadCredentialsException();
        }
    }

    private void validateUpdatingPermissions(User user) {
        User actor = getActorFromContext();

        if (RoleUtils.isAdmin(actor)) {
            if (!RoleUtils.isAdmin(user)) {
                throw new ForbiddenException();
            }
        }

        if (RoleUtils.isManager(actor)) {
            if (!RoleUtils.isManager(user)) {
                throw new ForbiddenException();
            }
        }

        if (RoleUtils.isUser(actor)) {
            if (!RoleUtils.isUser(user)) {
                throw new ForbiddenException();
            }
        }
    }

    private void validateDeletingRecoveringPermissions(User user) {
        User actor = getActorFromContext();

        if (RoleUtils.isAdmin(actor)) {
            if (RoleUtils.isUser(user)) {
                throw new ForbiddenException(); //todo because admin can only blocked & unblocked user but not delete!!!
            }
        }

        if (RoleUtils.isManager(actor)) {
            if (!RoleUtils.isManager(user)) {
                throw new ForbiddenException();
            }
        }

        if (RoleUtils.isUser(actor)) {
            if (!RoleUtils.isUser(user)) {
                throw new ForbiddenException();
            }
        }
    }

    private void validateCreationPermissions(User user) {
        User actor = getActorFromContext();

        if (RoleUtils.isManager(actor)) {
            if (!RoleUtils.isManager(user)) {
                throw new ForbiddenException();
            }
        }

        if (RoleUtils.isUser(actor)) {
            throw new ForbiddenException();
        }
    }
}
