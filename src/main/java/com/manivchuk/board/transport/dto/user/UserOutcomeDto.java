package com.manivchuk.board.transport.dto.user;

import com.manivchuk.board.transport.dto.general.IdCreatedUpdatedDeletedDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserOutcomeDto extends IdCreatedUpdatedDeletedDto {

    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String phone;
}
