package com.example.click_up_final.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.click_up_final.LoginActivity;
import com.example.click_up_final.R;
import com.example.click_up_final.UserInformationActivity;

public class MyinfoFragment extends Fragment {
    private ViewGroup rootView;
    private Button btnInformRegister, btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_myinfo, container, false);

        btnInformRegister = (Button) rootView.findViewById(R.id.btnInformRegister);
        btnLogout = (Button) rootView.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnInformRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserInformationActivity.class);
                startActivity(intent);
            }
        });


        return rootView;
    }
}