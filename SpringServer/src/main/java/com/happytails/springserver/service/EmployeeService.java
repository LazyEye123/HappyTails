package com.happytails.springserver.service;

import com.happytails.springserver.dto.EmployeeDTO;
import com.happytails.springserver.filter.EmployeeFilter;
import com.happytails.springserver.mapper.EmployeeMapperImpl;
import com.happytails.springserver.models.*;
import com.happytails.springserver.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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

    public EmployeeDTO save(EmployeeDTO employeeDto) {
        var employee = employeeMapper.dtoToEmployee(employeeDto);
        var e = employeeRepository.save(employee);
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
        var u = usersRepository.save(Users
                .builder()
                .username(employeeDto.getLogin())
                .password(new BCryptPasswordEncoder().encode(employeeDto.getPassword()))
                .roles("EMPLOYEE")
                .build());
        e.setUsersId(u.getId());
        return employeeMapper.employeeToDto(employeeRepository.save(e));
    }

    public EmployeeDTO uploadEmployeePhoto(MultipartFile photo, Long employeeId) throws IOException {
        var employee = employeeRepository.findById(employeeId).get();
        if (!photo.isEmpty()) {
            StringBuilder saveLocation = new StringBuilder(System.getProperty("user.dir"));
            saveLocation.append("/src/main/resources/uploaded/images/employees");
            var file = new File(saveLocation.toString());
            if (!file.exists()) {
                file.mkdir();
            }
            saveLocation.append("/");
            saveLocation.append(employeeId);
            saveLocation.append(".");
            saveLocation.append(photo.getOriginalFilename().split("[.]")[1]);
            try {
                photo.transferTo(new File(saveLocation.toString()));
            } catch (IOException e) {
                throw new IOException(e);
            }
            employee.setPhotoPath(saveLocation.toString());
            return employeeMapper.employeeToDto(employeeRepository.save(employee));
        }
        return employeeMapper.employeeToDto(employee);
    }

    public EmployeeDTO rateEmployee(EmployeeDTO employeeDTO) {
        var e = employeeRepository.findById(employeeDTO.getId()).get();
        var rate = ratingRepository.findById(e.getRatingId()).get();
        switch (employeeDTO.getRatingValue()) {
            case 1 -> rate.setOne(rate.getOne() + 1);
            case 2 -> rate.setTwo(rate.getTwo() + 1);
            case 3 -> rate.setThree(rate.getThree() + 1);
            case 4 -> rate.setFour(rate.getFour() + 1);
            case 5 -> rate.setFive(rate.getFive() + 1);
        }
        double middleValue = (rate.getOne() + 2 * rate.getTwo() + 3 * rate.getThree() + 4 * rate.getFour() + 5 * rate.getFive()) / (double) (rate.getOne() + rate.getTwo() + rate.getThree() + rate.getFour() + rate.getFive());
        e.setRating(middleValue);
        ratingRepository.save(rate);
        return employeeMapper.employeeToDto(employeeRepository.save(e));
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository
                .findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeesByFilter(EmployeeFilter employeeFilter) {
        Stream<Employee> employees = employeeRepository.findAll().stream();
        if(!employeeFilter.getCity().isBlank())
            employees = employees.filter(employee -> employee.getAddress().split(",")[0].equals(employeeFilter.getCity()));
        if (employeeFilter.getCat())
            employees = employees.filter(employee -> employee.getAnimalTypes().isCat());
        if (employeeFilter.getDog())
            employees = employees.filter(employee -> employee.getAnimalTypes().isDog());
        for (int i = 0; i < 3; i++)
            if (employeeFilter.getOrderTypes()[i])
                switch (i) {
                    case 0 ->
                            employees = employees.filter(employee -> employee.getOrderPrices().getWalkingPrice() > 0);
                    case 1 ->
                            employees = employees.filter(employee -> employee.getOrderPrices().getFurloughPrice() > 0);
                    case 2 ->
                            employees = employees.filter(employee -> employee.getOrderPrices().getDogsitterPrice() > 0);
                }

        switch (employeeFilter.getPriority()) {
            case Price -> employees = employees
                    .sorted((o1, o2) -> o1.getOrderPrices().getWalkingPrice() > 0 ? (int) (o1.getOrderPrices().getWalkingPrice() - o2.getOrderPrices().getWalkingPrice()) : 1)
                    .sorted((o1, o2) -> o1.getOrderPrices().getFurloughPrice() > 0 ? (int) (o1.getOrderPrices().getFurloughPrice() - o2.getOrderPrices().getFurloughPrice()) : 1)
                    .sorted((o1, o2) -> o1.getOrderPrices().getDogsitterPrice() > 0 ? (int) (o1.getOrderPrices().getDogsitterPrice() - o2.getOrderPrices().getDogsitterPrice()) : 1);
            case Review -> employees = employees
                    .sorted((o1, o2) -> o2.getReviewCount() - o1.getReviewCount());
            case Rating -> employees = employees
                    .sorted(Comparator.comparing(Employee::getRating).reversed());
        }
        return employees
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private EmployeeDTO convertToDto(Employee e) {
        var employeeDto = employeeMapper.employeeToDto(e);
        var orderPrices = e.getOrderPrices();
        employeeDto.setPrices(List.of(orderPrices.getWalkingPrice(), orderPrices.getFurloughPrice(), orderPrices.getDogsitterPrice()));
        employeeDto.setOrderTypes(List.of(orderPrices.getWalkingPrice() > 0, orderPrices.getFurloughPrice() > 0, orderPrices.getDogsitterPrice() > 0));
        employeeDto.setIsCat(e.getAnimalTypes().isCat());
        employeeDto.setIsDog(e.getAnimalTypes().isDog());
        return employeeDto;
    }
}
