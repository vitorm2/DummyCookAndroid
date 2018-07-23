package com.example.vitor.dummycook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        Pessoa user = (Pessoa) getIntent().getSerializableExtra("pessoa");

        ArrayList<String> lista = new ArrayList<>();
        lista.add(user.getCity());
        lista.add(user.getName());
        lista.add(""+user.getAge());

        ListView stepsList = (ListView) findViewById(R.id.stepList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, lista);

        stepsList.setAdapter(adapter);
    }
}
