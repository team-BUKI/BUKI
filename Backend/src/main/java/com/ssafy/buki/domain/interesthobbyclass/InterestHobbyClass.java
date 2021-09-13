package com.ssafy.buki.domain.interesthobbyclass;

import com.ssafy.buki.domain.hobbyclass.HobbyClass;
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
public class InterestHobbyClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hobbyClass_id")
    private HobbyClass hobbyClass;

    @Builder
    public InterestHobbyClass(Long id, User user, HobbyClass hobbyClass) {
        this.id = id;
        this.user = user;
        this.hobbyClass = hobbyClass;
    }
}
