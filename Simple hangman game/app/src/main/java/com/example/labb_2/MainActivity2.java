package com.example.labb_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    private String words [] = { "adopt","badge","camel","dance","ebola",
                                "flash","gamer","Hades","ideal","jeans"};
    private int id_button [] = {R.id.a_button, R.id.b_button, R.id.c_button, R.id.d_button, R.id.e_button,
                                R.id.f_button, R.id.g_button, R.id.h_button, R.id.i_button, R.id.j_button,
                                R.id.k_button, R.id.l_button, R.id.m_button, R.id.n_button, R.id.o_button,
                                R.id.p_button, R.id.q_button, R.id.r_button, R.id.s_button, R.id.t_button,
                                R.id.u_button, R.id.v_button, R.id.w_button, R.id.x_button, R.id.y_button,
                                R.id.z_button};
    private int id_text [] = {R.id.a_text, R.id.b_text, R.id.c_text, R.id.d_text, R.id.e_text};
    private Button [] btn = new Button[id_button.length];
    private EditText [] et = new EditText[id_text.length];
    private TextView tv;
    private Random r = new Random();
    private int attempts = 0, random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);




        random = r.nextInt(words.length);
        tv = findViewById(R.id.attempts_view);
        Intent intent_win = new Intent(MainActivity2.this,MainActivity3.class);
        Intent intent_lose = new Intent(MainActivity2.this,MainActivity4.class);
        for(int i = 0; i < id_text.length; i++){
            et[i] = findViewById(id_text[i]);
        }

        for(int i = 0; i <id_button.length; i++){
            btn[i] = findViewById(id_button[i]);
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button b = (Button)view;
                    String buttonText = b.getText().toString().toLowerCase();

                    if(words[random].contains(buttonText)){
                        char tempChar;
                        String tempString;
                        for(int k = 0; k < words[random].length(); k++){
                            tempChar = words[random].charAt(k);
                            tempString = String.valueOf(tempChar);
                            if(buttonText.equals(tempString)){
                                et[k].setText(tempString);
                                break;
                            }
                        }
                    } else {
                        attempts++;
                        tv.setText(String.valueOf(attempts));
                    }
                    if(!(TextUtils.isEmpty(et[0].getText().toString())) &&
                            !(TextUtils.isEmpty(et[1].getText().toString())) &&
                            !(TextUtils.isEmpty(et[2].getText().toString())) &&
                            !(TextUtils.isEmpty(et[3].getText().toString())) &&
                            !(TextUtils.isEmpty(et[4].getText().toString()))){
                        startActivity(intent_win);
                    }else if(attempts == 7) {
                        startActivity(intent_lose);
                    }
                }
            });
        }
    }
}