package com.ssafy.buki.domain.hobbyclass;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface HobbyClassRepository extends JpaRepository<HobbyClass, Long> {
    List<HobbyClass> findTop10ByBigCategoryIdOrderByLikeCntDesc(Integer bigCategoryId);


}
