package com.rtd.project.service;

import feign.RequestLine;

public interface ArduinoClient {
    @RequestLine("GET /LED=ON")
    void turnOnLed();

    @RequestLine("GET /LED=OFF")
    void turnOffLed();
}