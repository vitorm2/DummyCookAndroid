package com.example.vitor.dummycook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class StepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipeSelected");

        ArrayList<Step> stepsList = new ArrayList<>();
        for(Step s: recipe.getStepList()){
           stepsList.add(s);
        }


        ListView stepsListView = (ListView) findViewById(R.id.stepList);


        TextView text_numStepsList = (TextView) findViewById(R.id.text_numStepslist);
        TextView title_textStepsList = (TextView) findViewById(R.id.text_titleStepslist);
        TextView text_detailsStepList = (TextView) findViewById(R.id.text_detailsSteplist);



        StepsListAdapter adapter = new StepsListAdapter(stepsList, this);
        stepsListView.setAdapter(adapter);

        stepsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Seu codigo aqui
                Intent i = new Intent(StepsActivity.this, MainStepActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("recipeSelected",recipe);
                bundle.putInt("index", position);
                i.putExtras(bundle);

                startActivity(i);
            }
        });

    }
}
