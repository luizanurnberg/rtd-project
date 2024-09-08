package com.rtd.project.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class MachineUsage {
    private LocalDate date;
    private boolean hasProblem;
    private Double temperature;
    private Double usage;

    public MachineUsage(LocalDate date, boolean hasProblem, Double temperature, Double usage) {
        this.date = date;
        this.hasProblem = hasProblem;
        this.temperature = temperature;
        this.usage = usage;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
}
