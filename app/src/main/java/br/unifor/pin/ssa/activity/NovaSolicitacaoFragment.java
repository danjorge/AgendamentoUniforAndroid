package br.unifor.pin.ssa.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import br.unifor.pin.ssa.R;
import br.unifor.pin.ssa.entity.Solicitacao;
import br.unifor.pin.ssa.entity.Status;
import br.unifor.pin.ssa.entity.Usuarios;
import br.unifor.pin.ssa.restImpl.RestService;
import br.unifor.pin.ssa.restInteface.RestInterface;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Classe onde se instancia o Fragment para a criacao de uma nova solicitacao
 * Created by Daniel Jorge on 23/03/2016.
 */
public class NovaSolicitacaoFragment extends Fragment {

    private RestInterface restInterface;
    private TextInputLayout inputLayoutAssunto, inputLayoutDscSolicitacao;
    private EditText txtAssuntoSolicitacao, txtDescricaoSolicitacao;
    private ProgressDialog progressDialog;

    /**
     * Metodo responsavel pela criacao do Fragment quando inicializado.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nova_solicitacao, container, false);

        inputLayoutAssunto = (TextInputLayout) v.findViewById(R.id.input_layout_assunto_nova_solicitacao);
        inputLayoutDscSolicitacao = (TextInputLayout) v.findViewById(R.id.input_layout_dsc_nova_solicitacao);
        txtAssuntoSolicitacao = (EditText) v.findViewById(R.id.txt_assunto_nova_solicitacao);
        txtDescricaoSolicitacao = (EditText) v.findViewById(R.id.txt_dsc_nova_solicitacao);
        Button btnSalvarSolicitacao = (Button) v.findViewById(R.id.btn_salvar_solicitacao);

        txtAssuntoSolicitacao.addTextChangedListener(new MyTextWatcher(txtAssuntoSolicitacao));
        txtDescricaoSolicitacao.addTextChangedListener(new MyTextWatcher(txtDescricaoSolicitacao));

        /**
         * Instancia de interface que recupera o serviço para utilização na classe.
         */
        restInterface = RestService.getRestInterface();

        /**
         * Metodo responsavel por esperar uma acao do botao setado no Fragment.
         */
        btnSalvarSolicitacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateForm()){
                    return;
                }

                progressDialog = new ProgressDialog(v.getContext());
                progressDialog.setTitle("SSA Mobile");
                progressDialog.setMessage("Aguarde");
                progressDialog.setCancelable(false);
                progressDialog.show();

                //Instancia de usuario para setar na nova solicitacao.
                Usuarios u = new Usuarios();
                //Setamos o id para o persistence do servico
                //Utilizamos o EasyPreferences para buscar o id trazido via servico no login de usuario
                u.setId(Prefs.getInt("id", 1));

                //Instancia de status para definir o status inicial da solicitacao
                Status st = new Status();
                //Seta o id
                st.setId(1);

                //Instancia de uma nova solicitacao
                final Solicitacao sol = new Solicitacao();
                //Seta o usuario
                sol.setUsuario(u);
                //Seta o assunto escrito no fragment
                sol.setAssunto(txtAssuntoSolicitacao.getText().toString());
                //Seta a descricao escrita no fragment
                sol.setDescricao(txtDescricaoSolicitacao.getText().toString());
                //Seta o status da Solicitacao
                sol.setStatusSolicitacao(st);
                /**
                 * Utiliza a instancia da interface para buscar o metodo do serviço que salva a solicitacao
                 * Metodo POST que envia a solicitacao no corpo da requisicao e retorna SUCESSO ou FALHA.
                 */
                restInterface.salvarSolicitacao(sol, new Callback<Solicitacao>() {
                    @Override
                    public void success(Solicitacao solicitacao, Response response) {
                        progressDialog.dismiss();
                        Toast.makeText(getView().getContext(), "Solicitação salva com sucesso", Toast.LENGTH_LONG).show();
                        txtAssuntoSolicitacao.getText().clear();
                        txtDescricaoSolicitacao.getText().clear();
                        hideSoftKeyboard();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getView().getContext(), "FAILURED: " +error.getMessage(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                });
            }
        });

        return v;
    }

    private boolean validateForm() {
        boolean validateAssunto = validateAssunto();
        boolean validateDscSolicitacao = validateDscSolicitacao();
        return validateAssunto && validateDscSolicitacao;
    }

    private boolean validateAssunto() {
        if(txtAssuntoSolicitacao.getText().toString().trim().isEmpty()){
           inputLayoutAssunto.setError(getString(R.string.err_msg_assunto));
            requestFocus(txtAssuntoSolicitacao);
            return false;
        } else {
            inputLayoutAssunto.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDscSolicitacao() {
        if(txtDescricaoSolicitacao.getText().toString().trim().isEmpty()){
            inputLayoutDscSolicitacao.setError(getString(R.string.err_msg_dsc_solicitacao));
            requestFocus(txtDescricaoSolicitacao);
            return false;
        } else {
            inputLayoutDscSolicitacao.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view){
        if(view.requestFocus()){
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void hideSoftKeyboard() {
        if(getActivity().getCurrentFocus()!=null) {
            getContext();
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }

    private class MyTextWatcher implements TextWatcher{

        private View view;

        public MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()){
                case R.id.txt_assunto_nova_solicitacao:
                    validateAssunto();
                    break;
                case R.id.txt_dsc_nova_solicitacao:
                    validateDscSolicitacao();
                    break;
            }
        }
    }
}
