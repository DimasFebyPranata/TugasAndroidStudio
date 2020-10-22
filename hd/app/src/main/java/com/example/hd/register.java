package com.example.hd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class register extends AppCompatActivity implements View.OnClickListener {
    private Button reg;
    private TextView tvLogin;
    private EditText etUsername, etPassword;
    private DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DbHelper(this);
        reg = (Button)findViewById(R.id.btnReg);
        tvLogin = (TextView)findViewById(R.id.tvLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        reg.setOnClickListener(this);
        tvLogin.setOnClickListener(this);

    }

    public void register(View view) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnReg:
                register();
                break;

            case R.id.tvLogin:
                startActivity(new Intent(register.this,MainActivity.class));
                finish();
                break;
            default:
        }
    }

    private void register(){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if(username.isEmpty() && password.isEmpty()){
            displayToast("Username/Password Kosong");
        }else{
            db.addUser(username,password);
            displayToast("Registrasi Berhasil");
            finish();
        }
    }


    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}