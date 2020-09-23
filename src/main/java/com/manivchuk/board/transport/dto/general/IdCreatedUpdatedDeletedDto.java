package com.manivchuk.board.transport.dto.general;

import com.manivchuk.board.persistence.entity.general.IdEntity;
import com.manivchuk.board.persistence.entity.general.IdEntityCreatedUpdated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdCreatedUpdatedDeletedDto {

    private Boolean deleted;
}
