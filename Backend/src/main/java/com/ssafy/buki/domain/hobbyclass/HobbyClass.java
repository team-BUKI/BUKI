package com.ssafy.buki.domain.hobbyclass;

import com.ssafy.buki.domain.interesthobbyclass.InterestHobbyClass;
import com.ssafy.buki.domain.sido.Sido;
import com.ssafy.buki.domain.bigcategory.BigCategory;
import com.ssafy.buki.domain.sigungu.Sigungu;
import com.ssafy.buki.domain.smallcategory.SmallCategory;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HobbyClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    @NotNull
    private String title;

    @Enumerated(EnumType.STRING)
    private HobbyClassType type;

    @Column(length = 1000)
    @NotNull
    private String siteUrl;

    @Column(nullable = false)
    @NotNull
    private int price;

    @Column(length = 100)
    @NotNull
    private String site;

    @Column(nullable = false)
    @NotNull
    private int likeCnt;

    @Column(length = 1000)
    @NotNull
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bigCategory_id")
    private BigCategory bigCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "smallCategory_id")
    private SmallCategory smallCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sigungu_id")
    private Sigungu sigungu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_id")
    private Sido sido;

    @OneToMany(mappedBy = "hobbyClass", cascade = CascadeType.ALL)
    private List<InterestHobbyClass> interestHobbyClassList = new ArrayList<>();

}
