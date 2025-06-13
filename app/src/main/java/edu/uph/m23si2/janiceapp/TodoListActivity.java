package edu.uph.m23si2.janiceapp;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TodoListActivity extends AppCompatActivity {
    EditText edtNamaKegiatan;
    RadioButton rdbDalam, rdbLuar;
    CheckBox cbxOlahraga, cbxMakan, cbxJalan, cbxBelajar;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_todo_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtNamaKegiatan = findViewById(R.id.edtNamaKegiatan);
        rdbDalam = findViewById(R.id.rdbDalam);
        rdbLuar = findViewById(R.id.rdbLuar);
        cbxBelajar = findViewById(R.id.cbxBelajar);
        cbxMakan = findViewById(R.id.cbxMakan);
        cbxJalan =findViewById(R.id.cbxJalan);
        cbxOlahraga = findViewById(R.id.cbxOlahraga);
        btnSimpan = findViewById(R.id.btnSimpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validasiForm()){
                    String namaKegiatan="";
                    String jenisKegiatan="";
                    String tempatKegiatan="";
                    namaKegiatan=edtNamaKegiatan.getText().toString();
                    if(rdbDalam.isChecked()) tempatKegiatan = rdbDalam.getText().toString();
                    else if(rdbLuar.isChecked()) tempatKegiatan = rdbLuar.getText().toString();

                    if(cbxBelajar.isChecked()) jenisKegiatan+=cbxBelajar.getText().toString()+"\n";
                    if(cbxMakan.isChecked()) jenisKegiatan+=cbxMakan.getText().toString()+"\n";
                    if(cbxJalan.isChecked()) jenisKegiatan+=cbxJalan.getText().toString()+"\n";
                    if(cbxOlahraga.isChecked()) jenisKegiatan+=cbxOlahraga.getText().toString()+"\n";

                    String pesan= "Nama Kegiatan : "+namaKegiatan + "\nTempat Kegiatan :"+tempatKegiatan + "\nJenis Kegiatan : "+jenisKegiatan;
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Tombol Register diTekan", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0,0);
                    toast.show();
                }
            }
        });
    }

    Boolean validasiForm() {
        if (edtNamaKegiatan.getText().toString().equals("")) {
            edtNamaKegiatan.setError("Nama Kegiatan Wajib Diisi!");
            return false;
        }
        else
            return true;

    }
}