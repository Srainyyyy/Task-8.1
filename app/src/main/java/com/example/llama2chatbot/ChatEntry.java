package com.example.llama2chatbot;

public class ChatEntry {
    private String userMessage; // 用户消息
    private String botResponse; // 聊天机器人的回复

    // 构造函数，用于初始化用户消息和聊天机器人的回复
    public ChatEntry(String userMessage, String botResponse) {
        this.userMessage = userMessage;
        this.botResponse = botResponse;
    }

    // 获取用户消息的方法
    public String getUserMessage() {
        return userMessage;
    }

    // 设置用户消息的方法
    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    // 获取聊天机器人的回复的方法
    public String getBotResponse() {
        return botResponse;
    }

    // 设置聊天机器人的回复的方法
    public void setBotResponse(String botResponse) {
        this.botResponse = botResponse;
    }
}
