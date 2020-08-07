package co.com.smartgeeks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class manejoFito extends AppCompatActivity {

    TextView tvTitulo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manejo_fito);

        tvTitulo1 = findViewById(R.id.tvTitulo1);


    }
}
