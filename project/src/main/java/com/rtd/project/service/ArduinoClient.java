package com.rtd.project.service;

import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface ArduinoClient {
    @RequestLine("GET /MODE=ON&LED={led}")
    void turnOnLed(@Param("led") String led);

    @RequestLine("GET /MODE=OFF&LED={led}")
    void turnOffLed(@Param("led") String led);
}