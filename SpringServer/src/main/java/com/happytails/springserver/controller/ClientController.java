package com.happytails.springserver.controller;

import com.happytails.springserver.dto.EmployeeDTO;
import com.happytails.springserver.dto.OrderDTO;
import com.happytails.springserver.dto.PetDTO;
import com.happytails.springserver.models.Order;
import com.happytails.springserver.models.Pet;
import com.happytails.springserver.service.CustomerService;
import com.happytails.springserver.service.EmployeeService;
import com.happytails.springserver.filter.EmployeeFilter;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.net.*;
import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0")
public class ClientController {
    private final EmployeeService employeeService;
    private final CustomerService customerService;

    @GetMapping("/employee")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee/filter")
    public List<EmployeeDTO> getEmployeesByFilter(@RequestBody EmployeeFilter employeeFilter, HttpServletRequest request) throws IOException, GeoIp2Exception {
//        URL url = new URL("http://checkip.amazonaws.com");
//        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
//        var response = customerService.getUserLocation(in.readLine()); request.getRemoteAddr()
//        System.out.printf("Ваш город - %s?\n", response.getCity().getNames().get("ru"));
        return employeeService.getEmployeesByFilter(employeeFilter);
    }

    @PutMapping("/employee/rate")
    public void rateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.rateEmployee(employeeDTO);
    }

    @PostMapping("/pet")
    public Pet addPet(@Valid @RequestBody PetDTO petDTO) {
        return customerService.savePet(petDTO);
    }
    @PutMapping("/pet")
    public Pet updatePet(@Valid @RequestBody PetDTO petDTO) {
        return customerService.savePet(petDTO);
    }

    @DeleteMapping("/pet")
    public void deletePet(@Valid @RequestBody PetDTO petDTO) {
        customerService.deletePet(petDTO);
    }

    @PostMapping("/order")
    public Order createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        return customerService.createOrder(orderDTO);
    }
}
