package com.rtd.project.model;

import java.time.LocalDate;

public class MachineUsage {
    private final LocalDate date;
    private boolean hasProblem;
    private Double temperature;
    private Double usage;
    private final Machine machine;

    public MachineUsage(LocalDate date, boolean hasProblem, Double temperature, Double usage, Machine machine) {
        this.date = date;
        this.hasProblem = hasProblem;
        this.temperature = temperature;
        this.usage = usage;
        this.machine = machine;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isHasProblem() {
        return hasProblem;
    }

    public void setHasProblem(boolean hasProblem) {
        this.hasProblem = hasProblem;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getUsage() {
        return usage;
    }

    public void setUsage(Double usage) {
        this.usage = usage;
    }

    public Machine getMachine() {
        return machine;
    }
}
