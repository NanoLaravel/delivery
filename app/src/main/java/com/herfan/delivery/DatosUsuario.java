package com.herfan.delivery;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DatosUsuario extends AppCompatActivity {
    CircleImageView img;
    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_usuario);

        img = findViewById(R.id.img);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

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

    public void cerrarSesion(View view){
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}

