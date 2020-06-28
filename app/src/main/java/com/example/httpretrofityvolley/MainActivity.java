package com.example.httpretrofityvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText pass;
    private  EditText user;
    private TextView lista;
    private ProgressDialog progreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText)findViewById(R.id.txtusuario);
        pass=(EditText)findViewById(R.id.txtpass);
        lista=(TextView)findViewById(R.id.txtlistabancos);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();
        Retrofit retro= new Retrofit.Builder().baseUrl("https://api-uat.kushkipagos.com/transfer-subscriptions/v1/").addConverterFactory(GsonConverterFactory.create()).build();
        servicesBancos serv= retro.create(servicesBancos.class);
        Call<List<Datos>> call= serv.getusersGet("84e1d0de1fbf437e9779fd6a52a9ca18");
        call.enqueue(new Callback<List<Datos>>() {
            @Override
            public void onResponse(Call<List<Datos>> call, Response<List<Datos>> response) {
                List<Datos> data=response.body();
                String lstBancos="";
                for(Datos dt:data){
                    lstBancos = lstBancos + "\n" + dt.getName();
                }
                lista.setText("Respuesta WS Lista de Bancos" +  lstBancos);
                progreso.hide();
            }

            @Override
            public void onFailure(Call<List<Datos>> call, Throwable t) {
            progreso.hide();
            }
        });

    }
    public void Enviar(View view)
    {
        Intent segundo=new Intent(this,ValidacionLogin.class);
        segundo.putExtra("Usuario" , user.getText().toString().trim());
        segundo.putExtra("Clave",pass.getText().toString().trim());
        startActivity(segundo);
    }
}