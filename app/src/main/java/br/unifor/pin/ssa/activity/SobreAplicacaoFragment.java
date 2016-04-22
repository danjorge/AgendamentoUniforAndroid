package br.unifor.pin.ssa.activity;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.unifor.pin.ssa.R;

/**
 * Classe de instancia do fragment Sobre a Aplicacao
 * @author Daniel Jorge
 * Created by Daniel Jorge on 21/12/2015.
 */
public class SobreAplicacaoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sobre_aplicacao, container, false);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) v.findViewById(R.id.main_collapsing);
        collapsingToolbar.setTitle("Agendamento Unifor");

        return v;
    }
}
