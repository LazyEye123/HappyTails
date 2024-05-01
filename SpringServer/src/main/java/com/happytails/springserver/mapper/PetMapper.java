package com.happytails.springserver.mapper;

import com.happytails.springserver.dto.PetDTO;
import com.happytails.springserver.models.Pet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PetMapper {


    Pet dtoToPet(PetDTO petDTO);

    PetDTO petToDto(Pet pet);
}
