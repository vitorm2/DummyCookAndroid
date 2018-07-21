package com.example.vitor.dummycook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        Pessoa user = (Pessoa) getIntent().getSerializableExtra("pessoa");

        TextView mText = (TextView) findViewById(R.id.text_recipeName);
        TextView texto2 = (TextView) findViewById(R.id.text_requiriments);
        TextView texto3 = (TextView) findViewById(R.id.text_ingredients);
        ImageView imageRecipe = (ImageView) findViewById(R.id.image_recipe);
        imageRecipe.setClipToOutline(true);
        mText.setText(user.getName());
        texto2.setText(user.getCity());
        texto3.setText(user.getDescription());
    }
}
