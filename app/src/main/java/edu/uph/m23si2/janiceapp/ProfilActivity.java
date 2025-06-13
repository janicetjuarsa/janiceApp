package edu.uph.m23si2.janiceapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfilActivity extends AppCompatActivity {
    Button btnSubmit;
    EditText edtNama, edtEmail;
    TextView txvHasil;
    Spinner sprProdi;
    RadioButton rdbPria, rdbWanita;
    RadioGroup rdgJenisKelamin;
    CheckBox ckbMakan, ckbTidur, ckbBelajar;

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

        init();

        edtNama.setText(getIntent().getStringExtra("nama"));
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                simpan();
            }
        });
    }

    public void init(){
        btnSubmit = findViewById(R.id.btnSubmit);
        edtNama = findViewById(R.id.edtNama);
        edtEmail = findViewById(R.id.edtEmail);
        txvHasil = findViewById(R.id.txvHasil);
        sprProdi = findViewById(R.id.sprProdi);
        rdgJenisKelamin = findViewById(R.id.rdgJenisKelamin);
        rdbPria = findViewById(R.id.rdbPria);
        rdbWanita = findViewById(R.id.rdbWanita);
        ckbBelajar = findViewById(R.id.ckbBelajar);
        ckbMakan = findViewById(R.id.ckbMakan);
        ckbTidur = findViewById(R.id.ckbTidur);

// Create an ArrayAdapter using the string array and a default spinner layout.
//        ArrayAdapter akan menyesuaikan datanya seperti prodi_array, dimana kode dibwh ini utk layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.prodi_array,
                android.R.layout.simple_spinner_item
        );
// Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
        sprProdi.setAdapter(adapter);
    }

    public boolean isValidated(){
        if(edtNama.getText().toString().equals("")){
            edtNama.setError("Nama wajib di isi");
            return false;
        }
        else if(edtEmail.getText().toString().equals("")) {
            edtEmail.setError("Email wajib di isi");
            return false;
        }
        return true;
    }

    public void simpan(){
        if (isValidated()) {
        // Menampilkan hasil di txvHasil
        String nama = edtNama.getText().toString();
//                Mengambil nama dari edtNama dan ganti ke string dulu
        String prodi = sprProdi.getSelectedItem().toString();
        String email = edtEmail.getText().toString();
        String jenisKelamin = "";
        String hobi = "";
        if(rdbWanita.isChecked()) jenisKelamin=rdbWanita.getText().toString();
        else if(rdbPria.isChecked()) jenisKelamin=rdbPria.getText().toString();

        if(ckbTidur.isChecked()) hobi+=ckbTidur.getText().toString()+"; ";
        if(ckbMakan.isChecked()) hobi+=ckbMakan.getText().toString()+";" ;
        if(ckbBelajar.isChecked()) hobi+=ckbBelajar.getText().toString()+"; ";

        txvHasil.setText(nama + "("+jenisKelamin+")"+ "\nHobi"+hobi +"\n"+ email+ "\nProgram Studi "+ prodi+ "\n" + getNamaFakultas(prodi));
        }
    }
    String getNamaFakultas(String prodi) {
        String namaProdi = prodi.toLowerCase();
        if (namaProdi.equals("sistem informasi") || namaProdi.equals("informatika")) {
            return "Fakultas Teknologi Informasi";
        } else if (namaProdi.equals("hukum") || namaProdi.equals("law")) {
            return "Fakultas Hukum";
        } else if (namaProdi.equals("akuntansi") || namaProdi.equals("manajemen") ||
                namaProdi.equals("perhotelan")) {
            return "Fakultas Ekonomi dan Bisnis";
        } else
            return "Fakultas Tidak diTemukan";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.
        if (item.getItemId()==R.id.mSimpan){
            simpan();
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}