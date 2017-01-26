package com.example.juangajardo.solemneappanuncios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class PublicarActivity extends AppCompatActivity {

    EditText titProd, precProd, descProd, rut, clave;
    Button btn_PublicarDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar);

        btn_PublicarDatos = (Button) findViewById(R.id.btn_PublicarDatos);

        btn_PublicarDatos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                validaPublicaAnuncio();
            }
        });
    }

    public void validaPublicaAnuncio(){

        titProd = (EditText)findViewById(R.id.tit_prod);
        precProd = (EditText)findViewById(R.id.prec_prod);
        descProd = (EditText)findViewById(R.id.desc_prod);
        rut = (EditText)findViewById(R.id.rut);
        clave = (EditText)findViewById(R.id.clave);

        String titProdString = titProd.getText().toString();
        String precProdString = precProd.getText().toString();
        String descProdString = descProd.getText().toString();
        String rutString = rut.getText().toString();
        String claveString = clave.getText().toString();

        if(titProdString.isEmpty() || precProdString.isEmpty() || descProdString.isEmpty() || rutString.isEmpty() || claveString.isEmpty()){
            Toast.makeText(this,"Rellene todos los campos",Toast.LENGTH_SHORT).show();
        }else{
            enviardatos(titProdString, precProdString, descProdString, rutString, claveString);
        }


    }

    public void enviardatos(String tituloProducto, String precioProducto, String descripcionProducto, String rut, String clave ){


        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://anunwebservice.96.lt/wsphpandroid/insertarAnuncio.php";
        String parametros = "?titProducto=" + tituloProducto + "&preProducto=" + precioProducto+ "&descProducto=" + descripcionProducto+"&rutUsuario=" + rut + "&claveUsuario=" + clave;
        client.post(url + parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    String resultado = new String(responseBody);
                    if(resultado.isEmpty()){
                        Toast.makeText(PublicarActivity.this, "Error al registrar Anuncio", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(PublicarActivity.this, "Anuncio Registrado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PublicarActivity.this, EliminarProdActivity.class);
                        startActivity(intent);
                    }


                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String resultado = new String(responseBody);
                Toast.makeText(PublicarActivity.this, "Error al registrar anuncio", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
