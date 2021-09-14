package com.ssafy.buki.service;

import com.ssafy.buki.domain.bigcategory.BigCategory;
import com.ssafy.buki.domain.bigcategory.BigCategoryResDto;
import com.ssafy.buki.domain.bigcategory.BigCategoryRepository;
import com.ssafy.buki.domain.hobbyclass.ClassResDto;
import com.ssafy.buki.domain.hobbyclass.HobbyClass;
import com.ssafy.buki.domain.hobbyclass.HobbyClassRepository;
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
    public List<ClassResDto> getRecommendClass(Long id){
        return null;
    }
    // 2. Get - 인기 클래스 가져오기
    public Map<String, List<ClassResDto>> getPopularClass(){
        List<BigCategory> bigCategoryList = bigCategoryRepository.findAll();
        Map<String, List<ClassResDto>> popularList = new HashMap<>();
        for (BigCategory bigCategory: bigCategoryList
             ) {

            List<HobbyClass> hobbyClassList = hobbyClassRepository.findTop10BySmallCategoryIdOrderByLikeCnt(bigCategory.getId());
            List<ClassResDto> list = new ArrayList<>();

            for (HobbyClass hobbyClass: hobbyClassList
                 ) {
                list.add(ClassResDto.builder()
                        .id(hobbyClass.getId())
                        .price(hobbyClass.getPrice())
                        .site(hobbyClass.getSite())
                        .title(hobbyClass.getTitle())
                        .imageUrl(hobbyClass.getImageUrl())
                        .likeCnt(hobbyClass.getLikeCnt())
                        .siteUrl(hobbyClass.getSiteUrl())
                        .build());
            }
            popularList.put(bigCategory.getName(), list);
        }
        return popularList;
    }
    // 3. Get - 대분류 카테고리 가져오기
    public List<BigCategoryResDto> getBigCategory() {
        List<BigCategory> bigCategoryData = bigCategoryRepository.findAll();

        List<BigCategoryResDto> bigCategoryList = new ArrayList<>();
        for (BigCategory bigCategory : bigCategoryData
        ) {
            BigCategoryResDto bigCategoryResDto
                    = BigCategoryResDto.builder().id(bigCategory.getId()).name(bigCategory.getName()).build();
            System.out.println(bigCategory.getId() + " " + bigCategory.getName());
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
    public List<ClassResDto> getClassSearchByCategory(){
        return null;
    }
    // 6. Get - 키워드로 검색한 클래스 가져오기
    public List<ClassResDto> getClassSearchByKeyword(){
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
}
