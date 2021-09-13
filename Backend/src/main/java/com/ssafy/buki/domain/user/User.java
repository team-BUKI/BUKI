package com.ssafy.buki.domain.user;

import com.ssafy.buki.domain.diary.Diary;
import com.ssafy.buki.domain.interestcategory.InterestCategory;
import com.ssafy.buki.domain.interesthobbyclass.InterestHobbyClass;
import com.ssafy.buki.domain.interestregion.InterestRegion;
import com.ssafy.buki.domain.secondcharacter.SecondCharacter;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    @NotNull
    private String email;

    @Column(length = 10)
    @NotNull
    private String nickname;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(length = 50)
    private String secondcharacterNickname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<InterestCategory> interestCategoryList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<InterestRegion> interestRegionList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Diary> diaryList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SecondCharacter> secondCharacterList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<InterestHobbyClass> interestHobbyClassList = new ArrayList<>();

    @Builder
    public User(Long id, String email, String nickname, SocialType socialType, String secondcharacterNickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.socialType = socialType;
        this.secondcharacterNickname = secondcharacterNickname;
    }
}