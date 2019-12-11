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

public class ValidaCNPJ extends AppCompatActivity implements View.OnClickListener{

    private EditText _txtCNPJ;
    private Button _btnValidarCNPJ;
    private Button _btnVoltarCNPJ;
    private TextView _tvResultadoCNPJ;
    private static final String CNPJ_VALIDO = "CNPJ VÁLIDO";
    private static final String CNPJ_INVALIDO = "CNPJ INVÁLIDO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valida_cnpj);

        _txtCNPJ = (EditText) findViewById(R.id.txtCNPJ);
        _btnValidarCNPJ = (Button) findViewById(R.id.btnValidarCNPJ);
        _tvResultadoCNPJ = (TextView) findViewById(R.id.tvResultadoCNPJ);
        _btnVoltarCNPJ = (Button) findViewById(R.id.btnVoltarCNPJ);

        _btnValidarCNPJ.setOnClickListener(this);
        _btnVoltarCNPJ.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v instanceof Button){
            switch (v.getId()){
                case R.id.btnValidarCNPJ:{

                    final String cnpj = String.valueOf(_txtCNPJ.getText());

                    if( cnpj == null || cnpj.equals("") || cnpj.length() == 0){
                        _txtCNPJ.setText("");
                        _txtCNPJ.setHint(getResources().getString(R.string.digite_cnpj));
                        _btnValidarCNPJ.setText(getResources().getString(R.string.validar));
                    } else if (String.valueOf(_btnValidarCNPJ.getText()).equals(getResources().getString(R.string.limpar))){
                        _txtCNPJ.setText("");
                        _tvResultadoCNPJ.setText("");
                        _txtCNPJ.setHint(getResources().getString(R.string.digite_aqui));
                        _btnValidarCNPJ.setText(getResources().getString(R.string.validar));
                    } else{
                        validarCNPJ(cnpj);
                        _txtCNPJ.setHint(getResources().getString(R.string.digite_aqui));
                        _btnValidarCNPJ.setText(getResources().getString(R.string.limpar));

                        if(String.valueOf(_tvResultadoCNPJ.getText()).equals(CNPJ_INVALIDO)){
                            _tvResultadoCNPJ.setTextColor(getResources().getColor(R.color.vermelho));
                        }else{
                            _tvResultadoCNPJ.setTextColor(getResources().getColor(R.color.verde));
                        }
                    }

                    View view = this.getCurrentFocus();

                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    break;
                }
                case R.id.btnVoltarCNPJ:{
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                    this.finish();
                    break;
                }
            }
        }
    }

    private void validarCNPJ(final String cnpj) {

        if (cnpj.length() != 14) {
            _tvResultadoCNPJ.setText(CNPJ_INVALIDO);
            return;
        }

        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") ||
                cnpj.equals("22222222222222") || cnpj.equals("33333333333333") ||
                cnpj.equals("44444444444444") || cnpj.equals("55555555555555") ||
                cnpj.equals("66666666666666") || cnpj.equals("77777777777777") ||
                cnpj.equals("88888888888888") || cnpj.equals("99999999999999")){
            _tvResultadoCNPJ.setText(CNPJ_INVALIDO);
            return;
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

        sm = 0;
        peso = 2;

        for (i=11; i>=0; i--) {
            num = (int)(cnpj.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso + 1;
            if (peso == 10)
                peso = 2;
        }

        r = sm % 11;
        if ((r == 0) || (r == 1))
            dig13 = '0';
        else dig13 = (char)((11-r) + 48);

        sm = 0;
        peso = 2;
        for (i=12; i>=0; i--) {
            num = (int)(cnpj.charAt(i)- 48);
            sm = sm + (num * peso);
            peso = peso + 1;
            if (peso == 10)
                peso = 2;
        }

        r = sm % 11;
        if ((r == 0) || (r == 1))
            dig14 = '0';
        else dig14 = (char)((11-r) + 48);

        if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13))){
            _tvResultadoCNPJ.setText(CNPJ_VALIDO);
        } else{
            _tvResultadoCNPJ.setText(CNPJ_INVALIDO);
        }
    }
}
