package com.manivchuk.board.transport.dto.general;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdCreatedUpdatedDeletedDto extends IdCreatedUpdatedDto {

    private Boolean deleted;
}
