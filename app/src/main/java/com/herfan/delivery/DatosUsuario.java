package com.herfan.delivery;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Locale;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class DatosUsuario extends AppCompatActivity {
    CircleImageView img;
    TextView tv1, tv2,tvLatitud, tvLongitud;
    Button btnUbicacion, btnGuardarUbicacion, btnVerUbicacionMapa;
    private FusedLocationProviderClient fusedLocationClient;
    protected Location ultimaUbicacion;



    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("usuarios");

    //FirebaseStorage storage;
    //StorageReference storageReference;

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_usuario);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        img = findViewById(R.id.img);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tvLatitud = findViewById(R.id.tvLatitud);
        tvLongitud = findViewById(R.id.tvLongitud);
        btnUbicacion = findViewById(R.id.btnUbicacionUsuario);
        btnGuardarUbicacion = findViewById(R.id.btnGuardarUbicacion);
        btnVerUbicacionMapa= findViewById(R.id.btnVerUbicacionMapa);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

       /* btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DatosUsuario.this, MapsActivity.class);
                startActivity(i);
            }
        });*/

        btnGuardarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarUbicacion();
            }
        });

        btnVerUbicacionMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verUbicacionMapa();
            }
        });

        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ubicacion();
            }
        });

        if (user != null){
            String name = user.getDisplayName();
            String gmail = user.getEmail();

            tv1.setText("Usuario: " + name);
            tv2.setText("Email: " + gmail);

            Picasso.get().load(user.getPhotoUrl()).placeholder(R.drawable.ic_launcher_background).into(img);
        }
        else{
            getApplicationContext();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if (!checkPermissions()) {
            requestPermissions();
        } else {
             //ubicacion();
        }
    }

    @SuppressWarnings("MissingPermission")
    public void ubicacion() {

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        ultimaUbicacion = location;
                        if (location != null) {
                            tvLatitud.setText(String.format(Locale.ENGLISH, "%s%f", "", ultimaUbicacion.getLatitude()));
                            tvLongitud.setText(String.format(Locale.ENGLISH, "%s%f", "", ultimaUbicacion.getLongitude()));

                        }else{
                            Toast.makeText(DatosUsuario.this, "por favor activa la ubicacion por gps de tu dispositivo y reinicia la app", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    public void  verUbicacionMapa(){
        double latitud =  ultimaUbicacion.getLatitude();
        double longitud = ultimaUbicacion.getLongitude();
        //Uri ubicacionMapa = Uri.parse("geo:" + latitud + "," + longitud);
        //Intent i = new Intent(Intent.ACTION_VIEW, ubicacionMapa);
        //i.setPackage("com.google.android.apps.maps");

        Intent intent = new Intent(DatosUsuario.this, MapsActivity.class);

        intent.putExtra("latitud", latitud);
        intent.putExtra("longitud", longitud);
        startActivity(intent);
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(DatosUsuario.this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION);

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {


        } else {

            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            startLocationPermissionRequest();
        }
    }



    public void cargarUbicacion(){

        if(ultimaUbicacion != null){
            double latitud = Double.parseDouble(tvLatitud.getText().toString());
            double longitud = Double.parseDouble(tvLongitud.getText().toString());
            String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Cargando");
            progressDialog.show();

           /* myRef = FirebaseDatabase.getInstance().getReference().child("Usuario");
            DatabaseReference currentUserDB = myRef.child(mAuth.getCurrentUser().getUid());
            currentUserDB.child("uid").setValue(user_id);*/


            myRef = database.getReference().child("usuario");
            myRef.setValue("latitud:" +latitud+ " longitud:"+ longitud);

            progressDialog.dismiss();
            Toast.makeText(DatosUsuario.this, "Se cargo la ubicacion correctamente en basede datos", Toast.LENGTH_LONG)
                    .show();


        }else{
            Toast.makeText(DatosUsuario.this, "primero debe obtener la ubicacion", Toast.LENGTH_LONG)
                    .show();
        }
    }







    public void cerrarSesion(View view){
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}

