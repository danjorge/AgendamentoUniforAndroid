package br.com.danielsouza.minhaaplicacao;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by daniel.souza on 21/12/2015.
 */
public class SolicitacoesFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.solicitacoes_activity2, container, false);
        TextView textView = (TextView) v.findViewById(R.id.xxtView);
        textView.setText("Nação XXT");
        return v;
    }
}
