package com.rtd.project.service;


import com.rtd.project.repository.MachineRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MachineService {
    @Autowired
    MachineRepository machineRepository;

}
