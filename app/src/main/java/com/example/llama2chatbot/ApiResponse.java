package com.example.llama2chatbot;

public class ApiResponse {
    private String message; // 从后端接收到的消息

    // 构造函数，用于初始化从后端接收到的消息
    public ApiResponse(String message) {
        this.message = message;
    }

    // 获取从后端接收到的消息的方法
    public String getMessage() {
        return message;
    }

    // 设置从后端接收到的消息的方法
    public void setMessage(String message) {
        this.message = message;
    }
}
