package com.example.juangajardo.solemneappanuncios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class BuscarProdActivity extends AppCompatActivity {

    ListView listado;
    EditText palabra;
    Button btn_buscarProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_prod);

        btn_buscarProd = (Button) findViewById(R.id.btn_BuscarProd);
        listado = (ListView)findViewById(R.id.listView) ;

        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                Intent Detalle = new Intent(v.getContext(), DetalleActivity.class);
                startActivity(Detalle);
            }
        });

        btn_buscarProd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                validaPalabra();
            }
        });
    }

    public void validaPalabra(){
        palabra = (EditText)findViewById(R.id.palabra);

        String palabraString = palabra.getText().toString();

        if(palabraString.isEmpty()){
            Toast.makeText(this,"Rellene todos los campos",Toast.LENGTH_SHORT).show();
        }

        enviardatos(palabraString);
    }

    public void enviardatos(String palabra){


        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://anunwebservice.96.lt/wsphpandroid/buscarProducto.php";
        String parametros = "?palabra=" + palabra;
        client.post(url + parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                cargaLista(obtDatosJson(new String(responseBody)));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void cargaLista(ArrayList<String> datos )
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        listado.setAdapter(adapter);
    }

    public ArrayList<String> obtDatosJson (String response)
    {
        ArrayList<String> listado = new ArrayList<String>();
        try{
            JSONArray jsonArray = new JSONArray(response);
            String texto;
            for(int i = 0; i <jsonArray.length(); i++)
            {
                texto = jsonArray.getJSONObject(i).getString("titulo_producto")+" "+
                        jsonArray.getJSONObject(i).getString("descripcion")+" "+
                        jsonArray.getJSONObject(i).getString("precio");
                listado.add(texto);
            }

        }catch ( Exception e)
        {
            e.fillInStackTrace();
        }
        return listado;
    }


}
