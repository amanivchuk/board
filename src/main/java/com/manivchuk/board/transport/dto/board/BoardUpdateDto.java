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
public class BoardUpdateDto {

    @NotNull
    private Long id;

    @NotBlank
    @NotNull
    private String title;

    private byte[] picture;

    @NotBlank
    @NotNull
    private String text;

    private Date dateAdd;

}
