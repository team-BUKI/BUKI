package com.ssafy.buki.domain.hobbyclass;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HobbyClassRepository extends JpaRepository<HobbyClass, Long> {
    //인기 카테고리 상위 10개 가져오기
    List<HobbyClass> findTop10ByBigCategoryIdOrderByLikeCntDesc(Integer bigCategoryId);

    //카테고리별 검색하기
    //대분류와 소분류로만 검색
    Page<HobbyClass> findByBigCategoryIdAndSmallCategoryIdOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Pageable pageable);
    //대분류소분류가격
    Page<HobbyClass> findByBigCategoryIdAndSmallCategoryIdAndPriceBetweenOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Integer minPrice, Integer maxPrice, Pageable pageable);
    //대분류소분류시군구
    Page<HobbyClass> findByBigCategoryIdAndSmallCategoryIdAndSigunguIdOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Integer sigunguId, Pageable pageable);

    //대분류소분류시군구가격
    Page<HobbyClass> findByBigCategoryIdAndSmallCategoryIdAndSigunguIdAndPriceBetweenOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId,
                                                                                   Integer sigunguId, Integer minPrice, Integer maxPrice, Pageable pageable);
    //대분류소분류시도
    Page<HobbyClass> findByBigCategoryIdAndSmallCategoryIdAndSidoIdOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Integer sidoId, Pageable pageable);

    //대분류소분류시도가격
    Page<HobbyClass> findByBigCategoryIdAndSmallCategoryIdAndSidoIdAndPriceBetweenOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Integer sidoId,
                                                                                                           Integer minPrice, Integer maxPrice, Pageable pageable);
    //키워드검색
    Page<HobbyClass> findByTitleContainingOrderByLikeCntDesc(String keyword, Pageable pageable);
    HobbyClass findHobbyClassById(Long hobbyclass_id);

}
