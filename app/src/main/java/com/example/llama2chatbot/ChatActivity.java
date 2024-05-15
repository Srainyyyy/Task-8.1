package com.example.llama2chatbot;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {
    private EditText userInputEditText; // 用户输入框
    private Button sendButton; // 发送按钮
    private RecyclerView chatRecyclerView; // 聊天消息列表的 RecyclerView
    private ChatAdapter adapter; // 聊天消息列表的适配器
    private List<ChatEntry> chatHistory = new ArrayList<>(); // 存储聊天历史记录的列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // 初始化视图组件
        userInputEditText = findViewById(R.id.message_input);
        chatRecyclerView = findViewById(R.id.chat_recycler_view);

        // 设置 RecyclerView 的布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        chatRecyclerView.setLayoutManager(layoutManager);

        // 初始化适配器并设置到 RecyclerView
        adapter = new ChatAdapter(this, chatHistory);
        chatRecyclerView.setAdapter(adapter);

        // 获取发送按钮并设置点击监听器
        sendButton = findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 检查用户输入框是否成功获取
                if (userInputEditText == null) {
                    Log.e("ChatActivity", "User input EditText is null.");
                    return;
                }

                // 获取用户输入的消息
                String userInputMessage = userInputEditText.getText().toString().trim();
                if (TextUtils.isEmpty(userInputMessage)) {
                    // 用户输入为空，不进行后续操作
                    return;
                }

                // 发送消息到后端
                sendMessageToBackend(userInputMessage);

                // 清空输入框
                userInputEditText.setText("");
            }

        });
    }

    // 发送消息到后端
    private void sendMessageToBackend(String message) {
        // 获取当前的聊天历史记录列表
        List<ChatEntry> currentChatHistory = chatHistory;

        // 创建 ApiRequest 对象，并将当前的聊天历史记录列表传递给它
        ApiRequest request = new ApiRequest(message, currentChatHistory);

        // 发送请求到后端 API
        ApiService apiService = ApiClient.getClient();
        Call<ApiResponse> call = apiService.sendMessage(request);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    // 处理成功响应
                    ApiResponse apiResponse = response.body();
                    String botResponse = apiResponse.getMessage();
                    // 将聊天机器人的回复添加到聊天历史记录中
                    chatHistory.add(new ChatEntry("", botResponse));
                    // 刷新 RecyclerView
                    adapter.notifyDataSetChanged();
                } else {
                    // 处理错误响应
                    Toast.makeText(ChatActivity.this, "Failed to send message. " , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // 处理请求失败
                Toast.makeText(ChatActivity.this, "Failed to send message. Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ChatActivity", "Failed to send message", t); // 添加日志输出语句
            }
        });
    }

}
