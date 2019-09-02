package com.ariska.ocrapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tv_text;
    public static TextView tv_scanResult;
    public static String url;
    Button btn_get, btn_clear, btn_scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_text = (TextView) findViewById(R.id.tv_text);
        tv_scanResult = (TextView) findViewById(R.id.tv_scanResult);
        btn_get = (Button) findViewById(R.id.btn_button);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_scan = (Button) findViewById(R.id.btn_scan);

        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new doit().execute();
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_text.setText("");
            }
        });

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), ScanCodeActivity.class));
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public class doit extends AsyncTask<Void, Void, Void> {

        String words;
        String alamat;

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document document = Jsoup.connect("http://qr.w69b.com/g/mecrnCOOY").get();

                words = document.text();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            tv_text.setText(words);

        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
//        new doit().alamat = url;
        new doit().execute();
        tv_text.setText(url);
    }
}
