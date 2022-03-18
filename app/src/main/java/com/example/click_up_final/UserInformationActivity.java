package com.example.click_up_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserInformationActivity extends AppCompatActivity {
    private long time = 0; // 뒤로가기 두 번 클릭 시 종료하기 위해 사용되는 변수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfromation);

        findViewById(R.id.btnInformation).setOnClickListener(onClickListener);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnInformation:
                    profileUpdate();
                    break;
            }
        }
    };

    private void profileUpdate() {
        String name = ((EditText) findViewById(R.id.edtInformName)).getText().toString();
        String birth = ((EditText) findViewById(R.id.edtInfromBirth)).getText().toString();
        String phone = ((EditText) findViewById(R.id.edtInfromPhone)).getText().toString();
        String nickname = ((EditText) findViewById(R.id.edtInfromNickname)).getText().toString();

        if (name.length() > 0 && phone.length() > 0 && birth.length() > 0 && nickname.length() > 0) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();


            UserInformationCheck userinfo = new UserInformationCheck(name, birth, phone, nickname);


            db.collection("users").document(name).set(userinfo)
                    .addOnSuccessListener(avoid -> {
                        startToast("회원정보가 등록되었습니다.");
                        finish();
                    })
                    .addOnFailureListener(e -> startToast("회원정보 등록이 실패하였습니다."));

        }

    }

    private void startToast(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void startActivity(Class c) {
        Intent intent = new Intent(this, c);
        // 메인엑티비티에서 뒤로가기 누를 시 바로 종료를 위함
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    //뒤로가기 버튼 2번을 통해 시스템 종료
//    @Override
//    public void onBackPressed() {
//        if (System.currentTimeMillis() - time >= 2000) {
//            time = System.currentTimeMillis();
//            Toast.makeText(getApplicationContext(), "뒤로가기 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
//        } else if (System.currentTimeMillis() - time < 2000) {
//            moveTaskToBack(true);
//            finishAndRemoveTask();
//        }
//    }
}
