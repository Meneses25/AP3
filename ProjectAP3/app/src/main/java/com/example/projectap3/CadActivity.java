package com.example.projectap3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadActivity extends AppCompatActivity {

    EditText txtUser, txtPass, txtRepass;
    Button btnCad;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad);

        txtUser = findViewById(R.id.textUser);
        txtPass = findViewById(R.id.txtPass);
        txtRepass = findViewById(R.id.txtRepass);
        btnCad = findViewById(R.id.btnCad);
        DB = new DBHelper(this);

        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String User = txtUser.getText().toString();
                String Pass = txtPass.getText().toString();
                String Repass = txtRepass.getText().toString();

                if (User.equals("")||Pass.equals("")||Repass.equals(""))
                    Toast.makeText(CadActivity.this, "Preencher todos os campos", Toast.LENGTH_SHORT).show();
                else{
                    if (txtPass.equals(Repass)){
                        Boolean checkUser = DB.checktxtUser(User);
                        if (!checkUser){
                            Boolean insert = DB.insertData(User, Pass);
                            if (insert){
                                Toast.makeText(CadActivity.this, "Registrado com sucesso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);

                            }else {
                                Toast.makeText(CadActivity.this, "Registro falhou", Toast.LENGTH_SHORT).show();


                            }

                        }
                        else {
                            Toast.makeText(CadActivity.this, "Usuario já existe! por favor logar", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}