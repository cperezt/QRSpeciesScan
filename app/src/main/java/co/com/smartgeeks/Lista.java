package co.com.smartgeeks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lista extends AppCompatActivity {

    RecyclerView rv;
    JSONObject jo;
    JSONArray ja;
    ArrayList<ElementosLista> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lista = new ArrayList();

        rv = (RecyclerView)findViewById(R.id.my_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        String url = "https://smartgeeks.com.co/SpeciesQrScanner/getAllData.php";

        RequestQueue queue = Volley.newRequestQueue(this);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("cacao2",response);
                try {
                    ja = new JSONArray(response);
                    for (int i=0; i<ja.length();i++){
                        jo = ja.getJSONObject(i);
                        Log.i("vuelta",""+i);

                        lista.add(new ElementosLista(Integer.parseInt(jo.getString("id")),jo.getString("fotofruto"),jo.getString("especie")+ " "+jo.getString("codigo")));

                    }

                    MiAdaptador miAdaptador = new MiAdaptador(lista);
                    rv.setAdapter(miAdaptador);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"No se encuentra la especie vegetal seleccionada",Toast.LENGTH_LONG).show();
                Log.i("incorrecto","ok");
            }
        });


        queue.add(stringRequest);

        //lista = Arrays.asList(new ElementosLista(1,R.drawable.fruto,"Theobroma Cacao D34"), new ElementosLista(1,R.drawable.frutocacao,"Theobroma Cacao D35"), new ElementosLista(1,R.drawable.frutocacao,"Theobroma Cacao D36"));

    }
}
