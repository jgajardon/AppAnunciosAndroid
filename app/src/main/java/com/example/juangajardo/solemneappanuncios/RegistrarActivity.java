package com.example.juangajardo.solemneappanuncios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class RegistrarActivity extends AppCompatActivity {

    EditText rut, nombre, apellido, email, fono, clave;
    Button btn_EnviarDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        btn_EnviarDatos = (Button) findViewById(R.id.btn_EnviarDatos);

        btn_EnviarDatos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                validaEnviaDatos();
            }
        });
    }

    public void validaEnviaDatos(){

        rut = (EditText)findViewById(R.id.rut);
        nombre = (EditText)findViewById(R.id.nombre);
        apellido = (EditText)findViewById(R.id.apellido);
        email = (EditText)findViewById(R.id.email);
        fono = (EditText)findViewById(R.id.fono);
        clave = (EditText)findViewById(R.id.clave);

        String rut_string=rut.getText().toString();
        String nombre_string=nombre.getText().toString();
        String apellido_string=apellido.getText().toString();
        String email_string=email.getText().toString();
        String fono_string=fono.getText().toString();
        String clave_string=clave.getText().toString();

        if(rut_string.isEmpty() || nombre_string.isEmpty() || apellido_string.isEmpty() || email_string.isEmpty() || fono_string.isEmpty() || clave_string.isEmpty()){
            Toast.makeText(this,"Rellene todos los campos",Toast.LENGTH_SHORT).show();
        }else{
            enviardatos(rut_string,nombre_string,apellido_string,email_string,fono_string,clave_string);
        }

    }

    public void enviardatos(String rut, String nombre, String apellido, String email, String fono, String clave ){


        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://anunwebservice.96.lt/wsphpandroid/insertarUsuario.php";
        String parametros = "?rut=" + rut + "&nombre=" + nombre + "&apellido=" + apellido + "&email=" + email + "&fono=" + fono + "&clave="+ clave;
        client.post(url + parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    String resultado = new String(responseBody);
                    Toast.makeText(RegistrarActivity.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrarActivity.this, MenuActivity.class);
                    startActivity(intent);

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String resultado = new String(responseBody);
                Toast.makeText(RegistrarActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
