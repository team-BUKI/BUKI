package com.ssafy.buki.domain.diary;

import com.ssafy.buki.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    Diary findDiaryById(Long id);

    @Modifying
    @Query("Update Diary d SET d.content = ?1, d.image = ?2, d.share = ?3 where d.id = ?4")
    void updateDiary(String content, String image, Boolean share, Long id);

    void deleteDiaryById(Long id);
}
