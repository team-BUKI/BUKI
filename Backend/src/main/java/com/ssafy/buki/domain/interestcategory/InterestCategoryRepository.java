package com.ssafy.buki.domain.interestcategory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestCategoryRepository extends JpaRepository<InterestCategory, Long> {
    List<InterestCategory> findInterestCategoriesByUserId(Long user_id);
    void deleteInterestCategoriesByUserId(Long user_id);
}
