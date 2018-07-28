package com.example.vitor.dummycook;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipeSelected");

        TextView recipeName = (TextView) findViewById(R.id.text_recipeName);
        TextView textRequiriments = (TextView) findViewById(R.id.text_requiriments);
        TextView textIngredients = (TextView) findViewById(R.id.text_ingredients);
        ImageView imageRecipe = (ImageView) findViewById(R.id.image_recipe);
        Button startButton = (Button) findViewById(R.id.button_start);
        Button stepsButton = (Button) findViewById(R.id.button_steps);

        stepsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DetailsActivity.this, StepsActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("recipeSelected",recipe);

                i.putExtras(bundle);

                startActivity(i);
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DetailsActivity.this, MainStepActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("recipeSelected",recipe);

                i.putExtras(bundle);

                startActivity(i);
            }
        });

        int resID = getResources().getIdentifier(recipe.getRecipeImg(), "drawable", "com.example.vitor.dummycook");

        imageRecipe.setImageResource(resID);
        imageRecipe.setClipToOutline(true);
        recipeName.setText(recipe.getName());
        textRequiriments.setText(recipe.getTextRequirements());
        textIngredients.setText(recipe.getTextIngredients());
    }
}
