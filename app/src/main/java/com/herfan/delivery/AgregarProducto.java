package com.herfan.delivery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class AgregarProducto extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("productos");

    FirebaseStorage storage;
    StorageReference storageReference;

    private final int PICK_IMAGE_REQUEST = 22;
    //ControladorProductos controladorProductos;
    Button agregar, agregarImagen;
    EditText etnombreProducto, etprecioProducto;
    ImageView imagenProducto;
    private Uri ubicacionImagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);
        agregar = findViewById(R.id.btn1);
        //controladorProductos = new ControladorProductos(getApplicationContext());
        etnombreProducto = findViewById(R.id.etnombreProducto);
        etprecioProducto = findViewById(R.id.etprecioProducto);
        agregarImagen = findViewById(R.id.btnAgregarImagen);
        imagenProducto = findViewById(R.id.imageView);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        agregarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();
            }
        });

        imagenProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarImagen();
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etnombreProducto.getText().toString();
                String precio = etprecioProducto.getText().toString();
                Log.d("prueba", "" + precio);


                Producto p = new Producto();
                p.setNombre(nombre);
                p.setPrecio(precio);


                //controladorProductos.agregarProducto(p);
                myRef.child(nombre).setValue(p);//myRef.push().set...

                Toast.makeText(getApplicationContext(), "El producto se agrego con exito", Toast.LENGTH_LONG).show();
                etnombreProducto.setText("");
                etprecioProducto.setText("");




            }
        });

    }

    public void seleccionarImagen(){

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,"Seleccione la imagen"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK && data != null
                && data.getData() != null) {
            ubicacionImagen = data.getData();

            try{
                Bitmap imagen = MediaStore.Images.Media.getBitmap(getContentResolver(), ubicacionImagen);
                imagenProducto.setImageBitmap(imagen);

            }catch(IOException e){
                e.printStackTrace();

            }

        }

    }


    public void cargarImagen(){

        if(ubicacionImagen != null){

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Cargando");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(ubicacionImagen).addOnSuccessListener(
                    new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(AgregarProducto.this, "Se cargo la imagen correctamente", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
            );


        }
    }

}
