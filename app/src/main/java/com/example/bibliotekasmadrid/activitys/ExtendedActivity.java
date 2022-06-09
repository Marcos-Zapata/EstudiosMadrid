package com.example.bibliotekasmadrid.activitys;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bibliotekasmadrid.R;
import com.example.bibliotekasmadrid.modelsPlaces.Graph;
import com.example.bibliotekasmadrid.modelsPlaces.Location;
import com.squareup.picasso.Picasso;

public class ExtendedActivity extends AppCompatActivity {

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.Theme_BiblioteKasMadrid);
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_extendido);
        getIncomingInet();
    }

    private void getIncomingInet() {
        if (getIntent().hasExtra("selected_place")) {
            Graph recibido = getIntent().getParcelableExtra("selected_place");
            //Datos que se quieren mostrar:
            String titulo = recibido.getTitle();
            Location location = new Location();
            location.setLongitude(recibido.getLocation().getLongitude());
            location.setLatitude(recibido.getLocation().getLatitude());
            String calle = recibido.getAddress().getStreetAddress();
            String transporte = recibido.getOrganization().getOrganizationDesc();
            String horario = recibido.getOrganization().getSchedule();
            String servicio = recibido.getOrganization().getServices();
            setDatos(titulo, location, calle, transporte, horario, servicio);
        }
    }

    private void setDatos(String titulo, Location location, String calle, String transporte, String horario, String servicio) {
        TextView name = findViewById(R.id.TextoNombreExt);
        ImageView ImageMap = findViewById(R.id.ImagenMapa);
        if (location != null){
            DrawMap(ImageMap, location);
        }else {
            ImageMap.setImageResource(R.drawable.marca_madrid_azul_pantone286);
        }
        TextView nameCalle = findViewById(R.id.TextoNombreCalleExt);
        TextView datosTrans = findViewById(R.id.TextoComoLlegarExt);
        TextView schedule = findViewById(R.id.TextoHorarioExt);
        TextView service = findViewById(R.id.TextoInformacionExt);
        name.setText(titulo);
        if (calle.length() == 0) {
            nameCalle.setText("No se dispone de informacion");
        }else {
            nameCalle.setText(calle);
        }
        if (horario.length() == 0) {
            datosTrans.setText("No se dispone de informacion");
        }else {
            datosTrans.setText(transporte);
        }
        schedule.setText(horario);
        if (servicio.length() == 0) {
            service.setText("No se dispone de informacion");
        }else {
            service.setText(servicio);
        }
    }

    private void DrawMap(ImageView ImageMap, Location location){
        String lat = location.getLatitude()+"";
        String lon = location.getLongitude()+"";
        String url ="https://maps.googleapis.com/maps/api/staticmap?";
        url+="&zoom=14";
        url+="&size=400x400";
        url+="&maptype=roadmap";
        url+="&markers=size:tiny%7Ccolor:red%7Clabel:P%7C"+lat+", "+lon;
        url+="&key=YourApiKey";
        Picasso.get().load(url).into(ImageMap);
    }
}
