package br.unifor.pin.ssa.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.unifor.pin.ssa.R;

/**
 * Classe responsavel por instaciar o fragment Home
 * @author Daniel Jorge
 * Created by Daniel Jorge on 01/02/2016.
 */
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
