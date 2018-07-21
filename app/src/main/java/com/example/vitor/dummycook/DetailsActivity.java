package com.example.vitor.dummycook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Pessoa user = (Pessoa) getIntent().getSerializableExtra("pessoa");

        TextView mText = (TextView) findViewById(R.id.texto1);
        mText.setText(user.getName());
    }
}
