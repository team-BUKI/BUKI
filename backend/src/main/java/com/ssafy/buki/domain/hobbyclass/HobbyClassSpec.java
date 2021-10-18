package com.ssafy.buki.domain.hobbyclass;

import com.ssafy.buki.exception.BusinessException;
import com.ssafy.buki.exception.ErrorCode;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class HobbyClassSpec {
    //대분류
    //소분류
    //minPrice~ maxPrice
    //minPrice
    //시군구
    //시도

    public static Specification<HobbyClass> BigCategory(Integer bigCategoryId) {
        return new Specification<HobbyClass>() {
            @Override
            public Predicate toPredicate(Root<HobbyClass> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                if (bigCategoryId == null) throw new BusinessException(ErrorCode.NOT_RIGHT_DATA);

                return builder.equal(root.get("bigCategory"), bigCategoryId);
            }
        };
    }

    public static Specification<HobbyClass> SmallCategory(Integer smallCategoryId) {
        return new Specification<HobbyClass>() {
            @Override
            public Predicate toPredicate(Root<HobbyClass> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                if (smallCategoryId == null) return null;

                return builder.equal(root.get("smallCategory"), smallCategoryId);
            }
        };
    }

    public static Specification<HobbyClass> Sigungu(Integer sigunguId) {
        return new Specification<HobbyClass>() {
            @Override
            public Predicate toPredicate(Root<HobbyClass> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                if (sigunguId == null) return null;
                Integer sidoId = null;
                if (sigunguId.equals(1)) {
                    sidoId = 1;
                }
                if (sigunguId.equals(13)) {
                    sidoId = 2;
                }
                if (sidoId != null) {
                    return builder.equal(root.get("sido"), sidoId);
                }
                return builder.equal(root.get("sigungu"), sigunguId);
            }
        };
    }

    public static Specification<HobbyClass> Price(Integer minPrice, Integer maxPrice) {
        return new Specification<HobbyClass>() {
            @Override
            public Predicate toPredicate(Root<HobbyClass> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                if (minPrice == null && maxPrice == null) return null;
                if (maxPrice == null) throw new BusinessException(ErrorCode.NOT_RIGHT_DATA);
                Integer minTempPrice = minPrice == null ? 0 : minPrice;

                return builder.between(root.get("price"), minTempPrice, maxPrice);
            }
        };
    }

}
