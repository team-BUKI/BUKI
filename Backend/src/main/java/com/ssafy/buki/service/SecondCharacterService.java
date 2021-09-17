package com.ssafy.buki.service;

import com.ssafy.buki.domain.bigcategory.BigCategory;
import com.ssafy.buki.domain.bigcategory.BigCategoryRepository;
import com.ssafy.buki.domain.secondcharacter.SecondCharacter;
import com.ssafy.buki.domain.secondcharacter.SecondCharacterRepository;
import com.ssafy.buki.domain.secondcharacter.SecondCharacterResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SecondCharacterService {
    private final SecondCharacterRepository secondCharacterRepository;
    private final BigCategoryRepository bigCategoryRepository;

    public List<SecondCharacterResDto> getMySecondCharacters(Long userId){

        List<SecondCharacter> secondCharacters = secondCharacterRepository.findSecondCharactersByUserId(userId);
        List<SecondCharacterResDto> secondCharacterResDtos = new ArrayList<>();

        for(int i=0; i<secondCharacters.size(); i++){
            SecondCharacter secondCharacter = secondCharacters.get(i);
            BigCategory bigCategory = bigCategoryRepository.findBigCategoryById(secondCharacter.getBigCategory().getId());

            int bigCategoryId = bigCategory.getId();
            String bigCategoryName = bigCategory.getName();
            String image;

            if(secondCharacter.getExp() < 1000){
                image = bigCategory.getLevel1Image();
                System.out.println(bigCategoryName + " 레빌1");
            }else if(secondCharacter.getExp() < 2500){
                image = bigCategory.getLevel2Image();
                System.out.println(bigCategoryName + " 레빌1");
            }else{
                image = bigCategory.getLevel3Image();
                System.out.println(bigCategoryName + " 레빌1");
            }

            SecondCharacterResDto secondCharacterResDto = new SecondCharacterResDto(
                    secondCharacter.getId(),
                    secondCharacter.getExp(),
                    image,
                    bigCategoryId,
                    bigCategoryName,
                    secondCharacter.getRepresent());

            secondCharacterResDtos.add(secondCharacterResDto);
        }

        return secondCharacterResDtos;
    }
}
