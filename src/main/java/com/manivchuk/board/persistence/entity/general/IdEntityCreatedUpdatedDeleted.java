package com.manivchuk.board.persistence.entity.general;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class IdEntityCreatedUpdatedDeleted extends IdEntityCreatedUpdated{

    @Column(nullable = false)
    private Boolean deleted = Boolean.FALSE;
}
