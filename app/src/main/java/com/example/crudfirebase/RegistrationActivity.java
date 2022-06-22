package com.example.crudfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.crudfirebase.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private ActivityRegistrationBinding binding;

    private FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        myAuth = FirebaseAuth.getInstance();

        binding.idTextAlreadyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(i);

            }
        });

        binding.idBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.idPBLoading.setVisibility(View.VISIBLE);
                String userName = binding.idEdtUsername.getText().toString();
                String pwd = binding.idEdtPassword.getText().toString();
                String confirmPwd = binding.idEdtConfirmPassword.getText().toString();

                if (!pwd.equals(confirmPwd)){
                    Toast.makeText(RegistrationActivity.this, "Password not same", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(confirmPwd)){
                    Toast.makeText(RegistrationActivity.this, "Please add your credentials", Toast.LENGTH_SHORT).show();
                }else{
                    myAuth.createUserWithEmailAndPassword(userName, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                binding.idPBLoading.setVisibility(View.GONE);
                                Toast.makeText(RegistrationActivity.this, "User registered!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();
                            }
                            binding.idPBLoading.setVisibility(View.GONE);
                            Toast.makeText(RegistrationActivity.this, "Register fail!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}