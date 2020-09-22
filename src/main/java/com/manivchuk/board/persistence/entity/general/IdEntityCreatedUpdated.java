package com.manivchuk.board.persistence.entity.general;

import com.manivchuk.board.persistence.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class IdEntityCreatedUpdated extends IdEntity{

    @CreatedBy
    @OneToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @CreatedDate
    @Column(nullable = false)
    private Instant createdAt;

    @LastModifiedBy
    @OneToOne
    @JoinColumn(name = "updated_by_id")
    private User updatedBy;

    @LastModifiedDate
    @JoinColumn(nullable = false)
    private Instant updatedAt;
}
