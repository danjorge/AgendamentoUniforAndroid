package br.com.danielsouza.ssa.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.danielsouza.ssa.R;

/**
 * Created by daniel.souza on 23/03/2016.
 */
public class NovaSolicitacaoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nova_solicitacao, container, false);
    }
}
