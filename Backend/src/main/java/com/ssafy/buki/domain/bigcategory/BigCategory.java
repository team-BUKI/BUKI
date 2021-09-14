package com.ssafy.buki.domain.bigcategory;

import com.ssafy.buki.domain.diary.Diary;
import com.ssafy.buki.domain.secondcharacter.SecondCharacter;
import com.ssafy.buki.domain.smallcategory.SmallCategory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BigCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    @NotNull
    private String name;

    @Column(length = 100)
    private String level1Image;

    @Column(length = 100)
    private String level2Image;

    @Column(length = 100)
    private String level3Image;

    @OneToMany(mappedBy = "bigCategory", cascade = CascadeType.ALL)
    private List<SmallCategory> smallCategoryList = new ArrayList<>();

    @OneToMany(mappedBy = "bigCategory", cascade = CascadeType.ALL)
    private List<Diary> diaryList = new ArrayList<>();

    @OneToMany(mappedBy = "bigCategory", cascade = CascadeType.ALL)
    private List<SecondCharacter> secondCharacterList = new ArrayList<>();
}
