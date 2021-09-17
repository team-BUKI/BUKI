package com.ssafy.buki.service;

import com.ssafy.buki.domain.user.RoleType;
import com.ssafy.buki.domain.user.SocialType;
import com.ssafy.buki.domain.user.User;
import com.ssafy.buki.domain.user.UserRepository;
import com.ssafy.buki.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getId());
        if(userRepository.findOneByEmail(user.getEmail()) != null){
            throw new IllegalArgumentException("이미 존재하는 회원입니다");
        }
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        log.info("Fetching user {}", id);
        return userRepository.findUserById(id);
    }

    public User isUser(String email) {
        if(userRepository.findOneByEmail(email) == null){
            return null;
        }else{
            return userRepository.findOneByEmail(email);
        }
    }

    public String socialLogin(Long id, RoleType roleType) {
            return tokenProvider.createToken(id.toString(), roleType);
    }

    public void updateNickname(Long id, String nickname) {
        userRepository.updateNickname(id, nickname);
    }

    public boolean checkNickname(String nickname) {
        if(userRepository.findUserByNickname(nickname) == null){
            return true;
        }else{
            return false;
        }
    }
}
