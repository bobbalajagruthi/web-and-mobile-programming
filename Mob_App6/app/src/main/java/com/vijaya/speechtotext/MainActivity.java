package com.vijaya.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    TextToSpeech texttospeech;
    Button submitbtn;
    TextView question;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    String Question= "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        submitbtn = (Button) findViewById(R.id.Submitbtn);
        question= (TextView) findViewById(R.id.Textview);
        submitbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startChat();
            }
        });
        preferences = getSharedPreferences("namePrefs",0);
        editor = preferences.edit();
        texttospeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    // Setting the Locale.
                    texttospeech.setLanguage(Locale.US);
                    texttospeech.speak("Hello", TextToSpeech.QUEUE_FLUSH, null);
                    mVoiceInputTv.setText(Html.fromHtml("<p style=\"color:red;\">Speaker : Hello !!</p>"));
                }
            }
        });
    }

    private void startChat() {
        Question = question.getText().toString();
        Log.i("question", Question);
        mVoiceInputTv.append(Html.fromHtml("<p style=\"color:blue;\">You :"+Question +"</p>"));
        if (Question.contains("Hello") || Question.contains("Hi") || Question.contains("Hey") ) {
            texttospeech.speak("What is your name", TextToSpeech.QUEUE_FLUSH, null);
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:red;\">Speaker : What is your name ?</p>"));

        }else if (Question.contains("name")) {
            String name = Question.substring(Question.lastIndexOf(' ') + 1);
            // Setting into Editor
            editor.putString("name", name).apply();
            texttospeech.speak("Hello "+name, TextToSpeech.QUEUE_FLUSH, null);
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:red;\">Speaker : Hello, "+name+"</p>"));
        }else if(Question.contains("not feeling well")){
            texttospeech.speak("I can understand. Please tell your symptoms in short", TextToSpeech.QUEUE_FLUSH, null);
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:red;\">Speaker : I can understand. Please tell your symptoms in short</p>"));
        }else if(Question.contains("thank you")){
            texttospeech.speak("Thank you too"+preferences.getString("name","")+"Take care", TextToSpeech.QUEUE_FLUSH, null);
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:red;\">Speaker : Thank you too... "+preferences.getString("name","")+", Take care.</p>"));
        }else if(Question.contains("time")){
        // Speaking the Time for the User
            SimpleDateFormat sdfDate =new SimpleDateFormat("HH:mm");//dd/MM/yyyy
            Date now = new Date();
            String[] strDate = sdfDate.format(now).split(":");
            Log.i("startCha", String.valueOf(strDate));
            if(strDate[1].contains("00"))strDate[1] = "o'clock";
            texttospeech.speak("Now the time is "+sdfDate.format(now), TextToSpeech.QUEUE_FLUSH, null);
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:red;\">Speaker : Now the time is : "+sdfDate.format(now)+"</p>"));
        }else if(Question.contains("medicine")) {
            texttospeech.speak("Oh, you have fever??. Please take Dolo ", TextToSpeech.QUEUE_FLUSH, null);
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:red;\">Speaker : Oh, you have fever??. Please take Dolo .</p>"));
        } else {
            texttospeech.speak("Sorry I cant understand that, could you please type it again", TextToSpeech.QUEUE_FLUSH, null);
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:red;\">Speaker : Sorry, I cant understand that!!! could you please type it again ??</p>"));
        }


    }


}