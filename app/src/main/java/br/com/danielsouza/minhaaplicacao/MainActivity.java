package br.com.danielsouza.minhaaplicacao;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;

public class MainActivity extends NavigationDrawer {

    private TypedArray navMenuIcon;
    private String[] navMenuName;
    private ArrayList<br.com.danielsouza.minhaaplicacao.MenuItem> listMenuItens = new ArrayList<>();
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        super.onCreateDrawer();

        navMenuIcon = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        navMenuName = getResources().getStringArray(R.array.nav_drawer_items);

        listMenuItens.add(new br.com.danielsouza.minhaaplicacao.MenuItem(navMenuIcon.getResourceId(0, -1), navMenuName[0]));
        listMenuItens.add(new br.com.danielsouza.minhaaplicacao.MenuItem(navMenuIcon.getResourceId(1, -1), navMenuName[1]));
        listMenuItens.add(new br.com.danielsouza.minhaaplicacao.MenuItem(navMenuIcon.getResourceId(2, -1), navMenuName[2]));
        listMenuItens.add(new br.com.danielsouza.minhaaplicacao.MenuItem(navMenuIcon.getResourceId(3, -1), navMenuName[3]));
        listMenuItens.add(new br.com.danielsouza.minhaaplicacao.MenuItem(navMenuIcon.getResourceId(4, -1), navMenuName[4]));

        navMenuIcon.recycle();

        adapter = new ListViewAdapter(this, listMenuItens);
        listView.setAdapter(adapter);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case 1:
                        SolicitacoesFragment2 solicitacoes2 = new SolicitacoesFragment2();
                        getSupportFragmentManager().beginTransaction().replace(R.id.changeable, solicitacoes2).commit();
                        break;

                    case 3:
                        SobreAplicacaoFragment sobreAplicacao = new SobreAplicacaoFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.changeable, sobreAplicacao).commit();
                        break;

                    case 4:
                        dialogExitApplication(view);
                        break;
                }
                updateMenuItens(position);
                toogleMenu();
            }
        });


        setTitle("");

        frameLayout = (FrameLayout) findViewById(R.id.changeable);

        View actionB = findViewById(R.id.action_b);
        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.unifor.br");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }

    public void finish(View view){
        dialogExitApplication(view);
    }

    public void updateMenuItens(int position){
        for(br.com.danielsouza.minhaaplicacao.MenuItem m : listMenuItens){
            m.setSelected(false);
        }
        listMenuItens.get(position).setSelected(true);

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toogleMenu();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(doubleBackToExitPressedOnce){
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        View view = findViewById(R.id.changeable);
        dialogExitApplication(view);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 10);
    }

    protected void dialogExitApplication(View view){
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage("Tem certeza que deseja sair?")
                .setCancelable(true)
                .setNegativeButton("NÃO", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("SIM", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        //error here. Intend to close the activtiy that created this dialog and has the error
                    }
                });

        AlertDialog alert = builder.create();
        // The dialog utils is outside an activity. Need to set owner
        alert.show();
    }
}
