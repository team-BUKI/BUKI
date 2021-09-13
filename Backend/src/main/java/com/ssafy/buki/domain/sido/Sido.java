package com.ssafy.buki.domain.sido;

import com.ssafy.buki.domain.hobbyclass.HobbyClass;
import com.ssafy.buki.domain.sigungu.Sigungu;
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
public class Sido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "sido", cascade = CascadeType.ALL)
    private List<Sigungu> sigunguList = new ArrayList<>();

    @OneToMany(mappedBy = "sido", cascade = CascadeType.ALL)
    private List<HobbyClass> hobbyClassList = new ArrayList<>();
}
