package br.com.danielsouza.ssa.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.com.danielsouza.ssa.R;
import br.com.danielsouza.ssa.entity.Solicitacao;

/**
 * Created by Daniel Jorge on 08/04/2016.
 */
public class SolicitacaoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao_detail);

        Solicitacao solicitacao = (Solicitacao) getIntent().getSerializableExtra("Solicitacao");

        TextView txtIdSolicitacao = (TextView) findViewById(R.id.txt_id_solicitacao);
        TextView txtStatusSolicitacao = (TextView) findViewById(R.id.txt_status_solicitacao_detail);
        TextView txtSolicitante = (TextView) findViewById(R.id.txt_solicitante_detail);
        TextView txtAssuntoSolicitacao = (TextView) findViewById(R.id.txt_assunto_solicitacao_detail);
        TextView txtDscSolicitacao = (TextView) findViewById(R.id.txt_dsc_solicitacao_detail);

        txtIdSolicitacao.setText(solicitacao.getId().toString());
        txtStatusSolicitacao.setText(solicitacao.getStatusSolicitacao().getDescricao().toUpperCase());
        txtSolicitante.setText(solicitacao.getUsuario().getNome());
        txtAssuntoSolicitacao.setText(solicitacao.getAssunto().toUpperCase());
        txtDscSolicitacao.setText(solicitacao.getDescricao().toUpperCase());


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_out, R.anim.left_in);
    }
}
