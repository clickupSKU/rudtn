package com.example.click_up_final;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.click_up_final.Fragment.ChatFragment;
import com.example.click_up_final.Fragment.FriendFragment;
import com.example.click_up_final.Fragment.HomeFragment;
import com.example.click_up_final.Fragment.MyinfoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity {
    private long time = 0; // 뒤로가기 두 번 클릭 시 종료하기 위해 사용되는 변수
    Fragment home, chat, friend, myinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getHashKey();

        home = new HomeFragment();
        chat = new ChatFragment();
        friend = new FriendFragment();
        myinfo = new MyinfoFragment();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.linear01, home).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.linear01, home).commit();
                        return true;

                    case R.id.chat:
                        getSupportFragmentManager().beginTransaction().replace(R.id.linear01, chat).commit();
                        return true;

                    case R.id.friend:
                        getSupportFragmentManager().beginTransaction().replace(R.id.linear01, friend).commit();
                        return true;

                    case R.id.setting:
                        getSupportFragmentManager().beginTransaction().replace(R.id.linear01, myinfo).commit();
                        return true;
                }
                return false;
            }
        });
    }

    private void startActivity(Class c) {
        Intent intent = new Intent(this, c);
        // 메인엑티비티에서 뒤로가기 누를 시 바로 종료를 위함
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
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

