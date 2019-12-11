package com.teste.modelo.ppgi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ValidaCPF extends AppCompatActivity implements View.OnClickListener{

    private EditText _txtCPF;
    private Button _btnValidarCPF;
    private Button _btnVoltarCPF;
    private TextView _tvResultadoCPF;
    private static final String CPF_VALIDO = "CPF VÁLIDO";
    private static final String CPF_INVALIDO = "CPF INVÁLIDO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valida_cpf);

        _txtCPF = (EditText) findViewById(R.id.txtCPF);
        _btnValidarCPF = (Button) findViewById(R.id.btnValidarCPF);
        _tvResultadoCPF = (TextView) findViewById(R.id.tvResultadoCPF);
        _btnVoltarCPF = (Button) findViewById(R.id.btnVoltarCPF);

        _btnValidarCPF.setOnClickListener(this);
        _btnVoltarCPF.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v instanceof Button){
            switch (v.getId()){
                case R.id.btnValidarCPF:{

                    final String cpf = String.valueOf(_txtCPF.getText());

                    if( cpf == null || cpf.equals("") || cpf.length() == 0){
                        _txtCPF.setText("");
                        _txtCPF.setHint(getResources().getString(R.string.digite_cpf));
                        _btnValidarCPF.setText(getResources().getString(R.string.validar));
                    } else if (String.valueOf(_btnValidarCPF.getText()).equals(getResources().getString(R.string.limpar))){
                        _txtCPF.setText("");
                        _tvResultadoCPF.setText("");
                        _txtCPF.setHint(getResources().getString(R.string.digite_aqui));
                        _btnValidarCPF.setText(getResources().getString(R.string.validar));
                    } else{
                        validarCPF(cpf);
                        _btnValidarCPF.setText(getResources().getString(R.string.limpar));
                        _txtCPF.setHint(getResources().getString(R.string.digite_aqui));

                        if(String.valueOf(_tvResultadoCPF.getText()).equals(CPF_INVALIDO)){
                            _tvResultadoCPF.setTextColor(getResources().getColor(R.color.vermelho));
                        }else{
                            _tvResultadoCPF.setTextColor(getResources().getColor(R.color.verde));
                        }
                    }

                    View view = this.getCurrentFocus();

                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    break;
                }
                case R.id.btnVoltarCPF:{
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                    this.finish();
                    break;
                }
            }
        }
    }

    private void validarCPF(final String cpf) {

        if (cpf.length() != 11) {
            _tvResultadoCPF.setText(CPF_INVALIDO);
            return;
        }

        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") ||
                cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999")) {
            _tvResultadoCPF.setText(CPF_INVALIDO);
            return;
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        sm = 0;
        peso = 10;
        for (i=0; i<9; i++) {
            num = (int)(cpf.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11))
            dig10 = '0';
        else dig10 = (char)(r + 48);

        sm = 0;
        peso = 11;
        for(i=0; i<10; i++) {
            num = (int)(cpf.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11))
            dig11 = '0';
        else dig11 = (char)(r + 48);

        if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))){
            _tvResultadoCPF.setText(CPF_VALIDO);
        } else{
            _tvResultadoCPF.setText(CPF_INVALIDO);
        }
    }
}
