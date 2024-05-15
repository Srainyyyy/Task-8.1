package com.example.llama2chatbot;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    // 定义发送消息的 API 请求
    @POST("chat")
    Call<ApiResponse> sendMessage(@Body ApiRequest request);
}
