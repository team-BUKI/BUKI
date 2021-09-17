package com.ssafy.buki.controller;

import com.ssafy.buki.common.Common;
import com.ssafy.buki.domain.secondcharacter.SecondCharacterReqDto;
import com.ssafy.buki.domain.secondcharacter.SecondCharacterResDto;
import com.ssafy.buki.domain.user.User;
import com.ssafy.buki.service.SecondCharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/second")
@RequiredArgsConstructor
public class SecondCharacterController {
    private final Common common;
    private final SecondCharacterService secondCharacterService;

    // 1. 나의 부캐 확인하기
    @GetMapping("")
    public ResponseEntity<List<SecondCharacterResDto>> getMySecondCharacters(final Authentication authentication){
        User user = common.getUserByToken(authentication);
        return ResponseEntity.ok(secondCharacterService.getMySecondCharacters(user.getId()));
    }

    // 2. 대표 부캐 선택하기
    @PutMapping("")
    public ResponseEntity setRepresentCharacter(final Authentication authentication, @RequestBody SecondCharacterReqDto secondCharacterReqDto){
        User user = common.getUserByToken(authentication);
        secondCharacterService.setRepresentCharacter(secondCharacterReqDto.getPrevId(), secondCharacterReqDto.getAfterId(), user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
