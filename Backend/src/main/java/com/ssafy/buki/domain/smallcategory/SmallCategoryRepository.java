package com.ssafy.buki.domain.smallcategory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmallCategoryRepository extends JpaRepository<SmallCategory, Integer> {
    List<SmallCategory> findByBigCategoryId(Integer bigcategory_id);
}
