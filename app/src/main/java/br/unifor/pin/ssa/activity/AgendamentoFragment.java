package br.unifor.pin.ssa.activity;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.unifor.pin.ssa.R;
import br.unifor.pin.ssa.adapter.ListViewAgendamentoAdapter;
import br.unifor.pin.ssa.entity.Agendamento;
import br.unifor.pin.ssa.entity.AgendamentoResponse;
import br.unifor.pin.ssa.restImpl.RestService;
import br.unifor.pin.ssa.restInteface.RestInterface;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Classe responsavel por instanciar o fragment de Agendamento
 * @author Daniel Jorge
 * Created by Daniel Jorge on 27/01/2016.
 */
public class AgendamentoFragment extends Fragment {

    private List<Agendamento> listAgendamento = new ArrayList<>();
    private ListView listViewAgendamento;
    private ListViewAgendamentoAdapter listViewAgendamentoAdapter;
    private RestInterface restInterface;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lista_agendamento, container, false);

        listViewAgendamento = (ListView) v.findViewById(R.id.listViewAgendamento);

        //Instancia do dialog para bloqueio e informe de buscar de informacao para o usuario
        progressDialog = new ProgressDialog(v.getContext());
        progressDialog.setTitle("SSA Mobile");
        progressDialog.setMessage("Aguarde");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //Recupera a instancia da interface no fragment
        restInterface = RestService.getRestInterface();

        //Chama o serviço e retorna uma lista com os agendamentos quando o fragment e criado.
        restInterface.getAgendamentoJSON(Prefs.getString("matricula", "1413556"), new Callback<AgendamentoResponse>() {
            @Override
            public void success(AgendamentoResponse agendamentoResponse, Response response) {
                //Caso a lista esteja nula, apresenta um Toast informando que o usuario não possui agendamentos cadastrados
                //e retorna.
                if(agendamentoResponse == null){
                    progressDialog.dismiss();
                    Toast.makeText(getView().getContext(), "Você não tem agendamentos", Toast.LENGTH_LONG).show();
                    return;
                }

                //Percorre a lista do servico e adiciona o objeto a lista do fragment para adaptacao
                for (Agendamento a : agendamentoResponse.getListaAgendamento()) {
                    if (a.getSolicitacao() != null) {

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
                        String dataInicio = dateFormat.format(a.getDataInicio());
                        String dataFim = dateFormat.format(a.getDataFim());
                        try {
                            a.setDataInicio(dateFormat.parse(dataInicio));
                            a.setDataFim(dateFormat.parse(dataFim));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        listAgendamento.add(a);
                    }
                }
                progressDialog.dismiss();
                listViewAgendamentoAdapter = new ListViewAgendamentoAdapter(getView().getContext(), listAgendamento);
                listViewAgendamento.setAdapter(listViewAgendamentoAdapter);

            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                Toast.makeText(getView().getContext(), "FAILURE: " + error.getMessage(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });

        return v;
    }
}