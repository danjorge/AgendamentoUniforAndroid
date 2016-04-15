package br.com.danielsouza.ssa.activity;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import br.com.danielsouza.ssa.R;
import br.com.danielsouza.ssa.entity.Solicitacao;
import br.com.danielsouza.ssa.entity.Status;
import br.com.danielsouza.ssa.entity.Usuarios;
import br.com.danielsouza.ssa.restImpl.RestService;
import br.com.danielsouza.ssa.restInteface.RestInterface;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Classe onde se instancia o Fragment para a criacao de uma nova solicitacao
 * Created by Daniel Jorge on 23/03/2016.
 */
public class NovaSolicitacaoFragment extends Fragment {

    private RestInterface restInterface;
    private EditText txtAssuntoSolicitacao;
    private EditText txtDescricaoSolicitacao;
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

        txtAssuntoSolicitacao = (EditText) v.findViewById(R.id.txt_assunto_nova_solicitacao);
        txtDescricaoSolicitacao = (EditText) v.findViewById(R.id.txt_dsc_nova_solicitacao);
        Button btnSalvarSolicitacao = (Button) v.findViewById(R.id.btn_salvar_solicitacao);




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
}
