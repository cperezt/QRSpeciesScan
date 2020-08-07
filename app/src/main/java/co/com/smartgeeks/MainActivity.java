package co.com.smartgeeks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.vision.barcode.Barcode;
import com.notbytes.barcode_reader.BarcodeReaderActivity;
import com.notbytes.barcode_reader.BarcodeReaderFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BarcodeReaderFragment.BarcodeReaderListener, View.OnClickListener {

    private static final int BARCODE_READER_ACTIVITY_REQUEST = 1208;
    private TextView mTvResult;
    private TextView mTvResultHeader;
    String url, idsinconex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_fragment).setOnClickListener(this);
        mTvResult = findViewById(R.id.tv_result);
        mTvResultHeader = findViewById(R.id.tv_result_head);
    }

    private void addBarcodeReaderFragment() {
        BarcodeReaderFragment readerFragment = BarcodeReaderFragment.newInstance(true, false, View.VISIBLE);
        readerFragment.setListener(this);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fm_container, readerFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }



    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(this, "error in  scanning", Toast.LENGTH_SHORT).show();
            return;
        }

        if (requestCode == BARCODE_READER_ACTIVITY_REQUEST && data != null) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);

            //Toast.makeText(this, "hola mundo"+barcode.rawValue, Toast.LENGTH_SHORT).show();
            mTvResultHeader.setText("On Activity Result");
            mTvResult.setText("erro");





        }

    }


    @Override
    public void onScanned(Barcode barcode) {
        //Toast.makeText(this, barcode.rawValue, Toast.LENGTH_SHORT).show();
        url ="https://smartgeeks.com.co/SpeciesQrScanner/getData.php?id="+barcode.rawValue;
        idsinconex = ""+barcode.rawValue;
        mTvResultHeader.setText("Id Especie Vegetal");
        mTvResult.setText(barcode.rawValue);

        Log.i("urlsel",url);
        Log.i("idesl",idsinconex);

        RequestQueue queue = Volley.newRequestQueue(this);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("[]")){

                    Toast.makeText(getApplicationContext(),"No se encuentra la especie vegetal seleccionada",Toast.LENGTH_LONG).show();
                }else{
                    //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    Log.i("correcto","ok"+response);
                    Intent intent = new Intent(MainActivity.this, datosespecie.class);
                    intent.putExtra("url",url);
                    startActivity(intent);
                    finish();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"No existe conexion a red, se cargaran datos locales",Toast.LENGTH_LONG).show();
                Log.i("incorrecto","ok");
                Intent intent = new Intent(MainActivity.this, datosespecie.class);
                intent.putExtra("url",url);
                Log.i("idsinco","sd"+idsinconex);
                intent.putExtra("id",idsinconex);
                startActivity(intent);
            }
        });


        queue.add(stringRequest);

    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(this, "Camera permission denied!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_fragment:
                addBarcodeReaderFragment();
                break;

        }

    }
}
