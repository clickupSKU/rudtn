package com.example.click_up_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private long time = 0; // 뒤로가기 두 번 클릭 시 종료하기 위해 사용되는 변수
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btnRegister).setOnClickListener(onClickListener);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        moveTaskToBack(true);
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(1);
//    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnRegister:
                    signUp();
                    break;
            }
        }
    };

    private void signUp() {
        String userEmail = ((EditText)findViewById(R.id.edtId)).getText().toString().trim();
        String userPassword = ((EditText)findViewById(R.id.edtPassword)).getText().toString();
        String userPasswordCheck = ((EditText)findViewById(R.id.edtPasswordCheck)).getText().toString();
        String userName = ((EditText)findViewById(R.id.edtName)).getText().toString();

        if(userEmail.length() > 0 && userPassword.length() > 0 && userPasswordCheck.length() > 0 && userName.length() > 0) {

            if (userPassword.equals(userPasswordCheck)) {
                mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startToast("회원가입을 성공하였습니다.");
                                    updateUI(user);
                                    finish();
                                    //startActivity(UserInformationActivity.class);
                                } else {
                                    if (task.getException().toString() != null) {
                                        startToast(task.getException().toString());
                                        updateUI(null);
                                    }
                                }
                            }
                        });
            } else {
                startToast("비밀번호가 일치하지 않습니다.");
            }
        } else {
            startToast("이메일 또는 비밀번호를 입력해 주세요.");
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

    private void updateUI(FirebaseUser user) {

    }
//    //뒤로가기 버튼 2번을 통해 시스템 종료
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



