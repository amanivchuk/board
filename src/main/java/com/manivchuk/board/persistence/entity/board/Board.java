package com.manivchuk.board.persistence.entity.board;

import com.manivchuk.board.persistence.entity.general.IdEntityCreatedUpdatedDeleted;
import com.manivchuk.board.persistence.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "board")
public class Board extends IdEntityCreatedUpdatedDeleted {

    private String title;

    @Column(columnDefinition = "BYTEA")
    private byte[] picture;

    private String text;

    private Date dateAdd;

}
