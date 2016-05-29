package com.oswal.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class Form extends AppCompatActivity implements fill{

     Button mBack, mGuardar;
    private static final String URL_BASE = "http://192.168.56.1:8005/tareas/?format=json";
     EditText Titulo, Descripcion;
     Spinner Categoria;
    private RequestQueue requestQueue;
    JsonObjectRequest jsArrayRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        mBack = (Button)findViewById(R.id.salir);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fin();
            }
        });

        Titulo = (EditText)findViewById(R.id.titulo);
        Descripcion = (EditText)findViewById(R.id.descripcion);
        Categoria = (Spinner)findViewById(R.id.spinner);

        mGuardar = (Button)findViewById(R.id.guardar);
        mGuardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String desc, titulo;
                desc = Descripcion.getText().toString();
                titulo = Titulo.getText().toString();
                if (fill(desc) && fill(titulo) && Methods.HayInternet(v.getContext())){
                    requestQueue= Volley.newRequestQueue(v.getContext());
                    // Mapeo de los pares clave-valor
                    HashMap<String, String> parametros = new HashMap();
                    parametros.put("titulo", titulo);
                    parametros.put("descripcion", desc);
                    parametros.put("categoria", "5");

                    JsonObjectRequest jsArrayRequest = new JsonObjectRequest(
                            Request.Method.POST,
                            URL_BASE,
                            new JSONObject(parametros),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // Manejo de la respuesta
                                    Toast.makeText(Form.this, "la data fue enviada"+response, Toast.LENGTH_LONG).show();
                                    fin();
                                }
                            },
                            new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Manejo de errores

                                }
                            });

                    requestQueue.add(jsArrayRequest);
                }
            }
        });

    }

    @Override
    public boolean fill(String c) {
        if (c.length() > 0)
            return true;
        return false;
    }


    public void fin(){
        finish();
        Intent intent = new Intent(Form.this, MainActivity.class);
        startActivity(intent);
    }
}
