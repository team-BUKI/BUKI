package com.ssafy.buki.domain.interestcategory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestCategoryRepository extends JpaRepository<InterestCategory, Long> {
    InterestCategory findSmallBySmallCategoryId(Long category_id);
}
