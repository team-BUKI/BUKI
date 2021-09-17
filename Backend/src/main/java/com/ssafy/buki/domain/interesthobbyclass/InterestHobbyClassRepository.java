package com.ssafy.buki.domain.interesthobbyclass;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestHobbyClassRepository extends JpaRepository<InterestHobbyClass, Long> {
    InterestHobbyClass findInterestHobbyClassByHobbyClassIdAndUserId(Long hobbyclass_id,  Long user_id);
    List<InterestHobbyClass> findInterestHobbyClassByUserId(Long user_id);
    void deleteInterestHobbyClassByHobbyClassIdAndUserId(Long hobbyclass_id, Long user_id);
}
