package br.com.danielsouza.ssa.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.danielsouza.ssa.R;

/**
 * Created by Daniel Jorge on 08/04/2016.
 */
public class SolicitacaoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao_detail);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_out, R.anim.left_in);
    }
}
