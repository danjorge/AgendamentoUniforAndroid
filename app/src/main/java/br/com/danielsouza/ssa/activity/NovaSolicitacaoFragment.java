package br.com.danielsouza.ssa.activity;


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
 * Created by daniel.souza on 23/03/2016.
 */
public class NovaSolicitacaoFragment extends Fragment {

    private RestInterface restInterface;
    private EditText txtAssuntoSolicitacao;
    private EditText txtDescriçãoSolicitacao;
    private Status statusSolicitacao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nova_solicitacao, container, false);

        txtAssuntoSolicitacao = (EditText) v.findViewById(R.id.txt_assunto_nova_solicitacao);
        txtDescriçãoSolicitacao = (EditText) v.findViewById(R.id.txt_dsc_nova_solicitacao);
        Button btnSalvarSolicitacao = (Button) v.findViewById(R.id.btn_salvar_solicitacao);

        restInterface = RestService.getRestInterface();
        btnSalvarSolicitacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuarios u = new Usuarios();
                u.setId(Prefs.getInt("id", 1));

                Status st = new Status();
                st.setId(1);

                final Solicitacao sol = new Solicitacao();
                sol.setUsuario(u);
                sol.setAssunto(txtAssuntoSolicitacao.getText().toString());
                sol.setDescricao(txtDescriçãoSolicitacao.getText().toString());
                sol.setStatusSolicitacao(st);
                restInterface.salvarSolicitacao(sol, new Callback<Solicitacao>() {
                    @Override
                    public void success(Solicitacao solicitacao, Response response) {
                        Toast.makeText(getView().getContext(), "Solicitação salva com sucesso", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getView().getContext(), "FAILURED: " +error.getMessage(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                });
            }
        });

        return v;
    }
}
