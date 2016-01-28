package br.com.danielsouza.minhaaplicacao.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.danielsouza.minhaaplicacao.R;

/**
 * Created by daniel.souza on 27/01/2016.
 */
public class AgendamentoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.agendamento_activity, container, false);
        return v;
    }
}
