package com.ssafy.buki.domain.hobbyclass;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface HobbyClassRepository extends JpaRepository<HobbyClass, Long> {
    List<HobbyClass> findTop10ByBigCategoryIdOrderByLikeCntDesc(Integer bigCategoryId);
    List<HobbyClass> findTop100ByBigCategoryIdAndSmallCategoryIdOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId);
    List<HobbyClass> findTop100ByBigCategoryIdAndSmallCategoryIdAndPriceBetweenOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Integer minPrice, Integer maxPrice);
    List<HobbyClass> findTop100ByBigCategoryIdAndSmallCategoryIdAndSigunguIdOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Integer sigunguId);
    List<HobbyClass> findTop100ByBigCategoryIdAndSmallCategoryIdAndSigunguIdAndPriceBetweenOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId,
                                                                                   Integer sigunguId, Integer minPrice, Integer maxPrice);
    List<HobbyClass> findTop100ByBigCategoryIdAndSmallCategoryIdAndSidoIdOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Integer sidoId);
    List<HobbyClass> findTop100ByBigCategoryIdAndSmallCategoryIdAndSidoIdAndPriceBetweenOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Integer sidoId,
                                                                                                           Integer minPrice, Integer maxPrice);

    HobbyClass findHobbyClassById(Long hobbyclass_id);

}
