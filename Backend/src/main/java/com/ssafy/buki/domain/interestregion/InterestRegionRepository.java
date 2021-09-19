package com.ssafy.buki.domain.interestregion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestRegionRepository extends JpaRepository<InterestRegion, Long> {
    List<InterestRegion> findInterestRegionsByUserId(Long user_id);
    boolean deleteInterestRegionByUserId(Long user_id);
}
