package com.manivchuk.board.persistence.entity.token;

import com.manivchuk.board.persistence.entity.general.IdEntityExpiration;
import com.manivchuk.board.persistence.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"token", "user_id"}))
public class Token extends IdEntityExpiration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public Token(Instant expiration, String token, User user) {
        super(expiration);
        this.token = token;
        this.user = user;
    }
}
