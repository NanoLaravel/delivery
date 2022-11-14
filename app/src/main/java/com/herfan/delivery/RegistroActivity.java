package com.herfan.delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;
    private FirebaseDatabase database= FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("usuarios");

    EditText etNombreRegistro, etEmailRegistro, etContrasenaRegistro;
    Button btnRegistrarBD;
    Button btnIrLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnRegistrarBD = findViewById(R.id.btnRegistrarBD);
        btnIrLogin = findViewById(R.id.btnIrLogin);
        etNombreRegistro = findViewById(R.id.etNombreRegistro);
        etEmailRegistro = findViewById(R.id.etEmailRegistro);
        etContrasenaRegistro = findViewById(R.id.etContrasenaRegistro);
        myAuth = FirebaseAuth.getInstance();
        myAuth.signOut();

        btnRegistrarBD.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick (View view){

        String nombre = etNombreRegistro.getText().toString();
        String email = etEmailRegistro.getText().toString();
        String password = etContrasenaRegistro.getText().toString();


        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setCorreo(email);
        u.setPassword(password);

        registrar(u);

    }

    });
}


        @Override
        public void onStart() {
            super.onStart();
            // Check if user is signed in (non-null) and update UI accordingly.
            FirebaseUser currentUser = myAuth.getCurrentUser();
            if (currentUser != null){
                  Intent i = new Intent(RegistroActivity.this, HomeActivity2.class);
                  startActivity(i);
            }
        }

    public void registrar(Usuario u){

        myAuth.createUserWithEmailAndPassword(u.getCorreo(), u.getPassword())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = myAuth.getCurrentUser();
                        String id = user.getUid();
                        u.setId(id);
                        Log.d("prueba", u.toString());
                        myRef.child(id).setValue(u);
                        actualizarUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(RegistroActivity.this, "Fallo el registro.",
                                Toast.LENGTH_SHORT).show();
                        actualizarUI(null);
                    }
                });


    }

    public void actualizarUI(FirebaseUser usuario){

        if(usuario != null){
            Intent i = new Intent(RegistroActivity.this, HomeActivity2.class);
            startActivity(i);
        }else{
            Toast.makeText(RegistroActivity.this, "Ingrese sus datos de registro", Toast.LENGTH_LONG).show();
        }

    }

    }
