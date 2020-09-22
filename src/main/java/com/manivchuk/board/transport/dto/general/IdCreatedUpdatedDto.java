package com.manivchuk.board.transport.dto.general;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class IdCreatedUpdatedDto {

    private Long createdById;

    private Instant createdAt;

    private Long updatedById;

    private Instant updatedAt;
}
