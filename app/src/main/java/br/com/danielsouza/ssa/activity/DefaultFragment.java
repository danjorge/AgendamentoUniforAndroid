package br.com.danielsouza.ssa.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.danielsouza.ssa.R;

/**
 * Created by daniel.souza on 01/02/2016.
 */
public class DefaultFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.defaul_activity, container, false);
    }
}