package com.example.click_up_final.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.click_up_final.Adapter.CustomAdapter;
import com.example.click_up_final.R;
import com.example.click_up_final.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FriendFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<UserModel> arrayList;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friend, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_friend_recyclerview); // 리사이클러뷰 연결
        recyclerView.setHasFixedSize(true); // 성능 강화
        
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        
        arrayList = new ArrayList<>(); // 유저 객체를 담을 ArrayList
        
        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("users"); // DB 테이블 연결

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳

                // 새로고침 1 : 기존의 데이터를 없애고 새로운 데이터만 추가
                arrayList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // 반복문으로 데이터 리스트 추출
                    UserModel userModel = snapshot.getValue(UserModel.class); // UserModel 객체에 데이터를 담음
                    arrayList.add(userModel); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }

                // 새로고침 2 : 데이터가 추가되면 새로고침
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // DB를 가져오는 도중 에러 발생 -> 에러 Log 출력
                Log.e("FriendFragment", String.valueOf(error.toException()));
            }
        });

        adapter = new CustomAdapter(arrayList, getActivity());
        recyclerView.setAdapter(adapter); // 리사이클러뷰에 어댑터 연결
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
}
