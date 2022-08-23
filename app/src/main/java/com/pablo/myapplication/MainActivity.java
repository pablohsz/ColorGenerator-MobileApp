package com.pablo.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView cor1, cor2, cor3;
    TextView txCor1, txCor2, txCor3;
    Button genButton;
    Random gerador = new Random();
    public static int r, g, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cor1 = findViewById(R.id.cor1);
        cor2 = findViewById(R.id.cor2);
        cor3 = findViewById(R.id.cor3);
        txCor1 = findViewById(R.id.txCor1);
        txCor2 = findViewById(R.id.txCor2);
        txCor3 = findViewById(R.id.txCor3);
        genButton = findViewById(R.id.genButton);

        genButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Canvas canvas1, canvas2, canvas3;
                Bitmap bitmap1, bitmap2, bitmap3;

                bitmap1 = Bitmap.createBitmap(500, 200, Bitmap.Config.RGB_565);
                bitmap2 = Bitmap.createBitmap(500, 200, Bitmap.Config.RGB_565);
                bitmap3 = Bitmap.createBitmap(500, 200, Bitmap.Config.RGB_565);

                canvas1 = new Canvas(bitmap1);
                canvas2 = new Canvas(bitmap2);
                canvas3 = new Canvas(bitmap3);

                gerar();
                canvas1.drawRGB(r,g,b);
                txCor1.setText("(" + r + ", " + g + ", " + b + ")");
                gerar();
                canvas2.drawRGB(r,g,b);
                txCor2.setText("(" + r + ", " + g + ", " + b + ")");
                gerar();
                canvas3.drawRGB(r,g,b);
                txCor3.setText("(" + r + ", " + g + ", " + b + ")");

                cor1.setImageBitmap(bitmap1);
                cor2.setImageBitmap(bitmap2);
                cor3.setImageBitmap(bitmap3);
            }
        });
    }

    public void gerar(){
       r = gerador.nextInt(256);
       g = gerador.nextInt(256);
       b = gerador.nextInt(256);
    }

}