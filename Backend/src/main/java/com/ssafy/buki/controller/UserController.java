package com.ssafy.buki.controller;

import com.ssafy.buki.common.Common;
import com.ssafy.buki.domain.user.*;
import com.ssafy.buki.exception.BusinessException;
import com.ssafy.buki.service.UserService;
import com.ssafy.buki.token.TokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ssafy.buki.exception.ErrorCode.ALREADY_HAS_NICKNAME;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final Common common;

    // 1. 로그인 & 회원가입
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

    // 2. 사용자 정보 가져오기
    @GetMapping("/info")
    public ResponseEntity<InfoResDto> getInfo(final Authentication authentication){
        User user = common.getUserByToken(authentication);
        return new ResponseEntity<>(new InfoResDto(user.getEmail(), user.getNickname(), user.getSecondcharacterNickname()), HttpStatus.OK);
    }

    // 3. 닉네임 수정하기
    @PutMapping("/nickname")
    public ResponseEntity updateNickname(final Authentication authentication, @RequestBody NicknameReqDto nicknameReqDto){
        User user = common.getUserByToken(authentication);
        userService.updateNickname(user.getId(), nicknameReqDto.getNickname());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 4. 닉네임 중복 확인하기
    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<String> checkNickname(@PathVariable String nickname){
        if(userService.checkNickname(nickname)){
            return ResponseEntity.ok("사용 가능한 닉네임");
        }else{
            throw new BusinessException(ALREADY_HAS_NICKNAME);
        }
    }

    // 5. 회원 탈퇴
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(final Authentication authentication){
        User user = common.getUserByToken(authentication);
        userService.deleteUser(user.getId());
        return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
    }
}
