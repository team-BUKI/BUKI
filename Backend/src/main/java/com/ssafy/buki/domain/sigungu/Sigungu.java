package com.ssafy.buki.domain.sigungu;

import com.ssafy.buki.domain.hobbyclass.HobbyClass;
import com.ssafy.buki.domain.interestregion.InterestRegion;
import com.ssafy.buki.domain.sido.Sido;
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
public class Sigungu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10)
    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_id")
    private Sido sido;

    @OneToMany(mappedBy = "sigungu", cascade = CascadeType.ALL)
    private List<InterestRegion> interestRegionList = new ArrayList<>();

    @OneToMany(mappedBy = "sigungu", cascade = CascadeType.ALL)
    private List<HobbyClass> hobbyClassList = new ArrayList<>();
}
