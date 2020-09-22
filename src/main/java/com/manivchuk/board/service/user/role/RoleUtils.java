package com.manivchuk.board.service.user.role;

import com.manivchuk.board.persistence.entity.user.User;
import com.manivchuk.board.persistence.entity.user.UserRole;

public class RoleUtils {

    public static boolean isUser(User user) {
        return user.getAuthorities().contains(UserRole.USER);
    }

    public static boolean isManager(User user) {
        return user.getAuthorities().contains(UserRole.MANAGER);
    }

    public static boolean isAdmin(User user) {
        return user.getAuthorities().contains(UserRole.ADMIN);
    }

}
