package com.rtd.project.model.factory;

import com.rtd.project.exception.MachineStartTimeException;
import com.rtd.project.model.Machine;
import com.rtd.project.model.MachineUsage;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MachineFactory {
    public static Machine create(String name, String brand, LocalTime startTime, LocalTime endTime, String machineIPAddress) throws MachineStartTimeException{
        if(startTime.isBefore(endTime)){
            throw new MachineStartTimeException();
        }
        Machine machine = new Machine(name, brand, startTime, endTime, machineIPAddress);
        List <MachineUsage> usageList = new ArrayList<>();
        machine.setUsage(usageList);
        return machine;
    }
}
