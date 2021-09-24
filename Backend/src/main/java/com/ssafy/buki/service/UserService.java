package com.ssafy.buki.service;

import com.ssafy.buki.domain.interestcategory.InterestCategory;
import com.ssafy.buki.domain.interestcategory.InterestCategoryRepository;
import com.ssafy.buki.domain.interestregion.InterestRegion;
import com.ssafy.buki.domain.interestregion.InterestRegionRepository;
import com.ssafy.buki.domain.sigungu.SigunguRepository;
import com.ssafy.buki.domain.smallcategory.SmallCategoryRepository;
import com.ssafy.buki.domain.user.*;
import com.ssafy.buki.exception.BusinessException;
import com.ssafy.buki.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ssafy.buki.exception.ErrorCode.ALREADY_HAS_USER;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final InterestCategoryRepository interestCategoryRepository;
    private final SmallCategoryRepository smallCategoryRepository;
    private final InterestRegionRepository interestRegionRepository;
    private final SigunguRepository sigunguRepository;
    private final TokenProvider tokenProvider;

    // 회원 가입
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getId());
        if(userRepository.findOneByEmail(user.getEmail()) != null){
            throw new BusinessException(ALREADY_HAS_USER);
        }
        return userRepository.save(user);
    }

    // 사용자 정보 가져오기
    public User getUser(Long id) {
        log.info("Fetching user {}", id);
        return userRepository.findUserById(id);
    }

    // 회원 존재 여부
    public User isUser(String email) {
        if(userRepository.findOneByEmail(email) == null){
            return null;
        }else{
            return userRepository.findOneByEmail(email);
        }
    }

    // 소셜 로그인
    public String socialLogin(Long id, RoleType roleType) {
        return tokenProvider.createToken(id.toString(), roleType);
    }

    // 닉네임 수정하기
    public void updateNickname(Long id, String nickname) {
        userRepository.updateNickname(id, nickname);
    }

    // 닉네임 중복 여부
    public boolean checkNickname(String nickname) {
        if(userRepository.findUserByNickname(nickname) == null){
            return true;
        }else{
            return false;
        }
    }

    // 대표 부캐 별칭 형용사 저장하기
    public void updateSecondCharacterNicknameAdj(Long id, String adj){
        userRepository.updateSecondCharacterNicknameAdj(id, adj);
    }

    // 회원 삭제
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    // 회원 정보 등록하기
    public void saveUserInfo(User user, String nickname, List<Integer> categoryList, List<Integer> regionList){
        // 닉네임 등록
        userRepository.updateNickname(user.getId(), nickname);

        // 카테고리 등록
        for(int i : categoryList){
            InterestCategory interestCategory = InterestCategory.builder().user(user).smallCategory(smallCategoryRepository.findSmallCategoryById(i)).build();
            interestCategoryRepository.save(interestCategory);
        }

        // 지역 등록
        for(int i : regionList){
            InterestRegion interestRegion = InterestRegion.builder().user(user).sigungu(sigunguRepository.findSigunguById(i)).build();
            interestRegionRepository.save(interestRegion);
        }
    }
}
