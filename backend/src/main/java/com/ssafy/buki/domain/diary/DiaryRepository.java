package com.ssafy.buki.domain.diary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    // 일기 id로 가져오기
    Diary findDiaryById(Long id);

    // 선택한 날짜 일기 가져오기
    @Query("Select d from Diary d where d.user.id = ?2 and d.date in ?1 order by d.id desc")
    List<Diary> getDailyDiary(LocalDate date, Long userId);

    // 선택한 월 일기 가져오기
    @Query("Select d from Diary d where d.date between ?1 and ?2 and d.user.id = ?3")
    List<Diary> getMonthlyDiary(LocalDate start, LocalDate end, Long userId);

    // 일기 삭제하기
    void deleteDiaryById(Long id);

    // 일기 수정하기
    @Modifying
    @Query("Update Diary d SET d.content = ?1, d.image = ?2, d.share = ?3 where d.id = ?4")
    void updateDiary(String content, String image, Boolean share, Long id);

    // 전체 일기 가져오기(public)
    Page<Diary> findByUserIdAndShareTrueOrderByIdDesc(Long userId, Pageable pageable);

    // 전체 일기 가져오기(private 포함)
    Page<Diary> findByUserIdOrderByIdDesc(Long userId, Pageable pageable);

    // 사용자+대분류ID로 일기 가져오기
    @Query("Select d from Diary d where d.user.id = ?1 and d.bigCategory.id = ?2 and d.date between ?3 and ?4")
    List<Diary> getContinuousDiary(Long userId, Integer bigCategoryId, LocalDate start, LocalDate end);
}
