package br.com.danielsouza.ssa.activity;



import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import br.com.danielsouza.ssa.R;
import br.com.danielsouza.ssa.adapter.ListViewSolicitacaoAdapter;
import br.com.danielsouza.ssa.entity.Solicitacao;
import br.com.danielsouza.ssa.entity.SolicitacoesResponse;
import br.com.danielsouza.ssa.restImpl.RestService;
import br.com.danielsouza.ssa.restInteface.RestInterface;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
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
        progressDialog.show();

        restInterface = RestService.getRestInterface();
        restInterface.getSolicitacaoJSON(Prefs.getString("matricula", "1413556"), new Callback<SolicitacoesResponse>() {
            @Override
            public void success(SolicitacoesResponse solicitacoesResponse, Response response) {
                for (Solicitacao s : solicitacoesResponse.getListaSolicitacao()) {
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

        listViewSolicitacao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), SolicitacaoDetailActivity.class);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        return v;
    }
}
