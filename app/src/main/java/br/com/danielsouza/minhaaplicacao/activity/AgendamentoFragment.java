package br.com.danielsouza.minhaaplicacao.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.danielsouza.minhaaplicacao.R;
import br.com.danielsouza.minhaaplicacao.adapter.ListViewAgendamentoAdapter;
import br.com.danielsouza.minhaaplicacao.entity.Agendamento;
import br.com.danielsouza.minhaaplicacao.entity.Solicitacao;
import br.com.danielsouza.minhaaplicacao.entity.Status;
import br.com.danielsouza.minhaaplicacao.entity.Usuarios;

/**
 * Created by daniel.souza on 27/01/2016.
 */
public class AgendamentoFragment extends Fragment {

    private List<Agendamento> listAgendamento;
    private ListView listViewAgendamento;
    private ListViewAgendamentoAdapter listViewAgendamentoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.agendamento_activity, container, false);

        Date dataInicio = new Date();

        Date dataFim = new Date();

        Status statusSolicitacao = new Status();
        statusSolicitacao.setId(1);
        statusSolicitacao.setDescricao("Cadastrado");

        Usuarios usuario = new Usuarios();
        usuario.setId(1);
        usuario.setMatricula("1413556");
        usuario.setNome("Daniel");
        usuario.setSenha("12345678");

        Solicitacao solicitacao = new Solicitacao(1,"Aula dia 01/02", "Descrição de solicitação teste", "Precisamos conversar, marcarei um agendamneto", null, statusSolicitacao, usuario);

        Status statusAgendamento = new Status();
        statusAgendamento.setId(1);
        statusAgendamento.setDescricao("Agendamento Cadastrado");

        Agendamento agendamento = new Agendamento(1, "Aula dia 01/02", dataInicio, dataFim, solicitacao, statusAgendamento);

        listAgendamento = new ArrayList<>();
        listAgendamento.add(agendamento);

        listViewAgendamentoAdapter = new ListViewAgendamentoAdapter(v.getContext(), listAgendamento);
        listViewAgendamento = (ListView) v.findViewById(R.id.listViewAgendamento);
        listViewAgendamento.setAdapter(listViewAgendamentoAdapter);

        return v;
    }
}
