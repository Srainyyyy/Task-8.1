package com.example.llama2chatbot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder> {

    private List<ChatEntry> chatHistory;
    private Context context;

    public ChatAdapter(Context context, List<ChatEntry> chatHistory) {
        this.context = context;
        this.chatHistory = chatHistory;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_chat_ing, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        ChatEntry chatEntry = chatHistory.get(position);
        holder.bind(chatEntry);
    }

    @Override
    public int getItemCount() {
        return chatHistory.size();
    }


    public class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewUser;
        private TextView textViewUserText;
        private ImageView imageViewChat;
        private TextView textViewChatText;
        private LinearLayout messageContainer;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUser = itemView.findViewById(R.id.textViewUser);
            textViewUserText = itemView.findViewById(R.id.textViewUserText);
            imageViewChat = itemView.findViewById(R.id.imageViewChat);
            textViewChatText = itemView.findViewById(R.id.textViewChatText);
        }

        public void bind(ChatEntry chatEntry) {
            textViewUserText.setText(chatEntry.getUserMessage());
            textViewChatText.setText(chatEntry.getBotResponse());
        }
        }
    }

