package com.rtd.project.model.dto;

import java.time.LocalTime;

public record MachineResponseDto(Long id, String name, String brand, LocalTime startTime, LocalTime endTime, String ipAddress, Double usage) {
}
