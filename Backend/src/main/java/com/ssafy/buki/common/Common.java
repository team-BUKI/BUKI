package com.ssafy.buki.common;

import com.ssafy.buki.domain.user.User;
import com.ssafy.buki.exception.BusinessException;
import com.ssafy.buki.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import static com.ssafy.buki.exception.ErrorCode.INVALID_AUTH_TOKEN;

@Component
@RequiredArgsConstructor
public class Common {

    private final UserService userService;

    public User getUserByToken(Authentication authentication){
        if(authentication == null || !authentication.isAuthenticated()){
            throw new BusinessException(INVALID_AUTH_TOKEN);
        }
        Long userId = Long.parseLong(authentication.getName());
        User user = userService.getUser(userId);
        return user;
    }
}
