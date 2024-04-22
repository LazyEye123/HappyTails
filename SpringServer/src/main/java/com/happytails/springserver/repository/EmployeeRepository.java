package com.happytails.springserver.repository;

import com.happytails.springserver.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    List<Employee> findAllByAddressAndAnimalTypes_CatAndAnimalTypes_DogAndOrderPrices_DoWalkingAndOrderPrices_DoFurloughAndOrderPrices_DoDogsitterOrderByRatingAsc(String address, boolean cat, boolean dog, boolean doWalking, boolean doFurlough, boolean doDogsitter);
//    List<Employee> findAllByAddressAndAnimalTypes_CatAndAnimalTypes_DogAndOrderPrices_DoWalkingAndOrderPrices_DoFurloughAndOrderPrices_DoDogsitterOrderByReviewCountAsc(String address, boolean cat, boolean dog, boolean doWalking, boolean doFurlough, boolean doDogsitter);
//    List<Employee> findAllByAddressAndAnimalTypes_CatAndAnimalTypes_DogAndOrderPrices_DoWalkingAndOrderPrices_DoFurloughAndOrderPrices_DoDogsitterOrderByOrderPrices_WalkingPriceDescOrderPrices_FurloughPriceDescOrderPrices_DogsitterPriceDesc(String address, boolean cat, boolean dog, boolean doWalking, boolean doFurlough, boolean doDogsitter);
}