package com.ssafy.buki.domain.bigcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BigCategoryRepository extends JpaRepository<BigCategory, Integer> {

    BigCategory findBigCategoryById(Integer id);
}
