package com.ssafy.buki.service;

import com.ssafy.buki.common.Common;
import com.ssafy.buki.domain.bigcategory.BigCategory;
import com.ssafy.buki.domain.bigcategory.BigCategoryRepository;
import com.ssafy.buki.domain.hobbyclass.HobbyClassResDto;
import com.ssafy.buki.domain.hobbyclass.HobbyClass;
import com.ssafy.buki.domain.hobbyclass.HobbyClassRepository;
import com.ssafy.buki.domain.hobbyclass.HobbyClassReqDto;
import com.ssafy.buki.domain.interestcategory.InterestCategory;
import com.ssafy.buki.domain.interestregion.InterestRegion;
import com.ssafy.buki.domain.user.User;
import com.ssafy.buki.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.ssafy.buki.exception.ErrorCode.NOT_RIGHT_DATA;

@Service
@RequiredArgsConstructor
public class HobbyClassService {


    private final HobbyClassRepository hobbyClassRepository;

    private final BigCategoryRepository bigCategoryRepository;

    private final Common common;

    private final RedisTemplate redisTemplate ;

    ZSetOperations<String, String> setOperations;


    // 1. Get - 사용자 추천 클래스 가져오기
    // 유저 관심 카테고리 먼저 한 후 진행하기
    public List<HobbyClassResDto> getRecommendClass(User user) {
        List<InterestCategory> interestCategoryList = user.getInterestCategoryList();
        List<InterestRegion> interestRegionList = user.getInterestRegionList();
        List<HobbyClass> hobbyClassAllList = new ArrayList<>();
        for (InterestCategory category : interestCategoryList
        ) {
            int categoryId = category.getSmallCategory().getId();
            //온라인
            hobbyClassAllList.addAll(hobbyClassRepository.findTop5BySmallCategoryIdAndSidoIdOrderByLikeCntDesc(categoryId, 9));
            //오프라인
            if (interestRegionList.size() > 0) {
                List<Integer> list = new ArrayList<>();
                for (InterestRegion interestRegion : interestRegionList
                ) {
                    list.add(interestRegion.getSigungu().getId());
                }
                List<HobbyClass> classList = hobbyClassRepository.findTop5BySmallCategoryIdAndSidoIdNotAndSigunguIdInOrderByLikeCntDesc(categoryId, 9, list);
                hobbyClassAllList.addAll(classList);

            } else {
                hobbyClassAllList.addAll(hobbyClassRepository.findTop5BySmallCategoryIdAndSidoIdNotOrderByLikeCntDesc(categoryId, 9));
            }
        }
        Collections.sort(hobbyClassAllList, new Comparator<HobbyClass>() {
            @Override
            public int compare(HobbyClass o1, HobbyClass o2) {
                return o2.getLikeCnt() - o1.getLikeCnt();
            }
        });
        return common.entityListToDto(hobbyClassAllList, user);
    }

    // 2. Get - 인기 클래스 가져오기
    public List<List<HobbyClassResDto>> getPopularClass(User user) {
        List<BigCategory> bigCategoryList = bigCategoryRepository.findAll();
        List<List<HobbyClassResDto>> popularList = new ArrayList<>();
        List<HobbyClassResDto> temp = new ArrayList<>();
        for (BigCategory bigCategory : bigCategoryList
        ) {

            List<HobbyClass> hobbyClassList = hobbyClassRepository.findTop10ByBigCategoryIdOrderByLikeCntDesc(bigCategory.getId());
            if (user != null) {
                popularList.add(common.entityListToDto(hobbyClassList, user));
            } else {
                popularList.add(common.entityListToDto(hobbyClassList));
            }
        }

        return popularList;
    }


    // 5. Get - 카테고리로 검색한 클래스 가져오기
    public List<HobbyClassResDto> getClassSearchByCategory(User user, int classId, HobbyClassReqDto hobbyClassReqDto) {
        Integer bigCategoryId = hobbyClassReqDto.getBigCategoryId();
        Integer smallCategoryId = hobbyClassReqDto.getSmallCategoryId();
        Integer sigunguId = hobbyClassReqDto.getSigunguId();
        Integer minPrice = hobbyClassReqDto.getMinPrice();
        Integer maxPrice = hobbyClassReqDto.getMaxPrice();
        Integer sidoId = null;
        boolean all = false;
        if (sigunguId.equals(1)) {
            all = true;
            sidoId = 1;
        }
        if (sigunguId.equals(13)) {
            all = true;
            sidoId = 2;
        }

        PageRequest pageRequest = PageRequest.of(classId, 10, Sort.unsorted());

        Page<HobbyClass> hobbyClassList;
        if (sigunguId == null && minPrice == null && maxPrice == null) {
            hobbyClassList = hobbyClassRepository.findByBigCategoryIdAndSmallCategoryIdOrderByLikeCntDesc(
                    bigCategoryId, smallCategoryId, pageRequest);
        } else if (minPrice == null && maxPrice == null) {
            if (all)
                hobbyClassList = hobbyClassRepository.findByBigCategoryIdAndSmallCategoryIdAndSidoIdOrderByLikeCntDesc(bigCategoryId, smallCategoryId, sidoId, pageRequest);
            else
                hobbyClassList = hobbyClassRepository.findByBigCategoryIdAndSmallCategoryIdAndSigunguIdOrderByLikeCntDesc(bigCategoryId, smallCategoryId, sigunguId, pageRequest);
        } else if (sigunguId == null) {
            hobbyClassList = hobbyClassRepository.findByBigCategoryIdAndSmallCategoryIdAndPriceBetweenOrderByLikeCntDesc(bigCategoryId,
                    smallCategoryId, minPrice, maxPrice, pageRequest);
        } else {
            if (all)
                hobbyClassList = hobbyClassRepository.findByBigCategoryIdAndSmallCategoryIdAndSidoIdAndPriceBetweenOrderByLikeCntDesc(bigCategoryId, smallCategoryId, sigunguId, minPrice, maxPrice, pageRequest);
            else
                hobbyClassList = hobbyClassRepository.findByBigCategoryIdAndSmallCategoryIdAndSigunguIdAndPriceBetweenOrderByLikeCntDesc(
                        bigCategoryId, smallCategoryId, sigunguId, minPrice, maxPrice, pageRequest
                );
        }
//        마지막 페이지인지에 대한 값을 주어야하나요?
//        if(hobbyClassList.hasNext()) throw new BusinessException(IS_LAST_PAGE);

        return common.entityPageToDto(hobbyClassList, user);
    }


    // 6. Get - 키워드로 검색한 클래스 가져오기
    public List<HobbyClassResDto> getClassSearchByKeyword(User user, int page, String keyword) {
        if ("".equals(keyword)) throw new BusinessException(NOT_RIGHT_DATA);
        setOperations = redisTemplate.opsForZSet();
        setOperations.add(user.getId().toString(), keyword, (double) System.nanoTime());
        if (setOperations.size(user.getId().toString()) > 5) {
            setOperations.removeRange(user.getId().toString(), -6, -6);
        }
//        Set<String> range = setOperations.range(user.getId().toString(), 0, -1);
//        for (String temp: range
//             ) {
//            System.out.println(temp);
//        };

        Page<HobbyClass> hobbyClassList = hobbyClassRepository.findByTitleContainingOrderByLikeCntDesc(keyword, PageRequest.of(page, 10));

        return common.entityPageToDto(hobbyClassList, user);
    }


    public List<String> getRecentKeyword(User user) {
        Set<String> range = null;
        if(setOperations != null) {
            range = setOperations.range(user.getId().toString(), 0, -1);
        }
        if(range ==  null) return new ArrayList<>();
        return new ArrayList<>(range);
    }
}

//    // 3. Get - 대분류 카테고리 가져오기
//    public List<BigCategoryResDto> getBigCategory() {
//        List<BigCategory> bigCategoryData = bigCategoryRepository.findAll();
//        System.out.println("들어오나요??");
//        List<BigCategoryResDto> bigCategoryList = new ArrayList<>();
//        for (BigCategory bigCategory : bigCategoryData
//        ) {
//            BigCategoryResDto bigCategoryResDto
//                    = BigCategoryResDto.builder().id(bigCategory.getId()).name(bigCategory.getName()).build();
//            bigCategoryList.add(bigCategoryResDto);
//        }
//        return bigCategoryList;
//    }
//
//    // 4. Get - 세부 카테고리 가져오기
//    public List<SmallCategoryResDto> getSmallCategory(Integer bigcategory_id) {
//        List<SmallCategory> smallCategoryData = smallCategoryRepository.findByBigCategoryId(bigcategory_id);
//        List<SmallCategoryResDto> smallCategoryList = new ArrayList<>();
//        for (SmallCategory smallCategory : smallCategoryData
//        ) {
//            smallCategoryList.add(
//                    SmallCategoryResDto.builder().id(smallCategory.getId()).name(smallCategory.getName()).build()
//            );
//        }
//        return smallCategoryList;
//    }
//    // 7. 지역(시도) 리스트 가져오기
//    public List<SidoResDto> getSidoList() {
//        List<Sido> sidoData = sidoRepository.findAll();
//        List<SidoResDto> sidoList = new LinkedList<>();
//        for (int index = 0; index < sidoData.size() - 1; index++
//        ) {
//            sidoList.add(new SidoResDto(sidoData.get(index).getId(), sidoData.get(index).getName()));
//        }
//        int last = sidoData.size() - 1;
//        sidoList.add(0, new SidoResDto(sidoData.get(last).getId(), sidoData.get(last).getName()));
//        return sidoList;
//    }
//
//
//    // 8. 지역(시군구) 리스트 가져오기
//    public List<SigunguResDto> getSiGunGuList(Integer sidoId) {
//        List<Sigungu> sigunguData = sigunguRepository.findBySidoId(sidoId);
//        List<SigunguResDto> sigunguList = new ArrayList<>();
//
//        for (Sigungu sigungu : sigunguData
//        ) {
//            sigunguList.add(new SigunguResDto(sigungu.getId(), sigungu.getName()));
//        }
//        return sigunguList;
//    }