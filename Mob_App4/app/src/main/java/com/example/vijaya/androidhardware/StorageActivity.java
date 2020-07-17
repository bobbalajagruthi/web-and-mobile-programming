package com.example.vijaya.androidhardware;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class StorageActivity extends AppCompatActivity {
    EditText txt_content;
    EditText contenttoDisplay;
    String FILENAME = "MyAppStorage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        txt_content = (EditText) findViewById(R.id.id_txt_mycontent);
        contenttoDisplay = (EditText) findViewById(R.id.id_txt_display);
    }

    public void saveTofile(View v) throws IOException {

        // ICP Task4: Write the code to save the text
        String text = txt_content.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, MODE_APPEND);
            fos.write(text.getBytes());
            txt_content.getText().clear();
            Toast.makeText(this, "Saved"+getFilesDir()+ "/"+ FILENAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (fos != null){
                try {
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }


    }

    public void retrieveFromFile(View v) throws IOException {

        // ICP Task4: Write the code to display the above saved text
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine())!= null){
                sb.append(text);
            }
            contenttoDisplay.setText(sb.toString());
            contenttoDisplay.setVisibility(View.VISIBLE);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (fis != null){
                try {
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }


    }
}