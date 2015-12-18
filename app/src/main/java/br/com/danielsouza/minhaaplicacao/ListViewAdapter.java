package br.com.danielsouza.minhaaplicacao;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by daniel.souza on 16/12/2015.
 */
public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<MenuItem> listMenuItens;
    private TypedArray navMenuIcon;
    private String[] navMenuName;

    public ListViewAdapter(Context mContext) {
        this.mContext = mContext;
        listMenuItens = new ArrayList<>();
        setIconsAndName(mContext);
    }

    public void setIconsAndName(Context context){
        navMenuIcon = context.getResources().obtainTypedArray(R.array.nav_drawer_icons);
        navMenuName = context.getResources().getStringArray(R.array.nav_drawer_items);

        listMenuItens.add(new MenuItem(navMenuIcon.getResourceId(0, -1), navMenuName[0]));
        listMenuItens.add(new MenuItem(navMenuIcon.getResourceId(1, -1), navMenuName[1]));
        listMenuItens.add(new MenuItem(navMenuIcon.getResourceId(2, -1), navMenuName[2]));
        listMenuItens.add(new MenuItem(navMenuIcon.getResourceId(3, -1), navMenuName[3]));
        listMenuItens.add(new MenuItem(navMenuIcon.getResourceId(4, -1), navMenuName[4]));

        navMenuIcon.recycle();

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
            convertView = inflater.inflate(R.layout.row_layout, null);
        }

        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtLabel = (TextView) convertView.findViewById(R.id.label);

        icon.setImageResource(listMenuItens.get(position).getIcon());
        txtLabel.setText(listMenuItens.get(position).getNome());

        return convertView;
    }
}
