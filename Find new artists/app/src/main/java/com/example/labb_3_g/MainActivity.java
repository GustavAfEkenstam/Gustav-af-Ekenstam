package com.example.labb_3_g;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Controller controller;
    private ModelGetData modelGD;
    private Button search_button, clear_button;
    private Handler handler;
    private EditText text;
    private TextView et;
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(Looper.getMainLooper());
        modelGD = new ModelGetData();
        controller = new Controller(this, modelGD);

        et = findViewById(R.id.search_bar);
        text = findViewById(R.id.edit_text);
        search_button = findViewById(R.id.search_button);
        clear_button = findViewById(R.id.clear_button);

        text.setEnabled(false);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelGD.setArtistName(et.getText().toString());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        modelGD.run();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                controller.update();
                            }
                        });
                    }
                }).start();
            }
        });
        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearView();
            }
        });
    }
    public void setView(){
        text.setText(modelGD.getText());
    }
    public void clearView(){
        text.setText("");
    }
}