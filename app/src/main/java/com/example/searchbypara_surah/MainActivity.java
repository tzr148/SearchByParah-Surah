package com.example.searchbypara_surah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner type;
    ArrayAdapter<String> spinnerAdapterType;
    Spinner lang;
    ArrayAdapter<String> spinnerAdapterLang;
    EditText number;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    RecyclerView.LayoutManager layoutManager;
    Button search,commits;

    ArrayList<Verse> verses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search=findViewById(R.id.btn_search);
        commits=findViewById(R.id.btn_commits);
        type=findViewById(R.id.spinnerType);
        lang=findViewById(R.id.spinnerLang);
        number=findViewById(R.id.number);
        ArrayList<String> types= new ArrayList<>();
        types.add("By Parah");
        types.add("By Surah");
        spinnerAdapterType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        spinnerAdapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(spinnerAdapterType);
        ArrayList<String> langs= new ArrayList<>();
        langs.add("Urdu");
        langs.add("English");
        langs.add("Hindi");
        langs.add("Pushto");
        langs.add("Sindhi");
        spinnerAdapterLang = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, langs);
        spinnerAdapterLang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lang.setAdapter(spinnerAdapterLang);
        number=findViewById(R.id.number);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number_txt=number.getText().toString();
                String type_txt=type.getSelectedItem().toString();
                String lang_txt=lang.getSelectedItem().toString();
                verses=new ArrayList<Verse>();
                try {
                    InputStream inputStream = getAssets().open("QuranMetaData.json");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    JSONArray jsonArray = new JSONArray(builder.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String juz = object.getString("juz").toString();
                        String surah_number=object.getString("surah_number").toString();
                        String text=object.getString("text").toString();
                        String translation="";
                        if(type_txt.equals("Surah") && surah_number.equals(number_txt) )
                        {
                            text=object.getString("text");
                            if(lang_txt.equals("Urdu"))
                                translation=object.getString("UrduTranslation");
                            else if(lang_txt.equals("English"))
                                translation=object.getString("EnglishTranslation");
                            else if(lang_txt.equals("Sindhi"))
                                translation=object.getString("SindhiTranslation");
                            else if(lang_txt.equals("Hindi"))
                                translation=object.getString("HindiTranslation");
                            else if(lang_txt.equals("Pushto"))
                                translation=object.getString("PushtoTransation");
                            Verse verse=new Verse(text,translation);
                            verses.add(verse);
                        }
                        if(type_txt.equals("By Parah") && juz.equals(number_txt) ) {
                            text = object.getString("text");
                            if (lang_txt.equals("Urdu"))
                                translation = object.getString("UrduTranslation");
                            else if (lang_txt.equals("English"))
                                translation = object.getString("EnglishTranslation");
                            else if (lang_txt.equals("Sindhi"))
                                translation = object.getString("SindhiTranslation");
                            else if (lang_txt.equals("Hindi"))
                                translation = object.getString("HindiTranslation");
                            else if (lang_txt.equals("Pushto"))
                                translation = object.getString("PushtoTransation");
                            Verse verse=new Verse(text,translation);
                            verses.add(verse);
                        }
                    }
                } catch (IOException e) {
                    Log.e("File Error", "Error reading file: " + e.getMessage());
                } catch (JSONException e) {
                    Log.e("JSON Error", "Error parsing JSON object: " + e.getMessage());
                }
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(false);
                layoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new myRecyclerViewAdapter(verses) ;
                recyclerView.setAdapter(adapter);
            }
        });
    }
}