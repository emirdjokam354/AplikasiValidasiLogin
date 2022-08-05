package com.example.aplikasivalidasilogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String FILENAME = "login";
    EditText username, password, email, namaLengkap, asalSekolah, alamat;
    TextView txtPass;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Halaman Depan");

        username = findViewById(R.id.edtUsername);
        password = findViewById(R.id.edtPassword);
        email = findViewById(R.id.edtEmail);
        namaLengkap = findViewById(R.id.edtNama);
        asalSekolah = findViewById(R.id.edtAsalSekolah);
        alamat = findViewById(R.id.edtAlamat);
        txtPass = findViewById(R.id.txtPass);
        btnSimpan = findViewById(R.id.btnSimpan);

        btnSimpan.setVisibility(View.GONE);
        username.setEnabled(false);
        password.setVisibility(View.GONE);
        txtPass.setVisibility(View.GONE);
        email.setEnabled(false);
        namaLengkap.setEnabled(false);
        asalSekolah.setEnabled(false);
        alamat.setEnabled(false);

        bacaFileLogin();

    }

    void bacaFileLogin() {
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();

                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }

                br.close();
            }catch (IOException e) {
                System.out.println("Error : " + e.getMessage());
            }

            String data = text.toString();
            String[] dataItem = data.split(";");

            bacaDataUser(dataItem[0]);
        }
    }

    void bacaDataUser(String filename) {
//        Log.d("Check", "bacaDataUser: " + filename );
        File sdcard = getFilesDir();
        File file = new File(sdcard, filename);
        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();

                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }

                br.close();
            }catch (IOException e) {
                System.out.println("Error : " + e.getMessage());
            }

            String data = text.toString();
            String[] dataUser = data.split(";");

            username.setText(dataUser[0]);
            password.setText(dataUser[1]);
            email.setText(dataUser[2]);
            namaLengkap.setText(dataUser[3]);
            asalSekolah.setText(dataUser[4]);
            alamat.setText(dataUser[5]);
        }else {
            Toast.makeText(this, "File tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout :
                tampilkanDialogKonfirmasiLogout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    void hapusFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }

    void tampilkanDialogKonfirmasiLogout() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah anda yakin ingin logout ?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        hapusFile();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton(android.R.string.no, null).show();
    }
}