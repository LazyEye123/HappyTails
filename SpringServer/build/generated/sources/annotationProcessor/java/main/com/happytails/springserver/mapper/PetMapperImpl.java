package com.happytails.springserver.mapper;

import com.happytails.springserver.dto.PetDTO;
import com.happytails.springserver.models.Pet;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-20T20:15:09+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 18.0.2 (Oracle Corporation)"
)
@Component
public class PetMapperImpl implements PetMapper {

    @Override
    public Pet dtoToPet(PetDTO petDTO) {
        if ( petDTO == null ) {
            return null;
        }

        Pet.PetBuilder pet = Pet.builder();

        pet.id( petDTO.getId() );
        pet.name( petDTO.getName() );
        pet.type( petDTO.getType() );
        pet.gender( petDTO.getGender() );
        pet.age( petDTO.getAge() );
        pet.weight( petDTO.getWeight() );
        pet.breed( petDTO.getBreed() );
        pet.description( petDTO.getDescription() );
        pet.photoPath( petDTO.getPhotoPath() );
        pet.ownerId( petDTO.getOwnerId() );

        return pet.build();
    }

    @Override
    public PetDTO petToDto(Pet pet) {
        if ( pet == null ) {
            return null;
        }

        PetDTO.PetDTOBuilder petDTO = PetDTO.builder();

        petDTO.id( pet.getId() );
        petDTO.name( pet.getName() );
        petDTO.type( pet.getType() );
        petDTO.gender( pet.getGender() );
        petDTO.age( pet.getAge() );
        petDTO.weight( pet.getWeight() );
        petDTO.breed( pet.getBreed() );
        petDTO.description( pet.getDescription() );
        petDTO.photoPath( pet.getPhotoPath() );
        petDTO.ownerId( pet.getOwnerId() );

        return petDTO.build();
    }
}
