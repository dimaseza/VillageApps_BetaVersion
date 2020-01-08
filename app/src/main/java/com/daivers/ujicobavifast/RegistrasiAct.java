package com.daivers.ujicobavifast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrasiAct extends AppCompatActivity {

    Button btn_regist;
    EditText fullname_id, phone_id, password_id;
    DatabaseReference reference;

    String PHONE_KEY = "phonekey";
    String phone_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        fullname_id = findViewById(R.id.fullname_id2);
        phone_id = findViewById(R.id.phone_id2);
        password_id = findViewById(R.id.password_id2);

        btn_regist = findViewById(R.id.btn_regist);

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(PHONE_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(phone_key, phone_id.getText().toString());
                editor.apply();

                //save to database
                reference = FirebaseDatabase.getInstance().getReference().child("Users").child(phone_id.getText().toString());
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("fullname_id").setValue(fullname_id.getText().toString());
                        dataSnapshot.getRef().child("nomor_telepon_id").setValue(phone_id.getText().toString());
                        dataSnapshot.getRef().child("password_id").setValue(password_id.getText().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent goSucces = new Intent(RegistrasiAct.this, RegisterSucccesAct.class);
                startActivity(goSucces);
                finish();
            }
        });

    }

}
