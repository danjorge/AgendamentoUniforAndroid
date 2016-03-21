package br.com.danielsouza.minhaaplicacao.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.danielsouza.minhaaplicacao.R;
import br.com.danielsouza.minhaaplicacao.entity.Solicitacao;

/**
 * Created by daniel.souza on 27/01/2016.
 */
public class ListViewSolicitacaoAdapter extends BaseAdapter {

    private Context mContext;
    private List<Solicitacao> listSolicitacao;

    public ListViewSolicitacaoAdapter(Context mContext, List<Solicitacao> listSolicitacao) {
        this.mContext = mContext;
        this.listSolicitacao = listSolicitacao;
    }

    @Override
    public int getCount() {
        return listSolicitacao.size();
    }

    @Override
    public Object getItem(int position) {
        return listSolicitacao.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_solicitacao_layout, null);
        }

        TextView txtLabelNumSolicitacao = (TextView) convertView.findViewById(R.id.label_num_solicitacao);
        TextView txtLabelStatusSolicitacao = (TextView) convertView.findViewById(R.id.label_status_solicitacao);
        TextView txtLabelAssuntoSolicitacao = (TextView) convertView.findViewById(R.id.label_assunto_solicitacao);
        ImageView imgView = (ImageView) convertView.findViewById(R.id.imgViewSolicitacao);

        txtLabelNumSolicitacao.setText("Nº da Solicitação: " + listSolicitacao.get(position).getId());
        txtLabelStatusSolicitacao.setText("Status: " +listSolicitacao.get(position).getStatusSolicitacao().getDescricao());
        txtLabelAssuntoSolicitacao.setText("Assunto: " +listSolicitacao.get(position).getAssunto());
        imgView.setImageResource(R.drawable.arrow);

        return convertView;
    }
}