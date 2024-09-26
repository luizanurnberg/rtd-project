package com.rtd.project.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "arduinoClient", url = "http://192.168.4.1")
public interface ArduinoClient {
    @GetMapping("/LED=ON")
    void turnOnLed();

    @GetMapping("/LED=OFF")
    void turnOffLed();
}