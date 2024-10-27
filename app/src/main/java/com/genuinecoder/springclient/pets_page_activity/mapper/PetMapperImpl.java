package com.genuinecoder.springclient.pets_page_activity.mapper;

import com.genuinecoder.springclient.pets_page_activity.dto.PetDTO;
import com.genuinecoder.springclient.pets_page_activity.model.PetCard;

import java.util.ArrayList;
import java.util.List;

public class PetMapperImpl  {

    public PetCard dtoToPetCard(PetDTO petDTO) {
        if ( petDTO == null ) {
            return null;
        }

        PetCard.PetCardBuilder petCard = PetCard.builder();

        petCard.id( petDTO.getId() );
        petCard.name( petDTO.getName() );
        petCard.type( petDTO.getType() );
        petCard.gender( petDTO.getGender() );
        petCard.age( petDTO.getAge() );
        petCard.weight( petDTO.getWeight() );
        petCard.breed( petDTO.getBreed() );
        petCard.description( petDTO.getDescription() );
        petCard.photoPath( petDTO.getPhotoPath() );
        petCard.ownerId( petDTO.getOwnerId() );

        return petCard.build();
    }

    public PetDTO petCardToDto(PetCard petCard) {
        if ( petCard == null ) {
            return null;
        }

        PetDTO.PetDTOBuilder petDTO = PetDTO.builder();

        petDTO.id( petCard.getId() );
        petDTO.name( petCard.getName() );
        petDTO.type( petCard.getType() );
        petDTO.gender( petCard.getGender() );
        petDTO.age( petCard.getAge() );
        petDTO.weight( petCard.getWeight() );
        petDTO.breed( petCard.getBreed() );
        petDTO.description( petCard.getDescription() );
        petDTO.photoPath( petCard.getPhotoPath() );
        petDTO.ownerId( petCard.getOwnerId() );

        return petDTO.build();
    }

    public List<PetCard> dtoListToPetList(List<PetDTO> petDTOList)
    {
        List<PetCard> petCardList = new ArrayList<>();
        for(PetDTO dto : petDTOList)
        {
            petCardList.add(dtoToPetCard(dto));
        }
        return petCardList;
    }
}
