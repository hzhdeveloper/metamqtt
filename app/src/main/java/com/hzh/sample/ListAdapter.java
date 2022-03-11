package com.hzh.sample;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyHolder> {

    public Context context;
    public List<MessageBean> data;
    public String myTopic;

    public ListAdapter(List<MessageBean> data, Context context, String myTopic) {
        this.context = context;
        this.data = data;
        this.myTopic = myTopic;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_list, null);  // 此处root一般填null，填parent会报错
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        // 数据绑定
        String topic = data.get(position).getTopic();
        String message = data.get(position).getMessage();
        if (myTopic.equals(data.get(position).getTopic())) {
            // 我发的
            holder.il_left_view.setVisibility(View.VISIBLE);
            holder.il_right_view.setVisibility(View.GONE);
            holder.il_text.setGravity(Gravity.END);
            holder.il_text.setText(topic + "\t的消息" +"•"+ "\n" + message);
        } else {
            // 对方发的
            holder.il_left_view.setVisibility(View.GONE);
            holder.il_right_view.setVisibility(View.VISIBLE);
            holder.il_text.setGravity(Gravity.START);
            holder.il_text.setText("•" + topic + "\t的消息" + "\n" + message);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        private TextView il_text;
        private View il_left_view;
        private View il_right_view;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            // 绑定View
            il_text = itemView.findViewById(R.id.il_text);
            il_left_view = itemView.findViewById(R.id.il_left_view);
            il_right_view = itemView.findViewById(R.id.il_right_view);
        }

    }
}
