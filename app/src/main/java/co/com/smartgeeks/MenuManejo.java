package co.com.smartgeeks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuManejo extends AppCompatActivity {


    Button btnPlagas, btnEnfermedades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_manejo);

        btnEnfermedades = (Button)findViewById(R.id.btnEnfermedades);
        btnPlagas = (Button)findViewById(R.id.btnPlagas);



        btnEnfermedades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuManejo.this, manejoFito.class);
                startActivity(intent);

            }
        });


        btnPlagas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuManejo.this, manejoFito.class);
                startActivity(intent);

            }
        });
    }
}
