package com.example.juangajardo.solemneappanuncios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class EliminarProdActivity extends AppCompatActivity {
    ListView listado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_prod);

        listado = (ListView)findViewById(R.id.listView) ;

        consultarDatos();
    }

    public void consultarDatos(){


        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://anunwebservice.96.lt/wsphpandroid/todosAnuncios.php";
        client.get(url, new AsyncHttpResponseHandler() {
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
