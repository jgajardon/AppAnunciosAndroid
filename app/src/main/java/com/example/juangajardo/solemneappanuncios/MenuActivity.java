package com.example.juangajardo.solemneappanuncios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button btn_Registrarse, btn_Publicar, btn_BuscarProd, btn_EliminarProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_Registrarse = (Button) findViewById(R.id.btn_Registrarse);
        btn_Publicar = (Button) findViewById(R.id.btn_Publicar);
        btn_BuscarProd = (Button) findViewById(R.id.btn_BuscarProd);
        btn_EliminarProd = (Button) findViewById(R.id.btn_EliminarProd);

        btn_Registrarse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),RegistrarActivity.class));
            }
        });
        btn_Publicar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),PublicarActivity.class));
            }
        });
        btn_BuscarProd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),BuscarProdActivity.class));
            }
        });
        btn_EliminarProd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),EliminarProdActivity.class));
            }
        });
    }




}
