package br.unifor.pin.ssa.activity;



import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import br.unifor.pin.ssa.R;
import br.unifor.pin.ssa.adapter.ListViewSolicitacaoAdapter;
import br.unifor.pin.ssa.entity.Solicitacao;
import br.unifor.pin.ssa.restImpl.RestService;
import br.unifor.pin.ssa.restInteface.RestInterface;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Classe responsavel por instanciar o fragment de Solicitacoes
 * @author Daniel Jorge
 * Created by Daniel Jorge on 21/12/2015.
 */
public class SolicitacoesFragment extends Fragment {

    private ListView listViewSolicitacao;
    private ListViewSolicitacaoAdapter listViewSolicitacaoAdapter;
    private List<Solicitacao> listaSolicitacoes = new ArrayList<>();
    private RestInterface restInterface;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lista_solicitacoes, container, false);

        listViewSolicitacao = (ListView) v.findViewById(R.id.listViewSolicitacoes);

        progressDialog = new ProgressDialog(v.getContext());
        progressDialog.setTitle("SSA Mobile");
        progressDialog.setMessage("Aguarde");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //Recupera a instancia da interface no fragment
        restInterface = RestService.getRestInterface();
        //Chama o servico e retorna uma lista com as solicitacoes quando o fragment e criado.
        restInterface.getSolicitacaoJSON(Prefs.getString("matricula", "1413556"), new Callback<List<Solicitacao>>() {
            @Override
            public void success(List<Solicitacao> solicitacaos, Response response) {
                for (Solicitacao s : solicitacaos) {
                    if (s != null) {
                        listaSolicitacoes.add(s);
                    }
                }
                progressDialog.dismiss();
                listViewSolicitacaoAdapter = new ListViewSolicitacaoAdapter(getView().getContext(), listaSolicitacoes);
                listViewSolicitacao.setAdapter(listViewSolicitacaoAdapter);

            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                Toast.makeText(getView().getContext(), "FAILURE: " + error.getMessage(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });

        //Passa o objeto para outras activities
        listViewSolicitacao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), SolicitacaoDetailActivity.class);
                Solicitacao solicitacao = listaSolicitacoes.get(position);
                intent.putExtra("Solicitacao", solicitacao);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
                      });

        return v;
    }
}
