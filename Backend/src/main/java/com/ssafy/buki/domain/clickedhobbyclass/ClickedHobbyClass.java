package com.ssafy.buki.domain.clickedhobbyclass;

import com.ssafy.buki.domain.hobbyclass.HobbyClass;
import com.ssafy.buki.domain.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
//@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClickedHobbyClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hobbyClass_id")
    private HobbyClass hobbyClass;

}
