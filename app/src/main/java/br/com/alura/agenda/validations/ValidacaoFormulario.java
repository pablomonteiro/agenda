package br.com.alura.agenda.validations;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RatingBar;

import br.com.alura.agenda.FormularioActivity;
import br.com.alura.agenda.R;
import br.com.alura.agenda.modelo.Aluno;

/**
 * Created by pablo on 23/07/16.
 */
public class ValidacaoFormulario {

    private EditText nome;
    private EditText endereco;
    private EditText telefone;
    private EditText site;
    private RatingBar nota;
    private boolean formularioValido;

    public ValidacaoFormulario(FormularioActivity activity) {
        nome = (EditText) activity.findViewById(R.id.formulario_nome);
        endereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        telefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        site = (EditText) activity.findViewById(R.id.formulario_site);
        nota = (RatingBar) activity.findViewById(R.id.formulario_nota);
        formularioValido = true;
    }

    public boolean validaPreenchimentoDosCamposObrigatorios() {
        formularioValido = true;
        validaEditText(telefone);
        validaEditText(endereco);
        validaEditText(nome);
        return formularioValido;
    }

    private void validaEditText(EditText campo) {
        String campoDoFormulario = campo.getText().toString().trim();
        if(TextUtils.isEmpty(campoDoFormulario)) {
            campo.requestFocus();
            campo.setError("Campo Obrigatório");
            formularioValido = false;
        }
    }

}
