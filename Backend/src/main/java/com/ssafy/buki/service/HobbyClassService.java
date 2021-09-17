package com.ssafy.buki.service;

import com.ssafy.buki.domain.bigcategory.BigCategory;
import com.ssafy.buki.domain.bigcategory.BigCategoryResDto;
import com.ssafy.buki.domain.bigcategory.BigCategoryRepository;
import com.ssafy.buki.domain.hobbyclass.ClassResDto;
import com.ssafy.buki.domain.hobbyclass.HobbyClass;
import com.ssafy.buki.domain.hobbyclass.HobbyClassRepository;
import com.ssafy.buki.domain.hobbyclass.HobbyClassReqDto;
import com.ssafy.buki.domain.sido.Sido;
import com.ssafy.buki.domain.sido.SidoRepository;
import com.ssafy.buki.domain.sido.SidoResDto;
import com.ssafy.buki.domain.sigungu.Sigungu;
import com.ssafy.buki.domain.sigungu.SigunguRepository;
import com.ssafy.buki.domain.sigungu.SigunguResDto;
import com.ssafy.buki.domain.smallcategory.SmallCategory;
import com.ssafy.buki.domain.smallcategory.SmallCategoryResDto;
import com.ssafy.buki.domain.smallcategory.SmallCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HobbyClassService {

    @Autowired
    HobbyClassRepository hobbyClassRepository;

    @Autowired
    SidoRepository sidoRepository;

    @Autowired
    SigunguRepository sigunguRepository;

    @Autowired
    BigCategoryRepository bigCategoryRepository;

    @Autowired
    SmallCategoryRepository smallCategoryRepository;

    // 1. Get - 사용자 추천 클래스 가져오기
    // 유저 관심 카테고리 먼저 한 후 진행하기
    public List<ClassResDto> getRecommendClass() {

        return null;
    }

    // 2. Get - 인기 클래스 가져오기
    public List<List<ClassResDto>> getPopularClass() {
        List<BigCategory> bigCategoryList = bigCategoryRepository.findAll();
        List<List<ClassResDto>> popularList = new ArrayList<>();
        List<ClassResDto> temp = new ArrayList<>();
        for (BigCategory bigCategory : bigCategoryList
        ) {

            List<HobbyClass> hobbyClassList = hobbyClassRepository.findTop10ByBigCategoryIdOrderByLikeCntDesc(bigCategory.getId());
//            관심카테고리인지 확인하는 내용은 Interest API 수정 후 가져올 예정
            popularList.add(entityToDto(hobbyClassList));
        }

        for (List<ClassResDto> list: popularList
             ) {
            for (ClassResDto classRes: list
                 ) {
                System.out.println(classRes.toString());
            }
        }

        return popularList;
    }

    // 3. Get - 대분류 카테고리 가져오기
    public List<BigCategoryResDto> getBigCategory() {
        List<BigCategory> bigCategoryData = bigCategoryRepository.findAll();
        System.out.println("들어오나요??");
        List<BigCategoryResDto> bigCategoryList = new ArrayList<>();
        for (BigCategory bigCategory : bigCategoryData
        ) {
            BigCategoryResDto bigCategoryResDto
                    = BigCategoryResDto.builder().id(bigCategory.getId()).name(bigCategory.getName()).build();
            bigCategoryList.add(bigCategoryResDto);
        }
        return bigCategoryList;
    }

    // 4. Get - 세부 카테고리 가져오기
    public List<SmallCategoryResDto> getSmallCategory(Integer bigcategory_id) {
        List<SmallCategory> smallCategoryData = smallCategoryRepository.findByBigCategoryId(bigcategory_id);
        List<SmallCategoryResDto> smallCategoryList = new ArrayList<>();
        for (SmallCategory smallCategory : smallCategoryData
        ) {
            smallCategoryList.add(
                    SmallCategoryResDto.builder().id(smallCategory.getId()).name(smallCategory.getName()).build()
            );
        }
        return smallCategoryList;
    }

    // 5. Get - 카테고리로 검색한 클래스 가져오기
    public List<ClassResDto> getClassSearchByCategory(Long classId, HobbyClassReqDto hobbyClassReqDto) {
        Integer bigCategoryId = hobbyClassReqDto.getBigCategoryId();
        Integer smallCategoryId = hobbyClassReqDto.getSmallCategoryId();
        Integer sigunguId = hobbyClassReqDto.getSigunguId();
        Integer minPrice = hobbyClassReqDto.getMinPrice();
        Integer maxPrice = hobbyClassReqDto.getMaxPrice();
        Integer sidoId = null;
        boolean all = false;
        if(sigunguId.equals(1)) {
            all = true;
            sidoId =  1;
        }
        if(sigunguId.equals(13)) {
            all = true;
            sidoId =  2;
        }

        List<HobbyClass> hobbyClassList;
        if (sigunguId == null && minPrice == null && maxPrice == null) {
            hobbyClassList = hobbyClassRepository.findTop100ByBigCategoryIdAndSmallCategoryIdOrderByLikeCntDesc(
                    bigCategoryId, smallCategoryId);
        } else if (minPrice == null && maxPrice == null) {
            if(all) hobbyClassList = hobbyClassRepository.findTop100ByBigCategoryIdAndSmallCategoryIdAndSidoIdOrderByLikeCntDesc(bigCategoryId, smallCategoryId, sidoId);
            else hobbyClassList = hobbyClassRepository.findTop100ByBigCategoryIdAndSmallCategoryIdAndSigunguIdOrderByLikeCntDesc(bigCategoryId, smallCategoryId, sigunguId);
        } else if (sigunguId == null) {
            hobbyClassList = hobbyClassRepository.findTop100ByBigCategoryIdAndSmallCategoryIdAndPriceBetweenOrderByLikeCntDesc(bigCategoryId,
                    smallCategoryId, minPrice, maxPrice);
        } else {
            if(all) hobbyClassList = hobbyClassRepository.findTop100ByBigCategoryIdAndSmallCategoryIdAndSidoIdAndPriceBetweenOrderByLikeCntDesc(bigCategoryId, smallCategoryId, sigunguId, minPrice, maxPrice);
            else hobbyClassList = hobbyClassRepository.findTop100ByBigCategoryIdAndSmallCategoryIdAndSigunguIdAndPriceBetweenOrderByLikeCntDesc(
                    bigCategoryId, smallCategoryId, sigunguId, minPrice, maxPrice
            );
        }


        return entityToDto(hobbyClassList);
    }


    // 6. Get - 키워드로 검색한 클래스 가져오기
    public List<ClassResDto> getClassSearchByKeyword() {
        return null;
    }

    // 7. 지역(시도) 리스트 가져오기
    public List<SidoResDto> getSidoList() {
        List<Sido> sidoData = sidoRepository.findAll();
        List<SidoResDto> sidoList = new LinkedList<>();
        for (int index = 0; index < sidoData.size() - 1; index++
        ) {
            sidoList.add(new SidoResDto(sidoData.get(index).getId(), sidoData.get(index).getName()));
        }
        int last = sidoData.size() - 1;
        sidoList.add(0, new SidoResDto(sidoData.get(last).getId(), sidoData.get(last).getName()));
        return sidoList;
    }


    // 8. 지역(시군구) 리스트 가져오기
    public List<SigunguResDto> getSiGunGuList(Integer sidoId) {
        List<Sigungu> sigunguData = sigunguRepository.findBySidoId(sidoId);
        List<SigunguResDto> sigunguList = new ArrayList<>();

        for (Sigungu sigungu : sigunguData
        ) {
            sigunguList.add(new SigunguResDto(sigungu.getId(), sigungu.getName()));
        }
        return sigunguList;
    }

    //공통 메소드

    private List<ClassResDto> entityToDto(List<HobbyClass> hobbyClassList) {
        List<ClassResDto> list = new ArrayList<>();

        for (HobbyClass hobbyClass : hobbyClassList
        ) {
            list.add(ClassResDto.builder()
                    .id(hobbyClass.getId())
                    .title(hobbyClass.getTitle())
                    .type(hobbyClass.getType())
                    .site(hobbyClass.getSite())
                    .siteUrl(hobbyClass.getSiteUrl())
                    .price(hobbyClass.getPrice())
                    .likeCnt(hobbyClass.getLikeCnt())
                    .imageUrl(hobbyClass.getImageUrl())
                    .sidoId(hobbyClass.getSido().getId())
                    .sigunguId(hobbyClass.getSigungu().getId())
                    .bigcategoryId(hobbyClass.getBigCategory().getId())
                    .smallcategoryId(hobbyClass.getSmallCategory().getId())
                    .build());
        }

        return list;
    }
}
