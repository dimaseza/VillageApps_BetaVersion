package com.daivers.ujicobavifast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class RegisterSucccesAct extends AppCompatActivity {

    ImageButton btn_back;
    Button btn_go_sign_in;
    EditText otp_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_succces);

        btn_back = findViewById(R.id.btn_back);
        btn_go_sign_in = findViewById(R.id.btn_go_sign_in);
        otp_code = findViewById(R.id.otp_code);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBack = new Intent(RegisterSucccesAct.this, RegistrasiAct.class);
                startActivity(goBack);
                finish();
            }
        });

        btn_go_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goSignIn = new Intent(RegisterSucccesAct.this, LoginVillagersAct.class);
                startActivity(goSignIn);
                finish();
            }
        });

    }
}
