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

public class Lista extends AppCompatActivity {

    private ListView firmy;
    private EditText wysz, znaj;
    private Button nadz, gen, zalo, wsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firmy = (ListView) findViewById(R.id.listView4);
        wysz = (EditText) findViewById(R.id.editText5);
        znaj = (EditText) findViewById(R.id.editText4);

        zalo = (Button) findViewById(R.id.button8);
        gen = (Button) findViewById(R.id.button7);
        nadz = (Button) findViewById(R.id.button9);
        wsp = (Button) findViewById(R.id.button10);

        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JASONTask().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty.json?conditions[q]=" + znaj.getText().toString());
            }
        });


        nadz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JASONTaskNadzorcy().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty/" + wysz.getText().toString() + ".json?layers[]=komitetZalozycielski&layers[]=nadzor&layers[]=wspolnicy");
            }
        });

        zalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JASONTaskZalozyciele().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty/" + wysz.getText().toString() + ".json?layers[]=komitetZalozycielski&layers[]=nadzor&layers[]=wspolnicy");
            }
        });

        wsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JASONTaskWspolnicy().execute("https://api-v3.mojepanstwo.pl/dane/krs_podmioty/" + wysz.getText().toString() + ".json?layers[]=komitetZalozycielski&layers[]=nadzor&layers[]=wspolnicy");
            }
        });

    }


    private class JASONTaskZalozyciele extends AsyncTask<String, String, List<ModelFirmy>> {

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
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray akcje = parentObject.getJSONObject("layers").getJSONArray("komitetZalozycielski");


                List<ModelFirmy> modelFirmyList = new ArrayList<>();
                    for (int i = 0; i < akcje.length(); i++) {
                        JSONObject finalObject = akcje.getJSONObject(i);
                        ModelFirmy modelFirmy = new ModelFirmy();
                        modelFirmy.setNazwa(finalObject.getString("nazwa"));


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
                if (con != null) {
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

            KRSAdapterZalozyciele adapter = new KRSAdapterZalozyciele(getApplicationContext(), R.layout.lista, result);
            firmy.setAdapter(adapter);
            // TODO need to set data to the list
        }
    }

    private class JASONTaskNadzorcy extends AsyncTask<String, String, List<ModelFirmy>> {

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
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray akcje = parentObject.getJSONObject("layers").getJSONArray("nadzor");

                List<ModelFirmy> modelFirmyList = new ArrayList<>();
                for (int i = 0; i < akcje.length(); i++) {
                    JSONObject finalObject = akcje.getJSONObject(i);
                    ModelFirmy modelFirmy = new ModelFirmy();
                    modelFirmy.setNazwa(finalObject.getString("nazwa"));



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
                if (con != null) {
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

            KRSAdapterNadzorcy adapter = new KRSAdapterNadzorcy(getApplicationContext(), R.layout.lista, result);
            firmy.setAdapter(adapter);
            // TODO need to set data to the list
        }
    }

    private class JASONTaskWspolnicy extends AsyncTask<String, String, List<ModelFirmy>> {

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
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray akcje = parentObject.getJSONObject("layers").getJSONArray("wspolnicy");

                List<ModelFirmy> modelFirmyList = new ArrayList<>();
                for (int i = 0; i < akcje.length(); i++) {
                    JSONObject finalObject = akcje.getJSONObject(i);
                    ModelFirmy modelFirmy = new ModelFirmy();
                    modelFirmy.setNazwa(finalObject.getString("nazwa"));



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
                if (con != null) {
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

            KRSAdapterWspolnicy adapter = new KRSAdapterWspolnicy(getApplicationContext(), R.layout.lista, result);
            firmy.setAdapter(adapter);
            // TODO need to set data to the list
        }
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
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("Dataobject");

                //StringBuffer finnalBufferedData = new StringBuffer();

                List<ModelFirmy> modelFirmyList = new ArrayList<>();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    JSONObject nazwa = parentArray.getJSONObject(i).getJSONObject("data");
                    ModelFirmy modelFirmy = new ModelFirmy();
                    modelFirmy.setNazwa(nazwa.getString("krs_podmioty.nazwa"));
                    modelFirmy.setId(finalObject.getString("id"));


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
                if (con != null) {
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

            KRSAdapter adapter = new KRSAdapter(getApplicationContext(), R.layout.shearch_id, result);
            firmy.setAdapter(adapter);
            // TODO need to set data to the list
        }
    }


    public class KRSAdapterZalozyciele extends ArrayAdapter {

        private List<ModelFirmy> modelFirmyList;
        private int resource;
        private LayoutInflater inflater;

        public KRSAdapterZalozyciele(Context context, int resource, List<ModelFirmy> objects) {
            super(context, resource, objects);
            modelFirmyList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(resource, null);
            }

            TextView nazwa;

            nazwa = (TextView) convertView.findViewById(R.id.textView6);

            nazwa.setText(modelFirmyList.get(position).getNazwa());

            return convertView;
        }
    }

    public class KRSAdapterWspolnicy extends ArrayAdapter {

        private List<ModelFirmy> modelFirmyList;
        private int resource;
        private LayoutInflater inflater;

        public KRSAdapterWspolnicy(Context context, int resource, List<ModelFirmy> objects) {
            super(context, resource, objects);
            modelFirmyList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(resource, null);
            }

            TextView nazwa;

            nazwa = (TextView) convertView.findViewById(R.id.textView6);

            nazwa.setText(modelFirmyList.get(position).getNazwa());

            return convertView;
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
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(resource, null);
            }

            TextView nazwa;
            TextView id;

            nazwa = (TextView) convertView.findViewById(R.id.textView11);
            id = (TextView) convertView.findViewById(R.id.textView10);

            nazwa.setText(modelFirmyList.get(position).getNazwa());
            id.setText(modelFirmyList.get(position).getId());

            return convertView;
        }
    }

    public class KRSAdapterNadzorcy extends ArrayAdapter {

        private List<ModelFirmy> modelFirmyList;
        private int resource;
        private LayoutInflater inflater;

        public KRSAdapterNadzorcy(Context context, int resource, List<ModelFirmy> objects) {
            super(context, resource, objects);
            modelFirmyList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(resource, null);
            }

            TextView nazwa;

            nazwa = (TextView) convertView.findViewById(R.id.textView6);

            nazwa.setText(modelFirmyList.get(position).getNazwa());

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
            Intent glo = new Intent(Lista.this, MainActivity.class);
            startActivity(glo);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
