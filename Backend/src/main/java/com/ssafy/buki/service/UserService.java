package com.ssafy.buki.service;

import com.ssafy.buki.domain.user.RoleType;
import com.ssafy.buki.domain.user.SocialType;
import com.ssafy.buki.domain.user.User;
import com.ssafy.buki.domain.user.UserRepository;
import com.ssafy.buki.exception.BusinessException;
import com.ssafy.buki.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.buki.exception.ErrorCode.ALREADY_HAS_USER;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;
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
}
