package co.com.smartgeeks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

public class Documentos extends AppCompatActivity {

    RecyclerView rvListaArticulos;
    ArrayList<ElementosEnlaces> listaElementos;
    JSONArray ja;
    JSONObject jo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos);

        listaElementos = new ArrayList<>();
        rvListaArticulos = (RecyclerView)findViewById(R.id.my_recycler_view2);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaArticulos.setLayoutManager(llm);

        String url = "https://smartgeeks.com.co/SpeciesQrScanner/getArticles.php";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(0, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ja = new JSONArray(response);
                    for (int i=0; i<ja.length();i++){
                        jo = ja.getJSONObject(i);
                        listaElementos.add(new ElementosEnlaces("https://smartgeeks.com.co/species/public/common/imgEnlaces/"+jo.getString("foto"),jo.getString("descripcion"),jo.getString("titulo"),jo.getString("enlace")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                AdaptadorEnlaces adaptadorEnlaces = new AdaptadorEnlaces(listaElementos);
                rvListaArticulos.setAdapter(adaptadorEnlaces);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);





    }
}