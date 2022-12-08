package com.example.projectap3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtUser, txtPass;
    Button btnLogin, btnCad;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPass = (EditText) findViewById(R.id.txtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCad = (Button) findViewById(R.id.btnCad);
        DB = new DBHelper(this);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String User = txtUser.getText().toString();
                String Pass = txtPass.getText().toString();

                if (User.equals("")||Pass.equals(""))
                    Toast.makeText(MainActivity.this, "Preencher todos os campos", Toast.LENGTH_SHORT).show();

                else {
                    Boolean checkUserPass = DB.checktxtUsertxtPass(User, Pass);
                    if (checkUserPass){
                        Toast.makeText(MainActivity.this, "Login com sucesso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadActivity.class);
                startActivity(intent);
            }
        });
    }
}