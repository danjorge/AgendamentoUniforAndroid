package br.com.danielsouza.minhaaplicacao.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import java.util.ArrayList;

import br.com.danielsouza.minhaaplicacao.R;
import br.com.danielsouza.minhaaplicacao.adapter.ListViewSolicitacaoAdapter;
import br.com.danielsouza.minhaaplicacao.entity.Solicitacao;
import br.com.danielsouza.minhaaplicacao.entity.Status;
import br.com.danielsouza.minhaaplicacao.entity.Usuarios;


/**
 * Created by daniel.souza on 21/12/2015.
 */
public class SolicitacoesFragment extends Fragment {

    private ListView listViewSolicitacao;
    private ListViewSolicitacaoAdapter listViewSolicitacaoAdapter;
    private ArrayList<Solicitacao> listaSolicitacoes = new ArrayList<>();
    private Solicitacao solicitacao;

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

        solicitacao = new Solicitacao("Aula dia 01/02", "Descrição de solicitação teste", null, null, statusSolicitacao, usuario);

        listaSolicitacoes.add(solicitacao);

        listViewSolicitacao = (ListView) v.findViewById(R.id.listViewSolicitacoes);
        listViewSolicitacaoAdapter = new ListViewSolicitacaoAdapter(v.getContext(), listaSolicitacoes);
        listViewSolicitacao.setAdapter(listViewSolicitacaoAdapter);


        return v;
    }
}
