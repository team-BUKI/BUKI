package com.ssafy.buki.domain.secondcharacter;

import com.ssafy.buki.domain.bigcategory.BigCategory;
import com.ssafy.buki.domain.user.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SecondCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private int exp;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private Boolean represent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bigCategory_id")
    private BigCategory bigCategory;

    @Builder
    public SecondCharacter(Long id, int exp, LocalDateTime date, Boolean represent, User user, BigCategory bigCategory) {
        this.id = id;
        this.exp = exp;
        this.date = date;
        this.represent = represent;
        this.user = user;
        this.bigCategory = bigCategory;
    }
}
