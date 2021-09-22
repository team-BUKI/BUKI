package com.ssafy.buki.domain.secondcharacter;

import com.ssafy.buki.domain.bigcategory.BigCategory;
import com.ssafy.buki.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Repository
public interface SecondCharacterRepository extends JpaRepository<SecondCharacter, Long> {
    List<SecondCharacter> findSecondCharactersByUserId(Long userId);
    SecondCharacter findSecondCharacterById(Long id);
    SecondCharacter findSecondCharacterByUserIdAndBigCategoryId(Long userId, Integer bigCategoryId);

    Long countSecondCharacterByUserId(Long userId);


    @Modifying
    @Query("Update SecondCharacter sc SET sc.represent = 1 where sc.id = ?1 and sc.user = ?2")
    void afterRepresent(Long id, User user);

    @Modifying
    @Query("Update SecondCharacter sc SET sc.represent = 0 where sc.id = ?1 and sc.user = ?2")
    void prevRepresent(Long id, User user);

    @Modifying
    @Query("Update SecondCharacter sc SET sc.exp = sc.exp + 100, sc.date = current_date where sc.user = ?1 and sc.bigCategory = ?2")
    void plusExp(User user, BigCategory bigCategory);
}
