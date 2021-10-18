package com.ssafy.buki.domain.smallcategory;

import com.ssafy.buki.domain.bigcategory.BigCategory;
import com.ssafy.buki.domain.interestcategory.InterestCategory;

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
public class SmallCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45)
    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bigCategory_id")
    private BigCategory bigCategory;

    @OneToMany(mappedBy = "smallCategory", cascade = CascadeType.ALL)
    private List<InterestCategory> interestCategoryList = new ArrayList<>();
}
