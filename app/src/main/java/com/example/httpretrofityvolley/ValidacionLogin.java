package com.example.httpretrofityvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ValidacionLogin extends AppCompatActivity {
    private TextView respuesta;
    private ProgressDialog progreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacion_login);
        respuesta=(TextView)findViewById(R.id.lblrespuesta);
        String user=getIntent().getStringExtra("Usuario");
        String pass=getIntent().getStringExtra("Clave");
        RequestQueue request = Volley.newRequestQueue(this);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Verificando...");
        progreso.show();
        StringRequest volley=new StringRequest(Request.Method.GET, "http://uealecpeterson.net/ws/login.php?usr=" + user + "&pass=" + pass, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                respuesta.setText("Respuesta del WS: " +response.toString());
                progreso.hide();
            } }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                respuesta.setText(" Error "+error.toString());
                progreso.hide();
            }
        });
        request.add(volley);

    }
}