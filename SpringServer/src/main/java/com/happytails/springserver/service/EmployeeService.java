package com.happytails.springserver.service;

import com.happytails.springserver.dto.EmployeeDTO;
import com.happytails.springserver.filter.EmployeeFilter;
import com.happytails.springserver.mapper.EmployeeMapperImpl;
import com.happytails.springserver.models.*;
import com.happytails.springserver.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AnimalTypesRepository animalTypesRepository;
    private final OrderPricesRepository orderPricesRepository;
    private final RatingRepository ratingRepository;
    private final UsersRepository usersRepository;
    private final EmployeeMapperImpl employeeMapper;

    public Employee save(Employee employee) {
        var e = employeeRepository.save(employee);
        // для отключения защиты закомменть эту строчку
        e.setPassword(new BCryptPasswordEncoder().encode(e.getPassword()));
        e.setAnimalId(e.getId());
        e.setPricesId(e.getId());
        e.setRatingId(e.getId());
        animalTypesRepository.save(AnimalTypes
                .builder()
                .id(e.getId())
                .build());
        orderPricesRepository.save(OrderPrices
                .builder()
                .id(e.getId())
                .build());
        ratingRepository.save(Rating
                .builder()
                .id(e.getId())
                .build());
        // можешь в принципе отключить создание записи в таблице юзеров (ибо пароли там без энкодера из security
        // все равно будут не закодированы)
        usersRepository.save(Users
                .builder()
                .id(e.getId())
                .username(e.getLogin())
                .password(e.getPassword())
                .roles("EMPLOYEE")
                .build());
        return employeeRepository.save(e);
    }

    public Employee rateEmployee(Employee employee, Integer rating) {
        var e = employeeRepository.findById(employee.getId()).get();
        var rate = ratingRepository.findById(employee.getRatingId()).get();
        switch (rating) {
            case 1 -> rate.setOne(rate.getOne() + 1);
            case 2 -> rate.setTwo(rate.getTwo() + 1);
            case 3 -> rate.setThree(rate.getThree() + 1);
            case 4 -> rate.setFour(rate.getFour() + 1);
            case 5 -> rate.setFive(rate.getFive() + 1);
        }
        double middleValue = (rate.getOne() + 2 * rate.getTwo() + 3 * rate.getThree() + 4 * rate.getFour() + 5 * rate.getFive()) / (double) (rate.getOne() + rate.getTwo() + rate.getThree() + rate.getFour() + rate.getFive());
        e.setRating(middleValue);
        ratingRepository.save(rate);
        return employeeRepository.save(e);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<EmployeeDTO> getEmployeesByFilter(EmployeeFilter employeeFilter) {
        Stream<Employee> employees = employeeRepository.findAll().stream();
        var employeesDto = new ArrayList<EmployeeDTO>();
        switch (employeeFilter.getPriority()) {
            case Price -> //                return employeeRepository.findAllByAddressAndAnimalTypes_CatAndAnimalTypes_DogAndOrderPrices_DoWalkingAndOrderPrices_DoFurloughAndOrderPrices_DoDogsitterOrderByOrderPrices_WalkingPriceDescOrderPrices_FurloughPriceDescOrderPrices_DogsitterPriceDesc(employeeFilter.getCity(), employeeFilter.getCat(), employeeFilter.getDog(), employeeFilter.getOrderTypes()[0], employeeFilter.getOrderTypes()[1], employeeFilter.getOrderTypes()[2]);
                    employees = employeeRepository
                            .findAll()
                            .stream()
                            .sorted((o1, o2) -> (int) (orderPricesRepository.findById(o1.getPricesId()).get().getWalkingPrice() - orderPricesRepository.findById(o2.getPricesId()).get().getWalkingPrice()))
                            .sorted((o1, o2) -> (int) (orderPricesRepository.findById(o1.getPricesId()).get().getFurloughPrice() - orderPricesRepository.findById(o2.getPricesId()).get().getFurloughPrice()))
                            .sorted((o1, o2) -> (int) (orderPricesRepository.findById(o1.getPricesId()).get().getDogsitterPrice() - orderPricesRepository.findById(o2.getPricesId()).get().getDogsitterPrice()));
            case Review -> //                return employeeRepository.findAllByAddressAndAnimalTypes_CatAndAnimalTypes_DogAndOrderPrices_DoWalkingAndOrderPrices_DoFurloughAndOrderPrices_DoDogsitterOrderByReviewCountAsc(employeeFilter.getCity(), employeeFilter.getCat(), employeeFilter.getDog(), employeeFilter.getOrderTypes()[0], employeeFilter.getOrderTypes()[1], employeeFilter.getOrderTypes()[2]);
                    employees = employeeRepository
                            .findAll()
                            .stream()
                            .sorted((o1, o2) -> o2.getReviewCount() - o1.getReviewCount());
            case Rating -> //                return employeeRepository.findAllByAddressAndAnimalTypes_CatAndAnimalTypes_DogAndOrderPrices_DoWalkingAndOrderPrices_DoFurloughAndOrderPrices_DoDogsitterOrderByRatingAsc(employeeFilter.getCity(), employeeFilter.getCat(), employeeFilter.getDog(), employeeFilter.getOrderTypes()[0], employeeFilter.getOrderTypes()[1], employeeFilter.getOrderTypes()[2]);
                    employees = employeeRepository
                            .findAll()
                            .stream()
                            .sorted(Comparator.comparing(Employee::getRating).reversed());
        }
        if (employeeFilter.getCat() != null)
            employees = employees.filter(employee -> animalTypesRepository.getById(employee.getAnimalId()).isCat() == employeeFilter.getCat());
        if (employeeFilter.getDog() != null)
            employees = employees.filter(employee -> animalTypesRepository.getById(employee.getAnimalId()).isDog() == employeeFilter.getDog());
        if (employeeFilter.getOrderTypes() != null)
            switch (employeeFilter.getOrderTypes().length) {
                case 1 ->
                        employees = employees.filter(employee -> (orderPricesRepository.getById(employee.getPricesId()).getWalkingPrice() > 0) == employeeFilter.getOrderTypes()[0]);
                case 2 -> employees = employees.filter(employee -> {
                    var orderPrices = orderPricesRepository.getById(employee.getPricesId());
                    return ((orderPrices.getWalkingPrice() > 0) == employeeFilter.getOrderTypes()[0]) && ((orderPrices.getFurloughPrice() > 0) == employeeFilter.getOrderTypes()[1]);
                });
                case 3 -> employees = employees.filter(employee -> {
                    var orderPrices = orderPricesRepository.getById(employee.getPricesId());
                    return ((orderPrices.getWalkingPrice() > 0) == employeeFilter.getOrderTypes()[0]) && ((orderPrices.getFurloughPrice() > 0) == employeeFilter.getOrderTypes()[1]) && ((orderPrices.getDogsitterPrice() > 0) == employeeFilter.getOrderTypes()[2]);
                });
            }
        for (var e : employees.toList()) {
            var employeeDto = employeeMapper.employeeToDto(e);
            var orderPrices = orderPricesRepository.getById(e.getPricesId());
            employeeDto.setPrice(orderPrices.getWalkingPrice());
            employeeDto.setOrderTypes(new Boolean[]{orderPrices.getWalkingPrice() > 0, orderPrices.getFurloughPrice() > 0, orderPrices.getDogsitterPrice() > 0});
            var animalTypes = animalTypesRepository.getById(e.getAnimalId());
            employeeDto.setIsCat(animalTypes.isCat());
            employeeDto.setIsDog(animalTypes.isDog());
            employeesDto.add(employeeDto);
        }
        return employeesDto;
    }

    public void delete(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
