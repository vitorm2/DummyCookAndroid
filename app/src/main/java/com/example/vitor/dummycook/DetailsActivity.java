package com.example.vitor.dummycook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


        Pessoa user = (Pessoa) getIntent().getSerializableExtra("pessoa");

        TextView mText = (TextView) findViewById(R.id.text_recipeName);
        TextView texto2 = (TextView) findViewById(R.id.text_requiriments);
        TextView texto3 = (TextView) findViewById(R.id.text_ingredients);
        ImageView imageRecipe = (ImageView) findViewById(R.id.image_recipe);
        Button stratButton = (Button) findViewById(R.id.button_start);
        Button stepsButton = (Button) findViewById(R.id.button_steps);

        stepsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(DetailsActivity.this, StepsActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("pessoa",user);

                i.putExtras(bundle);

                startActivity(i);
            }
        });


        imageRecipe.setClipToOutline(true);
        mText.setText(user.getName());
        texto2.setText(user.getCity());
        texto3.setText(user.getDescription());
    }
}
