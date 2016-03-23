package br.com.danielsouza.minhaaplicacao.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import br.com.danielsouza.minhaaplicacao.R;
import br.com.danielsouza.minhaaplicacao.adapter.ListViewAgendamentoAdapter;
import br.com.danielsouza.minhaaplicacao.adapter.ListViewSolicitacaoAdapter;
import br.com.danielsouza.minhaaplicacao.entity.Solicitacao;
import br.com.danielsouza.minhaaplicacao.entity.Status;
import br.com.danielsouza.minhaaplicacao.entity.Usuarios;
import br.com.danielsouza.minhaaplicacao.extensions.RestService;
import br.com.danielsouza.minhaaplicacao.interfaces.RestInterface;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by daniel.souza on 21/12/2015.
 */
public class SolicitacoesFragment extends RestService {

    private ListView listViewSolicitacao;
    private ListViewSolicitacaoAdapter listViewSolicitacaoAdapter;
    private List<Solicitacao> listaSolicitacoes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.solicitacoes_activity, container, false);

        Status statusSolicitacao = new Status();
        statusSolicitacao.setId(1);
        statusSolicitacao.setDescricao("Cadastrado");

        Usuarios usuario = new Usuarios();
        usuario.setId(1);
        usuario.setMatricula("1413556");
        usuario.setNome("Daniel");
        usuario.setSenha("12345678");

        Solicitacao solicitacao = new Solicitacao(1,"Aula dia 01/02", "Descrição de solicitação teste", null, null, statusSolicitacao, usuario);
        Solicitacao solicitacao1 = new Solicitacao(2,"Reclamação Professor", "Estamos reclamando do professor magnus que não tem boa fluencia de ensino e nem consciencia de nota", null, null, statusSolicitacao, usuario);

        listaSolicitacoes.add(solicitacao);
        listaSolicitacoes.add(solicitacao1);

        listViewSolicitacao = (ListView) v.findViewById(R.id.listViewSolicitacoes);

        restInterface = RestService.getRestInterface();
        restInterface.getSolicitacaoJSON(new Callback<List<Solicitacao>>() {
            @Override
            public void success(List<Solicitacao> solicitacaoList, Response response) {
                listaSolicitacoes.addAll(solicitacaoList);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getView().getContext(), "FAILURE: " + error.getCause(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
        listViewSolicitacaoAdapter = new ListViewSolicitacaoAdapter(v.getContext(), listaSolicitacoes);


        listViewSolicitacao.setAdapter(listViewSolicitacaoAdapter);


        return v;
    }
}
