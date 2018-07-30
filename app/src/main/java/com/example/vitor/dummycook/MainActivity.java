package com.example.vitor.dummycook;


import android.app.Activity;


import android.content.Context;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

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

        int video_r1_step1 = R.raw.r1_step1;
        int video_r1_step3 =  R.raw.r1_step3;

        ArrayList<Step> stepList_rec1 =  new ArrayList<>();

        Step r1_step1 =  new Step("Melt the sugar in a pan over low heat, stiring constantly, until the sugar becomes a golden brown syrup.",
                "Melt the sugar", 1, "", video_r1_step1, "");
        Step r1_step2 =  new Step("Once the sugar becomes a golden brown syrup, it`s ready. Switch the stove off.",
                "Turning off the stove", 2, "step2", 0, "");
        Step r1_step3 =  new Step("Place all other ingredients into a blender, blend it for 5 minutes.",
                "Blend the ingredients", 3, "", video_r1_step3, "300");
        Step r1_step4 =  new Step("Pour mixture into the pan. The pan is now with the sugar caramelized",
                "Pour the mixture into the pan", 4, "step4", 0, "");
        Step r1_step5 =  new Step("Place water into a cake tin. Then, place the pan with the mixture and the caramelized sugar inside",
                "Place water into a cake tin", 5, "step5", 0, "");
        Step r1_step6 =  new Step("Turn on the oven at 200 degrees and put the  cake tin inside. Let it cook for 2 hours.",
                "Put the cake tin into the oven", 6, "step6", 0, "");
        Step r1_step7 =  new Step("Carefully remove your pan from the oven, watch out as the water will be very hot",
                "Remove from the oven", 7, "step7", 0, "");
        Step r1_step8 =  new Step("The flan must cool for a few hours, better if you let it rest on the refrigerator overnight",
                "Let it cool", 8, "", 0, "");
        Step r1_step9 =  new Step("To take if off the pan, heat the pan over low heat for 20 seconds, then invert into a serving plate. Pick a proper plate as it needs to be large enough for the flan and some of the caramel",
                "Transfer to a plate", 9, "step9", 0, "20");
        Step r1_step10 =  new Step("Enjoy your brazilian flan!", "Enjoy your flan!", 10,
                "step10", 0, "");

        stepList_rec1.add(r1_step1);
        stepList_rec1.add(r1_step2);
        stepList_rec1.add(r1_step3);
        stepList_rec1.add(r1_step4);
        stepList_rec1.add(r1_step5);
        stepList_rec1.add(r1_step6);
        stepList_rec1.add(r1_step7);
        stepList_rec1.add(r1_step8);
        stepList_rec1.add(r1_step9);
        stepList_rec1.add(r1_step10);

        Recipe r1 = new Recipe("Brazilan Flan", "image1",  stepList_rec1, "Oven, Pan, Cake Tin and Stoven",
                "1 cup sugar\n2 cans sweetened condensed milk\n1 can whole milk\n3 eggs",
                "6h", "8", "R$16,00");


        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ArrayList<Step> stepList_rec2 =  new ArrayList<>();

        Step r2_step1 = new Step("Turn on your oven to 325 degrees F (165 degrees C) and let it preheat",
                "Turn on your oven", 1, "preheat", 0, "");
        Step r2_step2 = new Step("In a large bowl, combine cream cheese, sugar and vanilla and beat until smooth",
                "Turn on your oven", 2, "mixing", 0, "");
        Step r2_step3 = new Step("Blend in eggs, one at a time",
                "Turn on your oven", 3, "blendeggs", 0, "");

        stepList_rec2.add(r2_step1);
        stepList_rec2.add(r2_step2);
        stepList_rec2.add(r2_step3);


        Recipe r2 = new Recipe("Double Layer Pumpkin Cheesecake", "image2", stepList_rec2, "1 beater",
                "2 (8 ounce) packages cream cheese, softened\n1/2 cup white sugar\n1/2 teaspoon vanilla extract\n2 eggs\n1 (9 inch) prepared graham cracker crust\n1/2 pumpkin puree\n1/2 teaspoon ground cinnamon\n1 pinch ground cloves\n1 pinch ground nutmeg\n1/2 cup frozen whipped topping thawed",
                "4h10", "8", "R$ 24,00");


        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ArrayList<Step> stepList_rec3 =  new ArrayList<>();

        Step r3_step1 = new Step("Turn on your oven to 325 degrees F (165 degrees C) and let it preheat",
            "Turn on your oven", 1, "preheat", 0, "");
        Step r3_step2 = new Step("In a large bowl, combine cream cheese, sugar and vanilla and beat until smooth",
            "Turn on your oven", 2, "mixing", 0, "");
        Step r3_step3 = new Step("Blend in eggs, one at a time",
            "Turn on your oven", 3, "blendeggs", 0, "");

        stepList_rec2.add(r3_step1);
        stepList_rec2.add(r3_step2);
        stepList_rec2.add(r3_step3);

        Recipe r3 =  new Recipe("Pumpkin Ginger Cupcakes", "image3", stepList_rec3, "1 beater",
                "", "1h 20min", "2 pratos", "R$ 24,00");

        //for(int i=0; i<5; i++){
          //  Pessoa p1 = new Pessoa(names[i], cities[i], desc, 12);
            //lista.add(i,p1);
        //}

        ArrayList<Recipe> recipeList =  new ArrayList<>();
        recipeList.add(r1);
        recipeList.add(r2);
        recipeList.add(r3);


        Context context =  getApplicationContext();
        android.content.res.Resources res = context.getResources();
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(recipeList, res);
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

        SearchView simpleSearchView = (SearchView) findViewById(R.id.simpleSearchView); // inititate a search view

    }

}
