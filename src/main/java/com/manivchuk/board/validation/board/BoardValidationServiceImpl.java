package com.manivchuk.board.validation.board;

import com.manivchuk.board.exception.standard.ForbiddenException;
import com.manivchuk.board.persistence.entity.board.Board;
import com.manivchuk.board.persistence.entity.user.User;
import com.manivchuk.board.service.user.role.RoleUtils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Setter(onMethod_ = @Autowired)
public class BoardValidationServiceImpl implements BoardValidationService {

    @Override
    public void validateCreation(Board board) {
        validateCreatingDeletingUpdatingPermissions();
    }

    @Override
    public void validateDeleting(Board board) {
        validateCreatingDeletingUpdatingPermissions();
    }

    @Override
    public void validateUpdating(Board board) {
        validateCreatingDeletingUpdatingPermissions();
    }

    private void validateCreatingDeletingUpdatingPermissions() {

    }
}
