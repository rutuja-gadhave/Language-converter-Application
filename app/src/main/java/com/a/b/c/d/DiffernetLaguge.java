package com.a.b.c.d;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.api.GoogleAPI;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;

public class DiffernetLaguge extends AppCompatActivity {
    private String first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_differnet_laguge);

        Intent intent = getIntent();

        first = intent.getStringExtra("Name");
        final TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(first);

        Button b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   String InputString ="Hello";
                ///  String OutputString;
                //  try {
                //GoogleAPI.setHttpReferrer("www.ecs");
                //     GoogleAPI.setHttpReferrer("https://translation.googleapis.com/language/translate/v2");
                //   GoogleAPI.setKey("AIzaSyApUWyK4OO0eLyQLUqDmCE2TotXk_vw1bY");
                // Translate.setHttpReferrer("http://android-er.blogspot.com/");

                //   OutputString = Translate.DEFAULT.execute(InputString,
                //  } catch (Exception ex) {
                ////      ex.printStackTrace();
                //     OutputString = "Error";
                // }
                Intent i = new Intent(DiffernetLaguge.this, ForthActivity.class);
                startActivity(i);
                // MyOutputText.setText(OutputString);
            }
        });


        Button b = (Button) findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiffernetLaguge.this, ThirdActivity.class);
                startActivity(i);
            }
        });

        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiffernetLaguge.this, CDataset.class);
                i.putExtra("name",first);
                startActivity(i);
            }
        });


    }
}