package com.rtd.project.entity.mapper;

import com.rtd.project.entity.MachineEntity;
import com.rtd.project.model.Machine;
import com.rtd.project.model.dto.MachineResponseDto;

public class MachineMapper {
    public static MachineEntity toEntity(Machine machine) {
        MachineEntity entity = new MachineEntity();
        entity.setName(machine.getName());
        entity.setBrand(machine.getBrand());
        entity.setMachineIPAddress(machine.getMachineIPAddress());
        entity.setStartTime(machine.getStartTime());
        entity.setEndTime(machine.getEndTime());
        entity.setTurnedOn(machine.isMachineOn());
        return entity;
    }

    public static MachineResponseDto toDto(MachineEntity entity) {
        MachineResponseDto responseDto = new MachineResponseDto(entity.getId(), entity.getName(), entity.getBrand(),
                entity.getStartTime(), entity.getEndTime(), entity.getMachineIPAddress(), entity.getUsage(),
                entity.isTurnedOn());
        return responseDto;
    }
}
