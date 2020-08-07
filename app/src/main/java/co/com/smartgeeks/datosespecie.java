package co.com.smartgeeks;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class datosespecie extends AppCompatActivity {


    TextView tvEspecie, tvCodigo, tvSensorial, tvCondAtmo, tvIndProd, tvPotencial, tvVarFerm, tvBromatologico, tvFisicos,tvEdaficos,tvPropietario, tvFinca;
    ImageView ivFoto, ivFoto3, ivFoto4, ivFoto5, ivFoto6, ivSensorial;
    Button btnNuevaLectura, btnVerTodo;
    JSONArray ja;
    JSONObject jo;
    String url,idsinconex;
    String urlFoto;


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        // Get the layout inflater
        LayoutInflater inflater = datosespecie.this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.activity_menu, null))
                // Add action buttons
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("salir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datosespecie);
        ivFoto = (ImageView)findViewById(R.id.ivFoto);
        ivFoto3 = (ImageView)findViewById(R.id.ivFoto3);
        ivFoto4 = (ImageView)findViewById(R.id.ivFoto4);
        ivFoto5 = (ImageView)findViewById(R.id.ivFoto5);
        ivFoto6 = (ImageView)findViewById(R.id.ivFoto6);
        tvEspecie = (TextView)findViewById(R.id.textView);
        tvCodigo = (TextView)findViewById(R.id.textView2);
        tvSensorial = (TextView)findViewById(R.id.textView9);
        tvCondAtmo = (TextView)findViewById(R.id.textView25);
        tvIndProd = (TextView)findViewById(R.id.textView15);
        tvVarFerm = (TextView)findViewById(R.id.textView17);
        tvBromatologico = (TextView)findViewById(R.id.textView21);
        tvFisicos = (TextView)findViewById(R.id.textView23);
        tvPropietario = (TextView)findViewById(R.id.tvPropietaro);
        tvFinca = (TextView)findViewById(R.id.tvFinca);
        btnNuevaLectura = (Button)findViewById(R.id.btNuevaLectura);
        btnVerTodo = (Button)findViewById(R.id.btnVerTodo);
        tvEdaficos = (TextView)findViewById(R.id.textView32);
        ivSensorial = (ImageView)findViewById(R.id.ivSensorial);
        ivSensorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog;
                dialog = new Dialog(datosespecie.this);
                dialog.setContentView(R.layout.dialogimage);

                // set the custom dialog components - text, image and button
                ImageView imagen = (ImageView) dialog.findViewById(R.id.imageViewDialog);
                imagen.setImageDrawable(ivSensorial.getDrawable());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
            }
        });

        btnVerTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(datosespecie.this, Lista.class);
                startActivity(intent);
            }
        });
        ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog;
                dialog = new Dialog(datosespecie.this);
                dialog.setContentView(R.layout.dialogimage);

                // set the custom dialog components - text, image and button
                ImageView imagen = (ImageView) dialog.findViewById(R.id.imageViewDialog);
                imagen.setImageDrawable(ivFoto.getDrawable());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
            }
        });
        ivFoto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog;
                dialog = new Dialog(datosespecie.this);
                dialog.setContentView(R.layout.dialogimage);

                // set the custom dialog components - text, image and button
                ImageView imagen = (ImageView) dialog.findViewById(R.id.imageViewDialog);
                imagen.setImageDrawable(ivFoto3.getDrawable());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
            }
        });
        ivFoto4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog;
                dialog = new Dialog(datosespecie.this);
                dialog.setContentView(R.layout.dialogimage);

                // set the custom dialog components - text, image and button
                ImageView imagen = (ImageView) dialog.findViewById(R.id.imageViewDialog);
                imagen.setImageDrawable(ivFoto4.getDrawable());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
            }
        });
        ivFoto5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog;
                dialog = new Dialog(datosespecie.this);
                dialog.setContentView(R.layout.dialogimage);

                // set the custom dialog components - text, image and button
                ImageView imagen = (ImageView) dialog.findViewById(R.id.imageViewDialog);
                imagen.setImageDrawable(ivFoto5.getDrawable());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
            }
        });

        url = getIntent().getStringExtra("url");
        idsinconex = getIntent().getStringExtra("id");
        Log.i("valorid","aja"+idsinconex);

        //Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();

        btnNuevaLectura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(datosespecie.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        RequestQueue queue = Volley.newRequestQueue(this);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

                try {
                    ja = new JSONArray(response);
                    jo = ja.getJSONObject(0);
                    tvEspecie.setText(jo.getString("especie"));
                    tvCodigo.setText(jo.getString("codigo"));
                    tvCondAtmo.setText(jo.getString("condiciones"));
                    tvIndProd.setText(jo.getString("indices"));
                    tvVarFerm.setText(jo.getString("variables"));
                    tvBromatologico.setText(jo.getString("bromatologicos"));
                    tvFisicos.setText(jo.getString("fisicos"));
                    tvSensorial.setText(jo.getString("sensoriales"));
                    tvEdaficos.setText(jo.getString("edaficos"));
                    tvPropietario.setText("Propietario: "+jo.getString("propietario"));
                    tvFinca.setText("Finca: "+jo.getString("nombrefinca"));
                    Glide.with(getApplicationContext()).load(jo.getString("fotoplanta")).centerCrop().into(ivFoto);
                    Glide.with(getApplicationContext()).load(jo.getString("fotohoja")).centerCrop().into(ivFoto3);
                    Glide.with(getApplicationContext()).load(jo.getString("fotofruto")).centerCrop().into(ivFoto4);
                    Glide.with(getApplicationContext()).load(jo.getString("fotofrutoverde")).centerCrop().into(ivFoto5);
                    Glide.with(getApplicationContext()).load(jo.getString("fotograno")).centerCrop().into(ivFoto6);
                    Glide.with(getApplicationContext()).load(jo.getString("fotosensoriales")).into(ivSensorial);
                    urlFoto = jo.getString("fotosensoriales");
                    Log.i("valornombre",ja.getString(0));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "DataBase1").allowMainThreadQueries().build();

                Especies especie = db.especiesDao().findByName(idsinconex);
                tvEspecie.setText(especie.especie);
                tvCodigo.setText(especie.codigo);
                tvCondAtmo.setText(especie.condiciones);
                tvIndProd.setText(especie.indices);
                tvVarFerm.setText(especie.variables);
                tvBromatologico.setText(especie.bromatologicos);
                tvFisicos.setText(especie.fisicos);
                tvSensorial.setText(especie.sensoriales);
                tvEdaficos.setText(especie.edaficos);
                String fotoPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/qrspecies";
                Log.i("pathfoto",fotoPath+especie.fotoPlanta);
                Toast.makeText(getApplicationContext(),""+fotoPath+"/"+especie.fotoPlanta, Toast.LENGTH_LONG).show();
                ivFoto.setImageBitmap(BitmapFactory.decodeFile(fotoPath+"/"+especie.fotoPlanta));
                ivFoto3.setImageBitmap(BitmapFactory.decodeFile(fotoPath+"/"+especie.fotoHoja));
                ivFoto4.setImageBitmap(BitmapFactory.decodeFile(fotoPath+"/"+especie.fotoFruto));
                ivFoto5.setImageBitmap(BitmapFactory.decodeFile(fotoPath+"/"+especie.fotoFrutoVerde));
                ivFoto6.setImageBitmap(BitmapFactory.decodeFile(fotoPath+"/"+especie.fotoGrano));
                ivSensorial.setImageBitmap(BitmapFactory.decodeFile(fotoPath+"/"+especie.fotoSensoriales));





                Toast.makeText(getApplicationContext(),"Error de conexión",Toast.LENGTH_LONG).show();

            }
        });


        queue.add(stringRequest);


    }
}
