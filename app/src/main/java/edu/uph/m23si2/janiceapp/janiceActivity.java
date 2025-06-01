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

public class janiceActivity extends AppCompatActivity {

    EditText edtNilaiA, edtNilaiB;
    Button btnTambah, btnKali, btnBagi;
    TextView txvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_janice);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtNilaiA = findViewById(R.id.edtNilaiA);
        edtNilaiB = findViewById(R.id.edtNilaiB);
        btnTambah = findViewById(R.id.btnTambah);
        btnKali = findViewById(R.id.btnKali);
        btnBagi = findViewById(R.id.btnBagi);
        txvHasil = findViewById(R.id.txvHasil);

        // Tambah
        btnTambah.setOnClickListener(v -> {
            double a = ambilAngka(edtNilaiA);
            double b = ambilAngka(edtNilaiB);
            double hasil = a + b;
            txvHasil.setText(formatHasil(hasil));
        });

        // Kali
        btnKali.setOnClickListener(v -> {
            double a = ambilAngka(edtNilaiA);
            double b = ambilAngka(edtNilaiB);
            double hasil = a * b;
            txvHasil.setText(formatHasil(hasil));
        });

        // Bagi
        btnBagi.setOnClickListener(v -> {
            double a = ambilAngka(edtNilaiA);
            double b = ambilAngka(edtNilaiB);
            if (b == 0) {
                txvHasil.setText("Tidak bisa dibagi 0");
            } else {
                double hasil = a / b;
                txvHasil.setText(formatHasil(hasil));
            }
        });
    }

    private double ambilAngka(EditText editText) {
        String nilai = editText.getText().toString();
        if (nilai.isEmpty()) return 0;
        return Double.parseDouble(nilai);
    }

    private String formatHasil(double angka) {
        if (angka == (long) angka) {
            return String.format("%d", (long) angka);
        } else {
            return String.format("%s", angka);
        }
    }
}