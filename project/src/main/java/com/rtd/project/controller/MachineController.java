package com.rtd.project.controller;

import com.rtd.project.exception.MachineStartTimeException;
import com.rtd.project.model.dto.ApiResponse;
import com.rtd.project.model.dto.MachineRequestDto;
import com.rtd.project.model.dto.MachineResponseDto;
import com.rtd.project.service.MachineService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/machine")
public class MachineController {
    private final MachineService machineService;
    @PostMapping("/")
    public ResponseEntity<ApiResponse<MachineResponseDto>> createMachine(@RequestBody MachineRequestDto machineRequestDto) {
        try {
            MachineResponseDto machineDto = machineService.createMachine(machineRequestDto);
            var apiResponse = new ApiResponse<MachineResponseDto>(true, "Your machine has been created successfully", machineDto);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (MachineStartTimeException exception) {
            var apiResponse = new ApiResponse<MachineResponseDto>(false, exception.getMessage(), null);
           return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
