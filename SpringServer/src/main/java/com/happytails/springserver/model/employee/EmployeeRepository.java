package com.happytails.springserver.model.employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    public List<Employee> findAllByAddressAndAnimalTypes_CatAndAnimalTypes_DogAndOrderPrices_DowalkingAndOrderPrices_DofurloughAndOrderPrices_DodogsitterOrderByRatingAsc(String address, boolean cat, boolean dog, boolean dowalking, boolean dofurlough, boolean dodogsitter);
    public List<Employee> findAllByAddressAndAnimalTypes_CatAndAnimalTypes_DogAndOrderPrices_DowalkingAndOrderPrices_DofurloughAndOrderPrices_DodogsitterOrderByReviewCountAsc(String address, boolean cat, boolean dog, boolean dowalking, boolean dofurlough, boolean dodogsitter);
    public List<Employee> findAllByAddressAndAnimalTypes_CatAndAnimalTypes_DogAndOrderPrices_DowalkingAndOrderPrices_DofurloughAndOrderPrices_DodogsitterOrderByOrderPrices_WalkingDescOrderPrices_FurloughDescOrderPrices_DogsitterDesc(String address, boolean cat, boolean dog, boolean dowalking, boolean dofurlough, boolean dodogsitter);

}