package com.example.aplikasivalidasilogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    EditText username, password, email, namaLengkap, asalSekolah, alamat;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Register");

        username = findViewById(R.id.edtUsername);
        password = findViewById(R.id.edtPassword);
        email = findViewById(R.id.edtEmail);
        namaLengkap = findViewById(R.id.edtNama);
        asalSekolah = findViewById(R.id.edtAsalSekolah);
        alamat = findViewById(R.id.edtAlamat);

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidation()) {
                    simpanFileData();
                }else {
                    Toast.makeText(RegisterActivity.this, "Mohon Lengkapi seluruh data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    boolean isValidation() {
        if (username.getText().toString().equals("") || password.getText().toString().equals("") || email.getText().toString().equals("")
        || namaLengkap.getText().toString().equals("") || asalSekolah.getText().toString().equals("") || alamat.getText().toString().equals("")) {
            return false;
        }else {
            return true;
        }
    }

    void simpanFileData() {
        String isiFile = username.getText().toString() + ";" + password.getText().toString() + ";" + email.getText().toString() + ";" + namaLengkap.getText().toString()
                + ";" + asalSekolah.getText().toString() + ";" + alamat.getText().toString();
        File file = new File(getFilesDir(), username.getText().toString());

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}