package com.happytails.springserver.controller;

import com.happytails.springserver.dto.EmployeeDTO;
import com.happytails.springserver.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/employee/photo")
    public EmployeeDTO uploadEmployeePhoto(@RequestBody MultipartFile photo, @RequestParam("employee_id") Long employeeId) throws IOException {
        return employeeService.uploadEmployeePhoto(photo, employeeId);
    }
}
