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
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

import java.util.List;

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

        Boolean first = true;

        if(user == null){ //회원가입
            User newUser = new User(loginReqDto.getEmail(), loginReqDto.getSocialType(), RoleType.USER);
            user = userService.saveUser(newUser);

        }else{
            if(user.getNickname() != null){
                first = false;
            }
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
        return new ResponseEntity<>(new InfoResDto(user.getEmail(), user.getNickname(), user.getSecondcharacterNicknameAdj(), user.getSecondcharacterNicknameNoun()), HttpStatus.OK);
    }

    // 3. 닉네임 수정하기
    @PutMapping("/nickname/{nickname}")
    public ResponseEntity updateNickname(final Authentication authentication, @PathVariable String nickname){
        User user = common.getUserByToken(authentication);
        userService.updateNickname(user.getId(), nickname);
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

    // 6. 대표 별칭 형용사 저장하기
    @PutMapping("/adj/{adj}")
    public ResponseEntity updateSecondNicknameAdj(final Authentication authentication, @PathVariable String adj){
        User user = common.getUserByToken(authentication);
        userService.updateSecondCharacterNicknameAdj(user.getId(), adj);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 7. 대표 별칭 형용사 가져오기
    @GetMapping("/adj")
    public ResponseEntity<String> getSecondNicknameAdj(final Authentication authentication){
        User user = common.getUserByToken(authentication);
        String adj = user.getSecondcharacterNicknameAdj();
        return ResponseEntity.status(HttpStatus.OK).body(adj);
    }

    // 8. 회원 정보 등록하기 (닉네임 + 관심 지역 + 관심 카테고리)
    @PostMapping("/info")
    public ResponseEntity saveUserInfo(@ApiIgnore final Authentication authentication, @RequestBody InfoReqDto infoReqDto){
        User user = common.getUserByToken(authentication);
        userService.saveUserInfo(user, infoReqDto.getNickname(), infoReqDto.getCategory(), infoReqDto.getRegion());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
