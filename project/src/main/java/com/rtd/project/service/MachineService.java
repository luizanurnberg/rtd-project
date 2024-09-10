package com.rtd.project.service;


import com.rtd.project.entity.MachineEntity;
import com.rtd.project.entity.mapper.MachineMapper;
import com.rtd.project.exception.MachineStartTimeException;
import com.rtd.project.model.Machine;
import com.rtd.project.model.dto.MachineRequestDto;
import com.rtd.project.model.dto.MachineResponseDto;
import com.rtd.project.model.factory.MachineFactory;
import com.rtd.project.repository.MachineRepository;
import com.rtd.project.repository.MachineUsageRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MachineService {
    @Autowired
    MachineRepository machineRepository;
    MachineUsageRepository machineUsageRepository;

    public MachineResponseDto createMachine(MachineRequestDto machineRequestDto) throws MachineStartTimeException {
        Machine machine = MachineFactory.create(machineRequestDto.name(), machineRequestDto.brand(), machineRequestDto.startTime(), machineRequestDto.endTime(), machineRequestDto.ipAddress(), machineRequestDto.isTurnedOn());
        MachineEntity entity = MachineMapper.toEntity(machine);
        return MachineMapper.toDto(machineRepository.save(entity));

    }

    public MachineResponseDto updateMachine(MachineRequestDto machineRequestDto) {
        return null;
    }

    public MachineResponseDto getMachineById(Long machineId) throws NoSuchElementException {
        MachineEntity entity = machineRepository.findById(machineId).orElseThrow();
        return MachineMapper.toDto(entity);
    }

    public void deleteMachine(Long machineId) {
        machineRepository.deleteById(machineId);
    }

    public List<MachineResponseDto> getAllMachines() {
        List<MachineEntity> entities = machineRepository.findAll();
        return entities.stream().map(MachineMapper::toDto).toList();
    }
}
