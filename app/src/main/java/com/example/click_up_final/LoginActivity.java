package com.example.click_up_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText edtLoginID, edtLoginPassword;
    private Button btnMainRegister, btnMainLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 파이어베이스 Auth 초기화
        mAuth = FirebaseAuth.getInstance();

        edtLoginID = findViewById(R.id.edtLoginID);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

        findViewById(R.id.btnMainRegister).setOnClickListener(onClickListener);
        findViewById(R.id.btnMainLogin).setOnClickListener(onClickListener);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnMainRegister:
                    startActivity(RegisterActivity.class);
                    break;

                case R.id.btnMainLogin:
                    login();
                    break;
            }
        }
    };

    private void login() {
        String userID = edtLoginID.getText().toString();
        String userPassword = edtLoginPassword.getText().toString();

        if (userID.length() > 0 && userPassword.length() > 0) {
            mAuth.signInWithEmailAndPassword(userID, userPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                                startToast("로그인이 성공하였습니다.");
                                startActivity(MainActivity.class);
                            } else {
                                if (task.getException() != null) {
                                    startToast(task.getException().toString());
                                    updateUI(null);
                                }
                            }
                        }
                    });
        } else {
            startToast("이메일 또는 비밀번호를 입력해 주세요.");
        }

    }

    private void updateUI(FirebaseUser user) {
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
}


