package com.teste.modelo.ppgi;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button _btnValidarCPF;
    private Button _btnValidarCNPJ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _btnValidarCPF = (Button) findViewById(R.id.btnValidarCPF);
        _btnValidarCNPJ = (Button) findViewById(R.id.btnValidarCNPJ);

        _btnValidarCPF.setOnClickListener(this);
        _btnValidarCNPJ.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v instanceof Button){
            switch (v.getId()){
                case R.id.btnValidarCPF:{
                    Intent i = new Intent(this,ValidaCPF.class);
                    startActivity(i);
                    break;
                }
                case R.id.btnValidarCNPJ:{
                    Intent i = new Intent(this,ValidaCNPJ.class);
                    startActivity(i);
                    break;
                }
            }
        }
    }


}
