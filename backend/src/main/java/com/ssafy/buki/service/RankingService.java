package com.ssafy.buki.service;

import com.ssafy.buki.common.Common;
import com.ssafy.buki.domain.ranking.RankingResDto;
import com.ssafy.buki.domain.secondcharacter.SecondCharacter;
import com.ssafy.buki.domain.secondcharacter.SecondCharacterRepository;
import com.ssafy.buki.domain.secondcharacter.SecondCharacterResDto;
import com.ssafy.buki.domain.user.User;
import com.ssafy.buki.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final UserRepository userRepository;
    private final SecondCharacterRepository secondCharacterRepository;
    private final RedisTemplate redisTemplate;
    private final Common common;

    protected static ZSetOperations<String, String> setOperations;


    public void init() {
        setOperations = redisTemplate.opsForZSet();
    }

    public List<RankingResDto> getRanking100People() {

//        if (setOperations == null) {
//            setAllExpData();
//        }
        List<User> userList = secondCharacterRepository.writeDiaryPeople();
        List<RankingResDto> list = new ArrayList<>();
        if (setOperations == null) init();
        if (setOperations.zCard("ranking") <= userList.size()) setAllExpData(userList);
        Set<ZSetOperations.TypedTuple<String>> ranking = setOperations.reverseRangeWithScores("ranking", 0, 100);

        for (ZSetOperations.TypedTuple<String> data : ranking
        ) {
            User user = userRepository.findUserById(Long.parseLong(data.getValue()));
            if(user == null){
                setOperations.remove("ranking", data.getValue());
                continue;
            }
            SecondCharacter secondCharacter = secondCharacterRepository.findSecondCharacterByUserIdAndRepresentIsTrue(user.getId());

            RankingResDto rankingResDto = RankingResDto.builder()
                    .id(user.getId())
                    .username(user.getNickname())
                    .bigcategoryId(secondCharacter.getBigCategory().getId())
                    .imageUrl(common.getSecondCharacterLevelImage(secondCharacter))
                    .totalExp(data.getScore().longValue())
                    .secondcharacterNicknameAdj(user.getSecondcharacterNicknameAdj())
                    .secondcharacterNicknameNoun(user.getSecondcharacterNicknameNoun())
                    .build();

            list.add(rankingResDto);

        }

        return list;
    }

    public void setAllExpData( List<User> userList) {
//        setOperations = redisTemplate.opsForZSet();

//        DateTime dateTime = new DateTime("2021-12-31 23:59:999999");
//        System.out.println(dateTime.millisOfSecond());
//        dateTime.millisOfSecond().toString();

        for (User user : userList
        ) {
            final Long exp = secondCharacterRepository.totalExpByUser(user.getId());
            if (exp != null) {
                setOperations.add("ranking", user.getId().toString(), exp);
            }
        }
    }
}

