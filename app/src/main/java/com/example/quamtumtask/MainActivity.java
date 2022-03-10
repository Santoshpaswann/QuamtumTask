package com.example.quamtumtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView btn_login, btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);
        btn_login.setBackgroundResource(R.drawable.btn_background);
        btn_signup.setBackgroundResource(R.drawable.btn_background_white);
        btn_login.setTextColor(getResources().getColor(R.color.white));
        btn_signup.setTextColor(getResources().getColor(R.color.black));

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, new LoginFragment());
        ft.commit();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_login.setBackgroundResource(R.drawable.btn_background);
                btn_login.setTextColor(getResources().getColor(R.color.white));
                btn_signup.setTextColor(getResources().getColor(R.color.black));
                btn_signup.setBackgroundResource(R.drawable.btn_background_white);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new LoginFragment());
                ft.commit();
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_signup.setBackgroundResource(R.drawable.btn_background);
                btn_login.setBackgroundResource(R.drawable.btn_background_white);
                btn_login.setTextColor(getResources().getColor(R.color.black));
                btn_signup.setTextColor(getResources().getColor(R.color.white));
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new SignUpFragment());
                ft.commit();
            }
        });

    }
}