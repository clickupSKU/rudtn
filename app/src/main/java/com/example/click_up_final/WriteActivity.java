package com.example.click_up_final;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WriteActivity extends AppCompatActivity {
    private long time = 0; // 뒤로가기 두 번 클릭 시 종료하기 위해 사용되는 변수
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
    }
    //뒤로가기 버튼 2번을 통해 시스템 종료
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - time >= 2000) {
            time = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "뒤로가기 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
        } else if (System.currentTimeMillis() - time < 2000) {
            moveTaskToBack(true);
            finishAndRemoveTask();
        }
    }
}
