package com.example.vitor.dummycook;


import android.app.Activity;


import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        String[] names = {"Alan", "Arthur", "Nicolas", "Angela", "Brenda", "Liz"};
        String[] cities = {"Rio", "Miami", "Paris", "Montevideo", "Tokyo", "Nairobi"};
        String desc = "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit";
        ArrayList<Pessoa> lista = new ArrayList<>();

        for(int i=0; i<5; i++){
            Pessoa p1 = new Pessoa(names[i], cities[i], desc, 12);
            lista.add(i,p1);
        }

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(lista);

        mRecyclerView.setAdapter(mAdapter);


        //mRecyclerView.addItemDecoration(
          //
        //      new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    // ...

        /*ListView listview = (ListView) findViewById(R.id.lista);


        String[] dados = new String[] { "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread",
                "Honeycomb", "Ice Cream Sandwich", "Jelly Bean",
                "KitKat", "Lollipop", "Marshmallow", "Nougat" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dados);

        listview.setAdapter(adapter);
*/
    }
}
