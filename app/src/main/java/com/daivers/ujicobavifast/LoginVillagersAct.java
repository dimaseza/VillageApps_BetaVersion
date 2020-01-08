package com.daivers.ujicobavifast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginVillagersAct extends AppCompatActivity {

    Button btn_sign_in;
    TextView no_acnt;
    EditText phone_id, password_id;

    String PHONE_KEY = "phonekey";
    String phone_key = "";

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_villagers);

        btn_sign_in = findViewById(R.id.btn_sign_in);
        no_acnt = findViewById(R.id.no_acnt);
        phone_id = findViewById(R.id.phone_id);
        password_id = findViewById(R.id.password_id);

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String xphone_id = phone_id.getText().toString();
                final String xpassword_id = password_id.getText().toString();

                reference = FirebaseDatabase.getInstance().getReference().child("Users").child(xphone_id);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            //ambil password dari firebase
                            String passwordFirebase = dataSnapshot.child("password_id").getValue().toString();

                            //validasi password dengan password firebase
                            if(xpassword_id.equals(passwordFirebase)){

                                //simpan phone key kpd local
                                SharedPreferences sharedPreferences = getSharedPreferences(PHONE_KEY, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(phone_key, phone_id.getText().toString());
                                editor.apply();

                                Intent goHome = new Intent(LoginVillagersAct.this, HomeAct.class);
                                startActivity(goHome);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "Password Salah!", Toast.LENGTH_SHORT).show();
                            }


                        }else{
                            Toast.makeText(getApplicationContext(), "User tidak ada!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Database Error!", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        no_acnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goRegist = new Intent(LoginVillagersAct.this, RegistrasiAct.class);
                startActivity(goRegist);
            }
        });
    }
}
