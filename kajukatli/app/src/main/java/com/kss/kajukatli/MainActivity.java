package com.kss.kajukatli;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
//declaration java object from XML component
    Button btn_speak , btn_shared;
    TextView tv_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //connect java object to XML component
        btn_speak = findViewById(R.id.btn_speak);
        tv_msg = findViewById(R.id.tv_msg);
        btn_shared = findViewById(R.id.btn_shared);
        //to click on the button
        btn_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to perform any action
                //to perform voice recognization with the help of intent
                Intent voice = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                //to get the collection of languages
                voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                voice.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now");
                //toget your default language
                voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                //to assign the process id to
                startActivityForResult(voice, 1);

            }
        });
        btn_shared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_TEXT, tv_msg.getText().toString());
                share.setType("text/plain");
                startActivity(Intent.createChooser(share, "share via"));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data!= null){
            ArrayList<String> arrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            //display to text view
            tv_msg.setText(arrayList.get(0));
        }
    }

}