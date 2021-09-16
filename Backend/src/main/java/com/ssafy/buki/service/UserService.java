package com.ssafy.buki.service;

import com.ssafy.buki.domain.user.RoleType;
import com.ssafy.buki.domain.user.User;

public interface UserService {
    User saveUser(User user);
    User getUser(Long id);
    User isUser(String email);
    String socialLogin(Long id, RoleType roleType);
}
