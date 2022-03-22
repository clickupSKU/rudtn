package com.example.click_up_final.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.click_up_final.LoginActivity;
import com.example.click_up_final.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyinfoFragment extends Fragment {
    private static final int PICK_FROM_ALBUM = 10;
    private FirebaseAuth firebaseAuth;

    private ViewGroup rootView;
    private Button btnInformRegister, btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_myinfo, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        } else {

            FirebaseUser user = firebaseAuth.getCurrentUser();
            Toast.makeText(getActivity(), "반갑습니다 " + user.getEmail() + "님", Toast.LENGTH_SHORT).show();
        }

            btnInformRegister = (Button) rootView.findViewById(R.id.btnInformRegister);
            btnLogout = (Button) rootView.findViewById(R.id.btnLogout);

            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    firebaseAuth.signOut();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });

            return rootView;
        }
}