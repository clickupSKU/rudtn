package com.example.click_up_final.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.click_up_final.R;
import com.example.click_up_final.UserModel;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<UserModel> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<UserModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        // 실제 데이터들과 매칭
        
        /*
        이미지 사용할 경우 사용하는 Glide 코드
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getImage)
                .into(holder.imageView);
         */

        holder.textView.setText(arrayList.get(position).getUserNickname());

    }

    @Override
    public int getItemCount() {
        // arrayList가 null이 아니면 arrayList의 사이즈 반환, null이면 0 반환
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        // ViewHolder에 이미지뷰와 텍스트뷰 선언
        ImageView imageView;
        TextView textView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.frienditem_imageview);
            this.textView = itemView.findViewById(R.id.frienditem_textview);
        }
    }
}
