package com.ssafy.buki.service;

import com.ssafy.buki.common.Common;
import com.ssafy.buki.domain.bigcategory.BigCategory;
import com.ssafy.buki.domain.bigcategory.BigCategoryRepository;
import com.ssafy.buki.domain.secondcharacter.SecondCharacter;
import com.ssafy.buki.domain.secondcharacter.SecondCharacterRepository;
import com.ssafy.buki.domain.secondcharacter.SecondCharacterResDto;
import com.ssafy.buki.domain.user.User;
import com.ssafy.buki.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SecondCharacterService {
    private final SecondCharacterRepository secondCharacterRepository;
    private final BigCategoryRepository bigCategoryRepository;
    private final UserRepository userRepository;
    private final Common common;

    public List<SecondCharacterResDto> getMySecondCharacters(Long userId){

        List<SecondCharacter> secondCharacters = secondCharacterRepository.findSecondCharactersByUserId(userId);
        List<SecondCharacterResDto> secondCharacterResDtos = new ArrayList<>();

        for(int i=0; i<secondCharacters.size(); i++){
            SecondCharacter secondCharacter = secondCharacters.get(i);
            BigCategory bigCategory = bigCategoryRepository.findBigCategoryById(secondCharacter.getBigCategory().getId());

            int bigCategoryId = bigCategory.getId();
            String bigCategoryName = bigCategory.getName();

            int level = common.getSecondCharacterLevel(secondCharacter);

            String image;

            if(level == 1){
                image = bigCategory.getLevel1Image();
            }else if(level == 2){
                image = bigCategory.getLevel2Image();
            }else{
                image = bigCategory.getLevel3Image();
            }

            SecondCharacterResDto secondCharacterResDto = new SecondCharacterResDto(
                    secondCharacter.getId(),
                    secondCharacter.getExp(),
                    level,
                    image,
                    bigCategoryId,
                    bigCategoryName,
                    secondCharacter.getRepresent());

            secondCharacterResDtos.add(secondCharacterResDto);
        }

        return secondCharacterResDtos;
    }

    public void setRepresentCharacter(Long prevId, Long afterId, User user){
        secondCharacterRepository.prevRepresent(prevId, user);
        secondCharacterRepository.afterRepresent(afterId, user);

        Integer bigCategoryId = secondCharacterRepository.findSecondCharacterById(afterId).getBigCategory().getId();

        String noun = bigCategoryRepository.getById(bigCategoryId).getNicknameNoun();

        userRepository.updateSecondCharacterNicknameNoun(user.getId(), noun);
    }
}
