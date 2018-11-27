package com.example.silvia.workhouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[]librosNombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listadLibros);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api=retrofit.create(Api.class);
        Call<List<LibrosIn>> call= api.getLibros();

        call.enqueue(new Callback<List<LibrosIn>>() {
            @Override
            public void onResponse(Call<List<LibrosIn>> call, Response<List<LibrosIn>> response) {

                List<LibrosIn> librosList= response.body();

               /* for (LibrosIn h:librosList)
                {
                    //Log.d("userId", String.valueOf(h.getUserId()));
                    //Log.d("id", String.valueOf(h.getId()));
                    //Log.d("title",h.getTitle());
                    Log.d("body", h.getBody());
                }*/

               librosNombres= new String[librosList.size()];

               for( int i=0; i<librosList.size();i++)
               {
                   librosNombres[i]=librosList.get(i).getTitle();
               }

               listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,librosNombres));
            }

            @Override
            public void onFailure(Call<List<LibrosIn>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String textItemList = (String) listView.getItemAtPosition(position);

                Toast.makeText(getBaseContext(), position+textItemList ,Toast.LENGTH_SHORT).show();

            }
        });




    }







}
