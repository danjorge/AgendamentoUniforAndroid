package br.unifor.pin.ssa.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import br.unifor.pin.ssa.R;
import br.unifor.pin.ssa.entity.Agendamento;
import br.unifor.pin.ssa.utils.Encripta;

/**
 * Created by Daniel on 28/04/2016.
 */
public class AgendamentoDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento_detail);


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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_out, R.anim.left_in);
    }
}
