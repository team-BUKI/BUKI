package com.ssafy.buki.domain.interesthobbyclass;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestHobbyClassRepository extends JpaRepository<InterestHobbyClass, Long> {
    InterestHobbyClass findInterestHobbyClassByHobbyClassIdAndUserId(Long hobbyclass_id,  Long user_id);
    Page<InterestHobbyClass> findInterestHobbyClassByUserIdOrderByIdDesc(Long user_id, Pageable pageable);
    void deleteInterestHobbyClassByHobbyClassIdAndUserId(Long hobbyclass_id, Long user_id);
}
