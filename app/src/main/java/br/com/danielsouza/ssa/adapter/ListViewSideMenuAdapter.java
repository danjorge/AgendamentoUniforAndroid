package br.com.danielsouza.ssa.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.danielsouza.ssa.R;
import br.com.danielsouza.ssa.entity.MenuItem;

/**
 * Created by daniel.souza on 16/12/2015.
 */
public class ListViewSideMenuAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<MenuItem> listMenuItens;

    public ListViewSideMenuAdapter(Context mContext, ArrayList<MenuItem> listMenuItens) {
        this.mContext = mContext;
        this.listMenuItens = listMenuItens;
    }


    @Override
    public int getCount() {
        return listMenuItens.size();
    }

    @Override
    public Object getItem(int position) {
        return listMenuItens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_sidemenu_layout, null);
        }

        LinearLayout rowWrapped = (LinearLayout) convertView.findViewById(R.id.rowWrapped);
        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtLabel = (TextView) convertView.findViewById(R.id.label);

        rowWrapped.setBackgroundColor(ContextCompat.getColor(mContext, listMenuItens.get(position).isSelected() ? R.color.blue : android.R.color.transparent));
        icon.setImageResource(listMenuItens.get(position).getIcon());
        txtLabel.setText(listMenuItens.get(position).getNome());
        txtLabel.setTextColor(ContextCompat.getColor(mContext, listMenuItens.get(position).isSelected() ? R.color.white : android.R.color.black));

        return convertView;
    }
}
