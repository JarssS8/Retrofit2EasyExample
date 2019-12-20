package com.example.retrofit2prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView titulo;
    private ImageView imagen;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://xkcd.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RestComic restComic=retrofit.create(RestComic.class);

        titulo=findViewById(R.id.txtNombre);
        imagen = findViewById(R.id.imageView);
        boton=findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Comic> comicCall = restComic.getComic(new Random().nextInt(1000));
                comicCall.enqueue(new Callback<Comic>() {
                    @Override
                    public void onResponse(Call<Comic> call, Response<Comic> response) {
                        Comic comic=response.body();
                        if (comic!=null) {
                            Picasso.get().load(comic.getImg()).into(imagen);
                            titulo.setText(comic.getTitle());
                        }

                    }

                    @Override
                    public void onFailure(Call<Comic> call, Throwable t) {

                    }
                });
            }
        });
    }
}
