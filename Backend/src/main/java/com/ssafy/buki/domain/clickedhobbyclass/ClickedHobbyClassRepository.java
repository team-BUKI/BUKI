package com.ssafy.buki.domain.clickedhobbyclass;

import com.ssafy.buki.domain.diary.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClickedHobbyClassRepository extends JpaRepository<ClickedHobbyClass, Long> {
    ClickedHobbyClass findClickedHobbyClassByUserIdAndHobbyClassId(Long user_Id, Long class_Id);

}
