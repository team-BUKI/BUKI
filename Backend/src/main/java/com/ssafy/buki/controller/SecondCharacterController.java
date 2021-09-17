package com.ssafy.buki.controller;

import com.ssafy.buki.common.Common;
import com.ssafy.buki.domain.secondcharacter.SecondCharacterResDto;
import com.ssafy.buki.domain.user.User;
import com.ssafy.buki.service.SecondCharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/second")
@RequiredArgsConstructor
public class SecondCharacterController {
    private final Common common;
    private final SecondCharacterService secondCharacterService;

    @GetMapping("")
    public ResponseEntity<List<SecondCharacterResDto>> getInfo(final Authentication authentication){
        User user = common.getUserByToken(authentication);
        return ResponseEntity.ok(secondCharacterService.getMySecondCharacters(user.getId()));
    }
}
