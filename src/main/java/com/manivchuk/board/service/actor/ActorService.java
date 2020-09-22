package com.manivchuk.board.service.actor;

import com.manivchuk.board.persistence.entity.user.User;
import com.manivchuk.board.service.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public interface ActorService {

    default User getActorFromContext() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))){
            return null;
        }

        return ApplicationContextProvider.getApplicationContext()
                .getBean(UserService.class)
                .findByIdUnsafe(((User) authentication.getPrincipal()).getId());
    }
}
