package com.example.hd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login, register;
    private EditText etUsername, etPassword;
    private DbHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DbHelper(this);
        session = new Session(this);
        login = (Button)findViewById(R.id.btnLog);
        register = (Button)findViewById(R.id.btnReg);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        if(session.loggedin()){
            startActivity(new Intent(MainActivity.this, mainpage.class));
            finish();
        }
    }

    public void login(View view) {
    }

    public void register(View view) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLog:
                login();
                break;
            case R.id.btnReg:
                startActivity(new Intent(MainActivity.this, com.example.hd.register.class));
                break;
            default:

        }
    }

    private void login(){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if(db.getUser(username,password)){
            session.setLoggedin(true);
            startActivity(new Intent(MainActivity.this, mainpage.class));
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "USERNAME/PASSWORD SALAH", Toast.LENGTH_SHORT).show();
        }
    }
}