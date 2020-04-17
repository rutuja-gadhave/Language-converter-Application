package com.a.b.c.d;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.api.GoogleAPI;
import com.google.api.GoogleAPIException;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class ShowActivity extends AppCompatActivity {
    String s="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        TextView textView = findViewById(R.id.textView3);

        try {

            FileInputStream fileIn=openFileInput("mmyfile.txt");

            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[2];

            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            textView.setText(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button translate = findViewById(R.id.button_translateToMarathi);
        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleAPI.setKey("AIzaSyAMVB-324doTj2mmq9cDYr9BZz-Nl4kXRo");
                GoogleAPI.setHttpReferrer("https://github.com/rmtheis/android-ocr");

                try {
                    String translatedText = Translate.DEFAULT.execute("good morning", Language.ENGLISH, Language.ENGLISH);
                    TextView textView1 = findViewById(R.id.textView_translatedText);
                    textView1.setText(translatedText);
                } catch (GoogleAPIException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
