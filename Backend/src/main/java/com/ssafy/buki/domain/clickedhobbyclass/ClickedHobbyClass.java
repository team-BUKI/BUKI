package com.ssafy.buki.domain.clickedhobbyclass;

import com.ssafy.buki.domain.bigcategory.BigCategory;
import com.ssafy.buki.domain.hobbyclass.HobbyClass;
import com.ssafy.buki.domain.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @NotNull
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hobbyClass_id")
    private HobbyClass hobbyClass;

    @Builder
    public ClickedHobbyClass(Integer count, LocalDateTime date, User user, HobbyClass hobbyClass) {
        this.count = count;
        this.date = date;
        this.user = user;
        this.hobbyClass = hobbyClass;
    }
}
