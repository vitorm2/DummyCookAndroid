package com.example.vitor.dummycook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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


        TextView text_numStepsList = (TextView) findViewById(R.id.text_numStepslist);
        TextView title_textStepsList = (TextView) findViewById(R.id.text_titleStepslist);
        TextView text_detailsStepList = (TextView) findViewById(R.id.text_detailsSteplist);

        /*
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.line_steps_list, lista);
        */

        StepsListAdapter adapter = new StepsListAdapter(lista, this);
        stepsList.setAdapter(adapter);
    }
}
