package br.unifor.pin.ssa.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.unifor.pin.ssa.R;
import br.unifor.pin.ssa.entity.Agendamento;

/**
 * Classe reponsavel por Adaptar a lista de agendamento no fragment
 * @author Daniel Jorge
 * Created by Daniel Jorge on 29/01/2016.
 */
public class ListViewAgendamentoAdapter extends BaseAdapter{

    private Context mContext;
    private List<Agendamento> listAgendmaneto;

    public ListViewAgendamentoAdapter(Context mContext, List<Agendamento> listAgendmaneto) {
        this.mContext = mContext;
        this.listAgendmaneto = listAgendmaneto;
    }

    @Override
    public int getCount() {
        return listAgendmaneto.size();
    }

    @Override
    public Object getItem(int position) {
        return listAgendmaneto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_row_agendamento, null);
        }

        TextView txtLabelNumAgendamento = (TextView) convertView.findViewById(R.id.label_num_agendamento);
        TextView txtLabelStatusAgendamento = (TextView) convertView.findViewById(R.id.label_status_agendamento);
        TextView txtLabelDataAgendamento = (TextView) convertView.findViewById(R.id.label_data_agendamento);
        ImageView imgView = (ImageView) convertView.findViewById(R.id.imgViewAgendamento);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        txtLabelNumAgendamento.setText("Nº do Agendamento: " + listAgendmaneto.get(position).getId());
        txtLabelStatusAgendamento.setText("Status: " + listAgendmaneto.get(position).getStatusAgendamento().getDescricao());
        txtLabelDataAgendamento.setText("Data: " +dateFormat.format(listAgendmaneto.get(position).getDataInicio()));
        imgView.setImageResource(R.drawable.arrow);

        return convertView;
    }
}
