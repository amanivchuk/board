package com.manivchuk.board.transport.dto.board;

import com.manivchuk.board.transport.dto.user.UserOutcomeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BoardOutcomeDto {

    private Long id;

    private String title;

    private byte[] picture;

    private String text;

    private Date dateAdd;

    private UserOutcomeDto user;
}
