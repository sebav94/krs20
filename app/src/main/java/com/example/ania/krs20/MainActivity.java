package com.example.ania.krs20;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?page=1&limit=10");
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    public void wysz(View view) {
        Intent wysz = new Intent(MainActivity.this, Wyszukiwarka.class);
        startActivity(wysz);
    }

    public void akcje(View view) {
        Intent dane = new Intent(MainActivity.this, Dane.class);
        startActivity(dane);
    }

    public void ludzie(View view) {
        Intent lista = new Intent(MainActivity.this, Lista.class);
        startActivity(lista);
    }

    public void podzialF(View view) {
        Intent podzial = new Intent(MainActivity.this, PodzialForma.class);
        startActivity(podzial);
    }

    public void podzialW(View view) {
        Intent podzialW = new Intent(MainActivity.this, PodzialWojew.class);
        startActivity(podzialW);
    }

    public void autorzy(View view) {
        Intent autor = new Intent(MainActivity.this, Autorzy.class);
        startActivity(autor);
    }
}
