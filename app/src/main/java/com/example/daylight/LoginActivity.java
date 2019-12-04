package com.example.daylight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {


//    @BindView(R.id.login_email)
//    TextView login_email;
//
//    @BindView(R.id.login_pwd)
//    TextView login_pwd;
//
//    @BindView(R.id.login_btn)
//    Button login_btn;


    Button login_btn;
    Button login_join;
    TextView login_email;
    TextView login_pwd;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        login_btn = (Button) findViewById(R.id.login_btn);
        login_join = (Button) findViewById(R.id.login_join);
        login_email = (TextView) findViewById(R.id.login_email);
        login_pwd = (TextView) findViewById(R.id.login_pwd);




//        login_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (login_email != null && login_pwd != null ) {
//                    String email = login_email.getText().toString();
//                    String password = login_pwd.getText().toString();
//                    checkValidUser(email, password);
//                }else{
//                    Toast.makeText(LoginActivity.this, "인증 실패", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });

        login_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login_email != null && login_pwd != null ) {
                    String email = login_email.getText().toString();
                    String password = login_pwd.getText().toString();
                    checkValidUser(email, password);
                }else{
                    Toast.makeText(LoginActivity.this, "인증 실패", Toast.LENGTH_SHORT).show();
                }
        Toast.makeText(LoginActivity.this, "인증 실패", Toast.LENGTH_SHORT).show();
            }
        });

    }

//    @OnClick(R.id.login_btn)
//    void onButtonClicked(){
//        if (login_email != null && login_pwd != null ) {
//                    String email = login_email.getText().toString();
//                    String password = login_pwd.getText().toString();
//                    checkValidUser(email, password);
//                }else{
//                    Toast.makeText(LoginActivity.this, "인증 실패", Toast.LENGTH_SHORT).show();
//                }
//        Toast.makeText(LoginActivity.this, "인증 실패", Toast.LENGTH_SHORT).show();
//    }

    public void checkValidUser(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "인증 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}

