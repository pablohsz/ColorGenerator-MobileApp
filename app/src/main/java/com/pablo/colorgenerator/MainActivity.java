package com.pablo.colorgenerator;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Bitmap bitmap1, bitmap2, bitmap3;
    Button genButton, copyButton;
    Canvas canvas1, canvas2, canvas3;
    ClipboardManager clipboard;
    ImageView cor1, cor2, cor3;
    TextView txCor1, txCor2, txCor3;
    Random gerador = new Random();
    public static int r, g, b;
    //Variáveis que serão usadas para setar o R G B das cores que serão geradas usando a Random
    public static String hexCor1, hexCor2, hexCor3;
    //Variáveis que armazenarão o hexadecimal das cores

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
        copyButton = findViewById(R.id.copyButton);

        clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        copyButton.setEnabled(false);
        copyButton.setVisibility(View.INVISIBLE);

        bitmap1 = Bitmap.createBitmap(500, 200, Bitmap.Config.RGB_565);
        bitmap2 = Bitmap.createBitmap(500, 200, Bitmap.Config.RGB_565);
        bitmap3 = Bitmap.createBitmap(500, 200, Bitmap.Config.RGB_565);

        canvas1 = new Canvas(bitmap1);
        canvas2 = new Canvas(bitmap2);
        canvas3 = new Canvas(bitmap3);

        genButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyButton.setEnabled(true);
                copyButton.setVisibility(View.VISIBLE);

                //Gerar cores do primeiro bloco
                gerar();
                canvas1.drawRGB(r, g, b);
                hexCor1 = String.format("#%02x%02x%02x", r, g, b);
                txCor1.setText(hexCor1);

                //Gerar cores do segundo bloco
                gerar();
                hexCor2 = String.format("#%02x%02x%02x", r, g, b);
                canvas2.drawRGB(r, g, b);
                txCor2.setText(hexCor2);

                //Gerar cores do terceiro bloco
                gerar();
                hexCor3 = String.format("#%02x%02x%02x", r, g, b);
                canvas3.drawRGB(r, g, b);
                txCor3.setText(hexCor3);

                cor1.setImageBitmap(bitmap1);
                cor2.setImageBitmap(bitmap2);
                cor3.setImageBitmap(bitmap3);

            }
        });

        //Quando uma das cores forem clicadas, será gerada uma nova cor para o bloco
        cor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gerar();
                canvas1.drawRGB(r, g, b);
                hexCor1 = String.format("#%02x%02x%02x", r, g, b);
                txCor1.setText(hexCor1);
                cor1.setImageBitmap(bitmap1);
            }
        });

        cor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gerar();
                hexCor2 = String.format("#%02x%02x%02x", r, g, b);
                canvas2.drawRGB(r, g, b);
                txCor2.setText(hexCor2);
                cor2.setImageBitmap(bitmap2);
            }
        });

        cor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gerar();
                hexCor3 = String.format("#%02x%02x%02x", r, g, b);
                canvas3.drawRGB(r, g, b);
                txCor3.setText(hexCor3);
                cor3.setImageBitmap(bitmap3);
            }
        });

        //Quando uma das cores sofrerem um clique longo, o RGB ou HEX será copiado para a área de transferência
        cor1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData clip = ClipData.newPlainText("", hexCor1);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity.this, "Copiado para a área de transferência", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        cor2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData clip = ClipData.newPlainText("", hexCor2);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity.this, "Copiado para a área de transferência", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        cor3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData clip = ClipData.newPlainText("", hexCor3);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity.this, "Copiado para a área de transferência", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = hexCor1 + "\n" + hexCor2 + "\n" + hexCor3;
                ClipData clip = ClipData.newPlainText("", txt);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity.this, "Cores copiadas para a área de transferência", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void gerar() {
        r = gerador.nextInt(256);
        g = gerador.nextInt(256);
        b = gerador.nextInt(256);
    }

}