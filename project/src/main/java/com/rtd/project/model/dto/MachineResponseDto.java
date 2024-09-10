package com.rtd.project.model.dto;

import com.rtd.project.entity.MachineUsageEntity;

import java.time.LocalTime;
import java.util.List;

public record MachineResponseDto(Long id, String name, String brand, LocalTime startTime, LocalTime endTime, String ipAddress, List<MachineUsageEntity> machineUsage, boolean hasProblem) {
}
