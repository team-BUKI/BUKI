package com.ssafy.buki.domain.interestregion;

import com.ssafy.buki.domain.sigungu.Sigungu;
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
public class InterestRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sigungu_id")
    private Sigungu sigungu;

    @Builder
    public InterestRegion(Long id, User user, Sigungu sigungu) {
        this.id = id;
        this.user = user;
        this.sigungu = sigungu;
    }
}
