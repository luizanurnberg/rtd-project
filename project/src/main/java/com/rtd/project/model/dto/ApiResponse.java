package com.rtd.project.model.dto;

public record ApiResponse<T>(boolean success, String message, T data){}
