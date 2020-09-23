package com.manivchuk.board.transport.dto.user;

import com.manivchuk.board.persistence.entity.user.UserRole;
import com.manivchuk.board.validation.userrole.UserRoleSubset;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    @NotBlank
    @NotNull
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @UserRoleSubset(anyOf = {UserRole.ADMIN, UserRole.MANAGER, UserRole.USER})
    private UserRole userRole;
}
