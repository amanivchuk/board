package com.manivchuk.board.transport.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserUpdateDto {

    @NotBlank
    @NotNull
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

}
