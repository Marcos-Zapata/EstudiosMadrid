package com.example.bibliotekasmadrid.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.bibliotekasmadrid.adapters.ListAdapter;
import com.example.bibliotekasmadrid.R;
import com.example.bibliotekasmadrid.modelsPlaces.Graph;
import com.example.bibliotekasmadrid.services.madridDataService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BibliotecasUniversitarias extends AppCompatActivity implements ListAdapter.OnNoteListener{
    List<Graph> result = new ArrayList<>();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_BiblioteKasMadrid);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bibliotecas_universitarias);

        //Menu de navegación
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bibliotecasUniversitarias);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.salasEstudio:
                        startActivity(new Intent(getApplicationContext(),SalasEstudio.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bibliotecas_bibliobuses:
                        startActivity(new Intent(getApplicationContext(),bibliotecas_bibliobuses.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bibliotecasUniversitarias:
                        return true;
                    case R.id.Busqueda:
                        startActivity(new Intent(getApplicationContext(), BusquedaNombre.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.BusquedaLoc:
                        startActivity(new Intent(getApplicationContext(),BusquedaLoc.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://datos.madrid.es/egob/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        madridDataService service = retrofit.create(madridDataService.class);

        service.listBibliotecasUniversitarias(null,null,null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        //Cuando funciona correctamente
                        x -> {
                            result = x.getGraph();
                            init(result);
                        },
                        //Cundo hay error en ejecucion
                        error -> {
                            Log.e("ERROR: ", error.getMessage());
                        }
                );


    }
    public void init(List<Graph> responseAPI){
        ListAdapter listAdapter = new ListAdapter(responseAPI,this,this);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this,ExtendedActivity.class);
        intent.putExtra("selected_place",result.get(position));
        startActivity(intent);
    }
}
