package com.ssafy.buki.domain.user;

import com.ssafy.buki.domain.clickedhobbyclass.ClickedHobbyClass;
import com.ssafy.buki.domain.diary.Diary;
import com.ssafy.buki.domain.interestcategory.InterestCategory;
import com.ssafy.buki.domain.interesthobbyclass.InterestHobbyClass;
import com.ssafy.buki.domain.interestregion.InterestRegion;
import com.ssafy.buki.domain.secondcharacter.SecondCharacter;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
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
//    @NotNull
    private String nickname;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    //인증 위한 ROLE_TYPE 추가
    @Column(name = "ROLE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

    @Column(length = 50)
    @ColumnDefault("초보")
    private String secondcharacterNicknameAdj;

    @Column(length = 45)
    private String secondcharacterNicknameNoun;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ClickedHobbyClass> clickedHobbyClassList = new ArrayList<>();

    @Builder
    public User(Long id, String email, String nickname, SocialType socialType, RoleType roleType, String secondcharacterNicknameAdj, String secondcharacterNicknameNoun) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.socialType = socialType;
        this.roleType = roleType;
        this.secondcharacterNicknameAdj = secondcharacterNicknameAdj;
        this.secondcharacterNicknameNoun = secondcharacterNicknameNoun;
    }

    //소셜 회원가입
    @Builder
    public User(String email, SocialType socialType, RoleType roleType) {
        this.email = email;
        this.secondcharacterNicknameAdj = "초보";
        this.socialType = socialType;
        this.roleType = roleType;
    }
}
