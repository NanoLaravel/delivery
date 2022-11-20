package com.herfan.delivery;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.squareup.picasso.Picasso;

import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class DatosUsuario extends AppCompatActivity {
    CircleImageView img;
    TextView tv1, tv2,tvLatitud, tvLongitud;
    Button btnUbicacion;
    private FusedLocationProviderClient fusedLocationClient;
    protected Location ultimaUbicacion;

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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

       /* btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DatosUsuario.this, MapsActivity.class);
                startActivity(i);
            }
        });*/

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
                       // ultimaUbicacion = location.getLatitude();
                        //tvLatitud.setText(String.format(Locale.ENGLISH, "%s%f","",ultimaUbicacion.getLatitude()));
                        //tvLongitud.setText(String.format(Locale.ENGLISH,"%s%f","", ultimaUbicacion.getLongitude()));
                        tvLatitud.setText(String.format(Locale.ENGLISH, "%s%f","", location.getLatitude()));
                        tvLongitud.setText(String.format(Locale.ENGLISH,"%s%f","", location.getLongitude()));

                    }
                });

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





    public void cerrarSesion(View view){
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}

