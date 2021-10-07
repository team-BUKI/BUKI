package com.ssafy.buki.controller;

import com.ssafy.buki.domain.ranking.RankingResDto;
import com.ssafy.buki.domain.secondcharacter.SecondCharacterResDto;
import com.ssafy.buki.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @GetMapping("")
    public ResponseEntity<List<RankingResDto>> getRanking100People(){
        return ResponseEntity.status(200).body(rankingService.getRanking100People());
    }


}
