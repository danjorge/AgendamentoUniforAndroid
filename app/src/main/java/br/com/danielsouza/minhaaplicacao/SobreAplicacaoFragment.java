package br.com.danielsouza.minhaaplicacao;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by daniel.souza on 21/12/2015.
 */
public class SobreAplicacaoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sobre_aplicacao_activity, container, false);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) v.findViewById(R.id.main_collapsing);
        collapsingToolbar.setTitle("Agendamento Unifor");

        return v;
    }
}
