package com.example.llama2chatbot;

import android.widget.EditText;

import java.util.List;

public class ApiRequest {
    private String message; // 用户消息
    private List<ChatEntry> chatHistory; // 聊天历史记录

    // 构造函数，用于初始化用户消息和聊天历史记录
    public ApiRequest(String message, List<ChatEntry> chatHistory) {
        this.message = message;
        this.chatHistory = chatHistory;
    }

    // 获取用户消息的方法
    public String getMessage() {
        return message;
    }

    // 设置用户消息的方法
    public void setMessage(String message) {
        this.message = message;
    }

    // 获取聊天历史记录的方法
    public List<ChatEntry> getChatHistory() {
        return chatHistory;
    }

    // 设置聊天历史记录的方法
    public void setChatHistory(List<ChatEntry> chatHistory) {
        this.chatHistory = chatHistory;
    }
}
