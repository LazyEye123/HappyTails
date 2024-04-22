package com.happytails.springserver.repository;

import com.happytails.springserver.models.AnimalTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalTypesRepository extends JpaRepository<AnimalTypes, Long> {
}
