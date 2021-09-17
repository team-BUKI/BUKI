package com.ssafy.buki.domain.secondcharacter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecondCharacterRepository extends JpaRepository<SecondCharacter, Long> {
    List<SecondCharacter> findSecondCharactersByUserId(Long userId);
}
