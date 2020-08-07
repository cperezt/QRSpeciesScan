package co.com.smartgeeks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class  menu extends AppCompatActivity {

    Button btnScanner, btnVer, btnManejo, btnSincronizar, btnCreditos, btnEnlaces;
    ProgressBar progressBar;
    ArrayList<String> listaFotoHoja = new ArrayList<>();
    ArrayList<String> listaFotoFruto = new ArrayList<>();
    ArrayList<String> listaFotoFrutoVerde = new ArrayList<>();
    ArrayList<String> listaFotoPlanta = new ArrayList<>();
    ArrayList<String> listaFotoGrano = new ArrayList<>();
    ArrayList<String> listaFotoSesorial = new ArrayList<>();
    Bitmap bm = BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.bottom_bar);
    int contador = 0;
    JSONArray ja;
    JSONObject jo;
    AppDatabase db;
    int j=0;
    int k=0;
    int l=0;
    int m=0;
    int n=0;
    int o=0;
    int x=0;
    int progress=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnManejo = (Button)findViewById(R.id.btnManejo);
        btnScanner = (Button)findViewById(R.id.btnScanner);
        btnEnlaces = (Button)findViewById(R.id.btnDocumentos);
        btnSincronizar = findViewById(R.id.btnSincronizar);
        btnCreditos =findViewById(R.id.btnCreditos);
        progressBar = findViewById(R.id.progressBar);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "DataBase1").allowMainThreadQueries().build();

        btnCreditos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, Creditos.class);
                startActivity(intent);
            }
        });

        btnSincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(0);
                Toast.makeText(getApplicationContext(),"Sincronizando",Toast.LENGTH_SHORT).show();
                if (ContextCompat.checkSelfPermission(menu.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Permission is not granted
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(menu.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                    } else {
                        // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(menu.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                0);

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                } else {
                    // Permission has already been granted
                    db.especiesDao().nukeTable();
                    listaFotoHoja.removeAll(listaFotoHoja);
                    listaFotoFruto.removeAll(listaFotoFruto);
                    listaFotoFrutoVerde.removeAll(listaFotoFrutoVerde);
                    listaFotoGrano.removeAll(listaFotoGrano);
                    listaFotoPlanta.removeAll(listaFotoPlanta);
                    listaFotoSesorial.removeAll(listaFotoSesorial);

                    Toast.makeText(getApplicationContext(),"sincronizando",Toast.LENGTH_LONG).show();
                    String url = "https://smartgeeks.com.co/SpeciesQrScanner/getAllData.php";

                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());



                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("cacao",response);
                            try {
                                ja = new JSONArray(response);

                                for (int i=0; i<ja.length();i++){
                                    contador = i+1;
                                    jo = ja.getJSONObject(i);
                                    //Especies especies = new Especies(jo.getInt("id"),jo.getString("especie"),jo.getString("fotohoja"),jo.getString("condiciones"));
                                    Especies especies = new Especies();
                                    especies.setIdEspecie(jo.getInt("id"));
                                    especies.setEspecie(jo.getString("especie"));
                                    especies.setCodigo(jo.getString("codigo"));
                                    especies.setFotoHoja("hoja_"+(i+1)+".jpg");
                                    especies.setFotoFruto("fruto_"+(i+1)+".jpg");
                                    especies.setFotoFrutoVerde("frutoverde_"+(i+1)+".jpg");
                                    especies.setFotoPlanta("planta_"+(i+1)+".jpg");
                                    especies.setFotoGrano("grano_"+(i+1)+".jpg");
                                    especies.setFotoSensoriales("sensorial_"+(i+1)+".jpg");
                                    especies.setCondiciones(jo.getString("condiciones"));
                                    especies.setEdaficos(jo.getString("edaficos"));
                                    especies.setIndices(jo.getString("indices"));
                                    especies.setVariables(jo.getString("variables"));
                                    especies.setBromatologicos(jo.getString("bromatologicos"));
                                    especies.setFisicos(jo.getString("fisicos"));
                                    especies.setSensoriales(jo.getString("sensoriales"));
                                    listaFotoHoja.add(jo.getString("fotohoja"));
                                    listaFotoFruto.add(jo.getString("fotofruto"));
                                    listaFotoFrutoVerde.add(jo.getString("fotofrutoverde"));
                                    listaFotoGrano.add(jo.getString("fotograno"));
                                    listaFotoPlanta.add(jo.getString("fotoplanta"));
                                    listaFotoSesorial.add(jo.getString("fotosensoriales"));
                                    Long respuesta  = db.especiesDao().insert(especies);

                                    if(respuesta>0){

                                    }else{
                                        Toast.makeText(getApplicationContext(),"Error en la sincronizacion",Toast.LENGTH_SHORT).show();
                                    }
                                }

                                GuardarFotos(listaFotoHoja,"hoja");
                                GuardarFotos(listaFotoFruto,"fruto");
                                GuardarFotos(listaFotoFrutoVerde, "frutoverde");
                                GuardarFotos(listaFotoGrano,"grano");
                                GuardarFotos(listaFotoPlanta,"planta");
                                GuardarFotos(listaFotoSesorial,"sensorial");
                                //Toast.makeText(getApplicationContext(),"Sincronizacion Finalizada",Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(getApplicationContext(),"No se encuentra "+error.toString(),Toast.LENGTH_LONG).show();
                            Log.i("incorrecto","ok"+error.toString());
                        }
                    });


                    queue.add(stringRequest);
                }

            }
        });

        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, MainActivity.class);
                startActivity(intent);

            }
        });

        btnManejo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, MenuManejo.class);
                startActivity(intent);

            }
        });

        btnEnlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu.this, Documentos.class);
                startActivity(intent);
            }
        });
    }

    private void GuardarFotos(final ArrayList listaFotos, final String tipoFoto) {

        if(tipoFoto.equals("hoja"))x=j;
        if(tipoFoto.equals("fruto"))x=k;
        if(tipoFoto.equals("frutoverde"))x=l;
        if(tipoFoto.equals("grano"))x=m;
        if(tipoFoto.equals("planta"))x=n;
        if(tipoFoto.equals("sensorial"))x=o;

        GlideApp.with(getApplicationContext())
                .asBitmap()
                .load(listaFotos.get(x))
                .into(new CustomTarget<Bitmap>(500,500) {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        saveImage(resource, tipoFoto+"_"+x, listaFotos, tipoFoto);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                        saveImage(bm, tipoFoto+"_"+x, listaFotos, tipoFoto);
                    }
                });


    }

    private String saveImage(Bitmap image, String name, ArrayList lista, String tipoFoto) {

        String savedImagePath = null;

        String imageFileName = name + ".jpg";
        //Log.i("aja",""+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/qrspecies");
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/qrspecies");
        boolean success = true;
        try{
            if (!storageDir.exists()) {

                success = storageDir.mkdirs();
                Log.i("mensaje",""+success);

            }
        }catch (Exception e){
            Log.w("creating file error", e.toString());

        }
        if (success) {
            File imageFile = new File(storageDir, imageFileName);
            savedImagePath = imageFile.getAbsolutePath();
            Log.i("mensaje",imageFileName);
            try {
                OutputStream fOut = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Add the image to the system gallery
            //galleryAddPic(savedImagePath);
            //Toast.makeText(getApplicationContext(), "IMAGE SAVED", Toast.LENGTH_LONG).show();
            if (tipoFoto.equals("hoja")){
                if(j<lista.size()-1){
                    j++;
                    Log.i("j",""+j);
                    GuardarFotos(lista,tipoFoto);
                }else{
                    j=0;

                }
            }
            if(tipoFoto.equals("fruto")){
                if(k<lista.size()-1){
                    k++;
                    Log.i("k",""+k);
                    GuardarFotos(lista,tipoFoto);
                }else{
                    k=0;

                }
            }
            if(tipoFoto.equals("frutoverde")){
                if(l<lista.size()-1){
                    l++;
                    Log.i("l",""+l);
                    GuardarFotos(lista,tipoFoto);
                }else{
                    l=0;

                }
            }
            if(tipoFoto.equals("grano")){
                if(m<lista.size()-1){
                    m++;
                    Log.i("m",""+m);
                    GuardarFotos(lista,tipoFoto);
                }else{
                    m=0;

                }
            }
            if(tipoFoto.equals("planta")){
                if(n<lista.size()-1){
                    n++;
                    Log.i("n",""+n);
                    GuardarFotos(lista,tipoFoto);
                }else{
                    n=0;

                }
            }
            if(tipoFoto.equals("sensorial")){
                if(o<lista.size()-1){
                    o++;
                    progress = o*100/lista.size()+1;
                    progressBar.setProgress(progress);
                    Log.i("o",""+o);
                    GuardarFotos(lista,tipoFoto);

                }else{
                    o=0;
                    Toast.makeText(getApplicationContext(),"Sincronizacion finalizada",Toast.LENGTH_SHORT).show();

                }
            }

        }
        return savedImagePath;
    }
}
