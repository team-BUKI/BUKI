package com.ssafy.buki.domain.diary;

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
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    @NotNull
    private String content;

    @Column(length = 1000)
    @NotNull
    private String image;

    @Column(length = 45)
    @NotNull
    private String smallCategoryName;

    @NotNull
    private Boolean share;

    @NotNull
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bigCategory_id")
    private BigCategory bigCategory;

    @Builder
    public Diary(Long id, String content, String image, String smallCategoryName, Boolean share, LocalDateTime date, User user, BigCategory bigCategory) {
        this.id = id;
        this.content = content;
        this.image = image;
        this.smallCategoryName = smallCategoryName;
        this.share = share;
        this.date = date;
        this.user = user;
        this.bigCategory = bigCategory;
    }
}
