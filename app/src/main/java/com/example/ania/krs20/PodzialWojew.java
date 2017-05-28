package com.example.ania.krs20;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ania.krs20.models.ModelFirmy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class PodzialWojew extends AppCompatActivity {
    // private TextView lista;
    private ListView firmy;
    private EditText wysz;
    private Button btn, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podzial_wojew);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firmy = (ListView)findViewById(R.id.listView6);
        wysz = (EditText)findViewById(R.id.editText7);

        btn = (Button)findViewById(R.id.button14);
       // btnNext = (Button)findViewById(R.id.button15);
        //  lista = (TextView)findViewById(R.id.textView2);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wysz.getText().toString().toLowerCase().equals("podkarpackie")) {
                    new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[krs_podmioty.wojewodztwo_id]=9");
                }else{
                    if(wysz.getText().toString().toLowerCase().equals("pomorskie")){
                        new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[krs_podmioty.wojewodztwo_id]=11");
                    }else{
                        if(wysz.getText().toString().toLowerCase().equals("mazowieckie")){
                            new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[krs_podmioty.wojewodztwo_id]=7");
                        }else{
                            if(wysz.getText().toString().toLowerCase().equals("zachodniopomorskie")) {
                                new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[krs_podmioty.wojewodztwo_id]=16");
                            }else{
                                if(wysz.getText().toString().toLowerCase().equals("opolskie")) {
                                    new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[krs_podmioty.wojewodztwo_id]=8");
                                }else{
                                    if(wysz.getText().toString().toLowerCase().equals("kujawsko-pomorskie")) {
                                        new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[krs_podmioty.wojewodztwo_id]=2");
                                    }else{
                                        if(wysz.getText().toString().toLowerCase().equals("świętokrzyskie")) {
                                            new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[krs_podmioty.wojewodztwo_id]=13");
                                        }else{
                                            if(wysz.getText().toString().toLowerCase().equals("śląskie")) {
                                                new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[krs_podmioty.wojewodztwo_id]=12");
                                            }else{
                                                if(wysz.getText().toString().toLowerCase().equals("małopolskie")) {
                                                    new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[krs_podmioty.wojewodztwo_id]=6");
                                                }else{
                                                    if(wysz.getText().toString().toLowerCase().equals("podlaskie")) {
                                                        new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[krs_podmioty.wojewodztwo_id]=10");
                                                    }else{
                                                        if(wysz.getText().toString().toLowerCase().equals("lubelskie")) {
                                                            new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[krs_podmioty.wojewodztwo_id]=3");
                                                        }else{
                                                            if(wysz.getText().toString().toLowerCase().equals("łódzkie")) {
                                                                new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[krs_podmioty.wojewodztwo_id]=5");
                                                            }else{
                                                                if(wysz.getText().toString().toLowerCase().equals("wielkopolskie")) {
                                                                    new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[krs_podmioty.wojewodztwo_id]=15");
                                                                }else{
                                                                    if(wysz.getText().toString().toLowerCase().equals("dolnośląskie")) {
                                                                        new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[krs_podmioty.wojewodztwo_id]=1");
                                                                    }else{
                                                                        if(wysz.getText().toString().toLowerCase().equals("warmińsko-mazurskie")) {
                                                                            new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[krs_podmioty.wojewodztwo_id]=14");
                                                                        }else{
                                                                            if(wysz.getText().toString().toLowerCase().equals("lubuskie")) {
                                                                                new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[krs_podmioty.wojewodztwo_id]=4");
                                                                            }else{
                                                                                Toast.makeText(getApplicationContext(), "Zły parametr", Toast.LENGTH_LONG).show();
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }

            }
        });
    }

    private class JASONTask extends AsyncTask<String, String, List<ModelFirmy>> {

        @Override
        protected List<ModelFirmy> doInBackground(String... params) {
            HttpsURLConnection con = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                con = (HttpsURLConnection) url.openConnection();
                con.connect();
                InputStream stream = con.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("Dataobject");

                //StringBuffer finnalBufferedData = new StringBuffer();

                List<ModelFirmy> modelFirmyList = new ArrayList<>();
                for(int i=0; i<parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i).getJSONObject("data");
                    ModelFirmy modelFirmy = new ModelFirmy();
                    modelFirmy.setNazwa(finalObject.getString("krs_podmioty.nazwa"));
                    modelFirmy.setAdres(finalObject.getString("krs_podmioty.adres"));
                    modelFirmy.setNip(finalObject.getString("krs_podmioty.nip"));
                    modelFirmy.setKrs(finalObject.getString("krs_podmioty.krs"));
                    modelFirmy.setForma(finalObject.getString("krs_podmioty.forma_prawna_str"));


                    modelFirmyList.add(modelFirmy);
                }

                return modelFirmyList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(con != null) {
                    con.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<ModelFirmy> result) {
            super.onPostExecute(result);

            KRSAdapter adapter = new KRSAdapter(getApplicationContext(), R.layout.row, result);
            firmy.setAdapter(adapter);
            // TODO need to set data to the list
        }
    }

    public class KRSAdapter extends ArrayAdapter {

        private List<ModelFirmy> modelFirmyList;
        private int resource;
        private LayoutInflater inflater;

        public KRSAdapter(Context context, int resource, List<ModelFirmy> objects) {
            super(context, resource, objects);
            modelFirmyList = objects;
            this.resource = resource;
            inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = inflater.inflate(resource, null);
            }

            TextView nazwa;
            TextView adres;
            TextView nip;
            TextView krs;
            TextView forma;

            nazwa = (TextView)convertView.findViewById(R.id.textView);
            adres = (TextView)convertView.findViewById(R.id.textView3);
            nip = (TextView)convertView.findViewById(R.id.textView4);
            krs = (TextView)convertView.findViewById(R.id.textView2);
            forma = (TextView)convertView.findViewById(R.id.textView5);

            nazwa.setText(modelFirmyList.get(position).getNazwa());
            adres.setText(modelFirmyList.get(position).getAdres());
            nip.setText("NIP: " + modelFirmyList.get(position).getNip());
            krs.setText("KRS: " + modelFirmyList.get(position).getKrs());
            forma.setText("Forma prawna: " + modelFirmyList.get(position).getForma());


            return convertView;
        }
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
        if (id == R.id.action_settings) {
            Intent glo = new Intent(PodzialWojew.this, MainActivity.class);
            startActivity(glo);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
