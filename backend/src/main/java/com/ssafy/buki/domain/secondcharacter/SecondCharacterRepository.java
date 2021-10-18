package com.ssafy.buki.domain.secondcharacter;

import com.ssafy.buki.domain.bigcategory.BigCategory;
import com.ssafy.buki.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Transactional
@Repository
public interface SecondCharacterRepository extends JpaRepository<SecondCharacter, Long> {
    List<SecondCharacter> findSecondCharactersByUserId(Long userId);
    SecondCharacter findSecondCharacterById(Long id);
    SecondCharacter findSecondCharacterByUserIdAndBigCategoryId(Long userId, Integer bigCategoryId);
    SecondCharacter findSecondCharacterByUserIdAndRepresentIsTrue(Long userId);

    Long countSecondCharacterByUserId(Long userId);

    @Query("SELECT sum(sc.exp) FROM SecondCharacter sc where sc.user.id = :user_id")
    Long totalExpByUser(@Param("user_id") Long user_id);

    @Query("select distinct sc2.date from SecondCharacter sc1, SecondCharacter sc2 where sc1.user.id = :user_id and sc1.date < sc2.date")
    LocalDateTime recentGetExpDay(@Param("user_id") Long user_id);

    @Modifying
    @Query("Update SecondCharacter sc SET sc.represent = 1 where sc.id = ?1 and sc.user = ?2")
    void afterRepresent(Long id, User user);

    @Modifying
    @Query("Update SecondCharacter sc SET sc.represent = 0 where sc.id = ?1 and sc.user = ?2")
    void prevRepresent(Long id, User user);

    @Modifying
    @Query("Update SecondCharacter sc SET sc.exp = sc.exp + 100, sc.date = current_time where sc.user = ?1 and sc.bigCategory = ?2")
    void plusExp(User user, BigCategory bigCategory);

    @Modifying
    @Query("Update SecondCharacter sc SET sc.exp = sc.exp + 150, sc.bonusDate = current_date  where sc.user = ?1 and sc.bigCategory = ?2")
    void plusBonusExp(User user, BigCategory bigCategory);

    @Query("select distinct sc.user from SecondCharacter sc")
    List<User> writeDiaryPeople();

}
