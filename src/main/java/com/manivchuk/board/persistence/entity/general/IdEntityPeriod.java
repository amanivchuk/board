package com.manivchuk.board.persistence.entity.general;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class IdEntityPeriod extends IdEntity{

    @Column(nullable = false)
    protected LocalDate begin;

    @Column(nullable = false)
    protected LocalDate end;
}
