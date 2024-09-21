package com.rtd.project.exception;

public class MachineStartTimeException extends RuntimeException {
    public MachineStartTimeException() {
        super("Your machine should start before the end time");
    }
}
