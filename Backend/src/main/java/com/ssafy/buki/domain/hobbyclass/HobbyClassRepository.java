package com.ssafy.buki.domain.hobbyclass;

import com.ssafy.buki.domain.interestregion.InterestRegion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HobbyClassRepository extends JpaRepository<HobbyClass, Long> {
    //추천 카테고리 - 온라인
    List<HobbyClass> findTop5BySmallCategoryIdAndSidoIdOrderByLikeCntDesc(Integer smallcategory_id, Integer sido_id);

    //추천 카테고리 - 오프라인
    List<HobbyClass> findTop5BySmallCategoryIdAndSidoIdNotAndSigunguIdInOrderByLikeCntDesc(Integer smallcategory_id, Integer sido_id, List<Integer> regions);

    List<HobbyClass> findTop5BySmallCategoryIdAndSidoIdNotOrderByLikeCntDesc(Integer smallcategory_id, Integer sido_id);

    //인기 카테고리 상위 10개 가져오기
    List<HobbyClass> findTop10ByBigCategoryIdOrderByLikeCntDesc(Integer bigCategoryId);

    //카테고리별 검색하기

    //대분류로만 검색
    Page<HobbyClass> findByBigCategoryIdOrderByLikeCntDesc(Integer bigCategoryId, Pageable pageable);

    //대분류와  min Price ~ max Price 이하
    Page<HobbyClass> findByBigCategoryIdAndPriceBetweenOrderByLikeCntDesc(Integer bigCategoryId, Integer minPrice, Integer maxPrice, Pageable pageable);

    //대분류와 min Price 이상
    Page<HobbyClass> findByBigCategoryIdAndPriceGreaterThanEqualOrderByLikeCntDesc(Integer bigCategoryId, Integer minPrice, Pageable pageable);

    //대분류와 시군구
    Page<HobbyClass> findByBigCategoryIdAndSigunguIdOrderByLikeCntDesc(Integer bigCategoryId, Integer sigunguId, Pageable pageable);

    //대분류와 시도
    Page<HobbyClass> findByBigCategoryIdAndSidoIdOrderByLikeCntDesc(Integer bigCategoryId, Integer sidoId, Pageable pageable);

    //대분류와 시군구  min Price ~ max Price 이하
    Page<HobbyClass> findByBigCategoryIdAndSigunguIdAndPriceBetweenOrderByLikeCntDesc(Integer bigCategoryId, Integer sigunguId, Integer minPrice, Integer maxPrice, Pageable pageable);

    //대분류와 시군구 min Price 이상
    Page<HobbyClass> findByBigCategoryIdAndSigunguIdAndPriceGreaterThanEqualOrderByLikeCntDesc(Integer bigCategoryId, Integer sigunguId, Integer minPrice, Pageable pageable);

    //대분류와 min Price ~ max Price 이하 시도
    Page<HobbyClass> findByBigCategoryIdAndSidoIdAndPriceBetweenOrderByLikeCntDesc(Integer bigCategoryId, Integer sidoId, Integer minPrice, Integer maxPrice, Pageable pageable);

    //대분류와 min Price 이상 시도
    Page<HobbyClass> findByBigCategoryIdAndSidoIdAndPriceGreaterThanEqualOrderByLikeCntDesc(Integer bigCategoryId, Integer sidoId, Integer minPrice, Pageable pageable);

    //대분류와 소분류로만 검색
    Page<HobbyClass> findByBigCategoryIdAndSmallCategoryIdOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Pageable pageable);

    //대분류소분류 min Price ~ max Price 이하
    Page<HobbyClass> findByBigCategoryIdAndSmallCategoryIdAndPriceBetweenOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Integer minPrice, Integer maxPrice, Pageable pageable);

    //대분류소분류 min Price 이상
    Page<HobbyClass> findByBigCategoryIdAndSmallCategoryIdAndPriceGreaterThanEqualOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Integer minPrice, Pageable pageable);

    //대분류소분류시군구
    Page<HobbyClass> findByBigCategoryIdAndSmallCategoryIdAndSigunguIdOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Integer sigunguId, Pageable pageable);

    //대분류소분류시군구  min Price ~ max Price 이하
    Page<HobbyClass> findByBigCategoryIdAndSmallCategoryIdAndSigunguIdAndPriceBetweenOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Integer sigunguId, Integer minPrice, Integer maxPrice, Pageable pageable);

    //대분류소분류시군구 min Price 이상
    Page<HobbyClass> findByBigCategoryIdAndSmallCategoryIdAndSigunguIdAndPriceGreaterThanEqualOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Integer sigunguId, Integer minPrice, Pageable pageable);

    //대분류소분류시도
    Page<HobbyClass> findByBigCategoryIdAndSmallCategoryIdAndSidoIdOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Integer sidoId, Pageable pageable);

    //대분류소분류시도 min Price ~ max Price 이하
    Page<HobbyClass> findByBigCategoryIdAndSmallCategoryIdAndSidoIdAndPriceBetweenOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Integer sidoId,
                                                                                                     Integer minPrice, Integer maxPrice, Pageable pageable);

    //대분류소분류시도 min Price 이상
    Page<HobbyClass> findByBigCategoryIdAndSmallCategoryIdAndSidoIdAndPriceGreaterThanEqualOrderByLikeCntDesc(Integer bigCategoryId, Integer smallCategoryId, Integer sidoId,
                                                                                                     Integer minPrice, Pageable pageable);

    //키워드검색
    Page<HobbyClass> findByTitleContainingOrderByLikeCntDesc(String keyword, Pageable pageable);

    HobbyClass findHobbyClassById(Long hobbyclass_id);

}
