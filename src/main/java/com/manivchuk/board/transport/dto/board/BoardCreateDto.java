package com.manivchuk.board.transport.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateDto {

    @NotBlank
    @NotNull
    private String title;

    private byte[] photo;

    @NotBlank
    @NotNull
    private String text;

    private Date dateAdd;

}
