package com.rtd.project.model;

import com.rtd.project.exception.MachineStartTimeException;

import java.time.LocalTime;
import java.util.List;

public class Machine {
    private String name;
    private String brand;
    private LocalTime startTime;
    private LocalTime endTime;
    private String machineIPAddress;
    private List<MachineUsage> usage;

    public Machine(String name, String brand, LocalTime startTime, LocalTime endTime, String machineIPAddress) {
        this.name = name;
        this.brand = brand;
        this.startTime = startTime;
        this.endTime = endTime;
        this.machineIPAddress = machineIPAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) throws MachineStartTimeException {
        if(startTime.isAfter(this.endTime)) {
            throw new MachineStartTimeException();
        }
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) throws MachineStartTimeException {
        if(endTime.isBefore(this.startTime)) {
            throw new MachineStartTimeException();
        }
        this.endTime = endTime;
    }

    public String getMachineIPAddress() {
        return machineIPAddress;
    }

    public void setMachineIPAddress(String machineIPAddress) {
        this.machineIPAddress = machineIPAddress;
    }
}
