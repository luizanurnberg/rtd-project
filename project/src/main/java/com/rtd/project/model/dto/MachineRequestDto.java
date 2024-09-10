package com.rtd.project.model.dto;

import java.time.LocalTime;

public record MachineRequestDto(String name, String brand, LocalTime startTime, LocalTime endTime, String ipAddress, boolean isTurnedOn) {
}
