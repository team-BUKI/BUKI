package com.ssafy.buki.domain.interestcategory;

import com.ssafy.buki.domain.smallcategory.SmallCategory;
import com.ssafy.buki.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterestCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "smallCategory_id")
    private SmallCategory smallCategory;

    @Builder
    public InterestCategory(Long id, User user, SmallCategory smallCategory) {
        this.id = id;
        this.user = user;
        this.smallCategory = smallCategory;
    }
}
