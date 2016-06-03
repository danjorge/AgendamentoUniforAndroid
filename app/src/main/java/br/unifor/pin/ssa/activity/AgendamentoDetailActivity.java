package br.unifor.pin.ssa.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import br.unifor.pin.ssa.R;
import br.unifor.pin.ssa.entity.Agendamento;
import br.unifor.pin.ssa.utils.Encripta;

/**
 * Classe Responsavel por instaciar o detalhamento de um agendamento.
 * @author Daniel
 * Created by Daniel on 28/04/2016.
 */
public class AgendamentoDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento_detail);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setTitle("");

        Agendamento agendamento = (Agendamento) getIntent().getSerializableExtra("Agendamento");

        TextView txtAgendamentoId = (TextView) findViewById(R.id.txt_id_agendamento_detail);
        TextView txtDataInicio = (TextView) findViewById(R.id.txt_data_inicio_agendamento_detail);
        TextView txtDataFim = (TextView) findViewById(R.id.txt_data_fim_agendamento_detail);
        TextView tituloAgendamento = (TextView) findViewById(R.id.txt_titulo_agendamento_detail);
        TextView dscAgendamento = (TextView) findViewById(R.id.txt_dsc_agendamento_detail);
        TextView dscSolicitacaoAgendamento = (TextView) findViewById(R.id.txt_dsc_solicitacao_agendamento_detail);
        TextView txtSolicitante = (TextView) findViewById(R.id.txt_solicitante_agendamento_detail);
        TextView labelRespostaSolicitacaoNoAgendamento = (TextView) findViewById(R.id.label_resposta_solicitacao_agendamento_detail);
        TextView txtRespostaSolicitacaoNoAgendamento = (TextView) findViewById(R.id.txt_resposta_solicitacao_agendamento_detail);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);

        if (txtAgendamentoId != null) {
            txtAgendamentoId.setText(agendamento.getIdEvent().toString());
        }
        if (txtDataInicio != null) {
            txtDataInicio.setText(dateFormat.format(agendamento.getDataInicio()));
        }
        if (txtDataFim != null) {
            txtDataFim.setText(dateFormat.format(agendamento.getDataFim()));
        }
        if (tituloAgendamento != null) {
            tituloAgendamento.setText(agendamento.getTitulo().toUpperCase());
        }
        if (dscAgendamento != null) {
            dscAgendamento.setText(agendamento.getDscAgendamentoEvent().toUpperCase());
        }
        if (dscSolicitacaoAgendamento != null) {
            dscSolicitacaoAgendamento.setText(agendamento.getSolicitacao().getDescricao().toUpperCase());
        }
        if(txtSolicitante != null){
            txtSolicitante.setText(agendamento.getSolicitacao().getUsuario().getNome().toUpperCase());
        }
        if(labelRespostaSolicitacaoNoAgendamento != null){
            labelRespostaSolicitacaoNoAgendamento.setVisibility(agendamento.getSolicitacao().getRespostaSolicitacao() != null ? View.VISIBLE : View.GONE);
        }
        if(txtRespostaSolicitacaoNoAgendamento != null){
            txtRespostaSolicitacaoNoAgendamento.setVisibility(agendamento.getSolicitacao().getRespostaSolicitacao() != null ? View.VISIBLE : View.GONE);
            txtRespostaSolicitacaoNoAgendamento.setText(txtRespostaSolicitacaoNoAgendamento.getVisibility() == View.VISIBLE ? agendamento.getSolicitacao().getRespostaSolicitacao().toUpperCase() : "" );
        }
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hideSoftKeyboard();
        overridePendingTransition(R.anim.right_out, R.anim.left_in);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
