package com.example.tarea2consumojson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //REALIZADO POR: GEOVANNY OCHOA

        Map<String, String> Usuarios = new HashMap<String, String>();
        WebService ws= new WebService("https://dummyjson.com/users", Usuarios,
                MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        Log.i("Resultado",result);
        TextView txtDatosUsers = (TextView) findViewById(R.id.txtDatos);
        String lstUsers="";
        //Guardo en un objeto tipo Json y despues extraigo sus datos.
        JSONObject JObjeto= new JSONObject(result);
        JSONArray JSONlista = JObjeto.getJSONArray("users");

        for(int i=0; i< JSONlista.length();i++){
            JSONObject user= JSONlista.getJSONObject(i);
            lstUsers = lstUsers + "\n" +
                    user.getString("id").toString()+" | "
                    +user.getString("firstName").toString()+" "
                    +user.getString("lastName").toString()+" | "
                    +user.getString("age").toString()+" | "
                    +user.getString("email").toString()+" | "
                    +user.getString("gender").toString();
        }
        txtDatosUsers.setText("Lista de Usuarios" + lstUsers);
    }
}