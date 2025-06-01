package edu.uph.m23si2.janiceapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfilActivity extends AppCompatActivity {
    Button btnSubmit;
    EditText edtNama, edtEmail, edtProdi;
    TextView txvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSubmit = findViewById(R.id.btnSubmit);
        edtNama = findViewById(R.id.edtNama);
        edtEmail = findViewById(R.id.edtEmail);
        edtProdi = findViewById(R.id.edtProdi);
        txvHasil = findViewById(R.id.txvHasil);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Menampilkan hasil di txvHasil
                String nama = edtNama.getText().toString();
//                Mengambil nama dari edtNama dan ganti ke string dulu
                String prodi = edtProdi.getText().toString();
                String email = edtEmail.getText().toString();
                    txvHasil.setText(nama + "\n"+ email+ "\n" + prodi);
            }
        });
    }
}