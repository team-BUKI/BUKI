package com.ssafy.buki.domain.secondcharacter;

import com.ssafy.buki.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SecondCharacterRepository extends JpaRepository<SecondCharacter, Long> {
    List<SecondCharacter> findSecondCharactersByUserId(Long userId);

    SecondCharacter findSecondCharacterById(Long id);

    @Transactional
    @Modifying
    @Query("Update SecondCharacter sc SET sc.represent = 1 where sc.id = ?1 and sc.user = ?2")
    void afterRepresent(Long id, User user);

    @Transactional
    @Modifying
    @Query("Update SecondCharacter sc SET sc.represent = 0 where sc.id = ?1 and sc.user = ?2")
    void prevRepresent(Long id, User user);
}
