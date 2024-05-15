package com.example.llama2chatbot;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://10.0.2.2:5000/"; // 后端 API 的基本 URL
    private static Retrofit retrofit = null;

    // 获取 Retrofit 客户端实例
    public static ApiService getClient() {
        if (retrofit == null) {
            // 创建 OkHttpClient，添加日志拦截器
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);

            // 构建 Retrofit 实例，并设置基本 URL、JSON 转换器和 OkHttpClient
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClientBuilder.build())
                    .build();
        }
        // 创建并返回 API 服务实例
        return retrofit.create(ApiService.class);
    }
}
