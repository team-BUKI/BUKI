package com.ssafy.buki.controller;

import com.ssafy.buki.domain.user.*;
import com.ssafy.buki.token.TokenAuthenticationFilter;
import com.ssafy.buki.token.TokenProvider;
import com.ssafy.buki.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;

    public UserController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserService userService){
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResDto> login(@Valid @RequestBody LoginReqDto loginReqDto){
        User user = userService.isUser(loginReqDto.getEmail());
        String jwt = null;
        boolean first = false;

        if(user == null){ //회원가입
            first = true;
            User newUser = new User(loginReqDto.getEmail(), loginReqDto.getSocialType(), RoleType.USER);
            user = userService.saveUser(newUser);

        }
        jwt = userService.socialLogin(user.getId(), RoleType.USER);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(TokenAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new LoginResDto(user.getId(), jwt, user.getNickname(), first), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<InfoResDto> getInfo(final Authentication authentication){
        if(authentication == null || !authentication.isAuthenticated()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Long userId = Long.parseLong(authentication.getName());
        User user = userService.getUser(userId);
        return new ResponseEntity<>(new InfoResDto(user.getEmail(), user.getNickname(), user.getSecondcharacterNickname()), HttpStatus.OK);
    }

}
