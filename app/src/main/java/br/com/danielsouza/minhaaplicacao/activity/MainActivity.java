package br.com.danielsouza.minhaaplicacao.activity;



import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;

import br.com.danielsouza.minhaaplicacao.R;
import br.com.danielsouza.minhaaplicacao.adapter.ListViewSideMenuAdapter;
import br.com.danielsouza.minhaaplicacao.entity.Solicitacao;
import br.com.danielsouza.minhaaplicacao.interfaces.RestInterface;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends NavigationDrawer {

    private TypedArray navMenuIcon;
    private String[] navMenuName;
    private ArrayList<br.com.danielsouza.minhaaplicacao.entity.MenuItem> listMenuItens = new ArrayList<>();
    private boolean doubleBackToExitPressedOnce = false;

    private ActionBar actionBar;

    private RelativeLayout fabMenuLayout;

    private DefaultFragment defaultFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        super.onCreateDrawer();

        defaultFragment = new DefaultFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.changeable, defaultFragment).commit();

        navMenuIcon = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        navMenuName = getResources().getStringArray(R.array.nav_drawer_items);

        listMenuItens.add(new br.com.danielsouza.minhaaplicacao.entity.MenuItem(navMenuIcon.getResourceId(0, -1), navMenuName[0]));
        listMenuItens.add(new br.com.danielsouza.minhaaplicacao.entity.MenuItem(navMenuIcon.getResourceId(1, -1), navMenuName[1]));
        listMenuItens.add(new br.com.danielsouza.minhaaplicacao.entity.MenuItem(navMenuIcon.getResourceId(2, -1), navMenuName[2]));
        listMenuItens.add(new br.com.danielsouza.minhaaplicacao.entity.MenuItem(navMenuIcon.getResourceId(3, -1), navMenuName[3]));
        listMenuItens.add(new br.com.danielsouza.minhaaplicacao.entity.MenuItem(navMenuIcon.getResourceId(4, -1), navMenuName[4]));

        navMenuIcon.recycle();

        adapter = new ListViewSideMenuAdapter(this, listMenuItens);
        listView.setAdapter(adapter);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case 0:
                        DefaultFragment defaultFragment = new DefaultFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.changeable, defaultFragment).commit();
                        break;

                    case 1:
                        SolicitacoesFragment solicitacoes = new SolicitacoesFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.changeable, solicitacoes).commit();
                        break;

                    case 2:
                        AgendamentoFragment agendamentoFragment = new AgendamentoFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.changeable, agendamentoFragment).commit();
                        break;

                    case 3:
                        SobreAplicacaoFragment sobreAplicacao = new SobreAplicacaoFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.changeable, sobreAplicacao).commit();
                        break;

                    case 4:
                        finish(view);
                        break;

                }
                updateMenuItens(position);
                toogleMenu();
            }
        });


        setTitle("");

        frameLayout = (FrameLayout) findViewById(R.id.changeable);

        actionB = findViewById(R.id.action_b);
        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.unifor.br");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        final FloatingActionsMenu multipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        fabMenuLayout = (RelativeLayout) findViewById(R.id.layout_fab_menu);
        fabMenuLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (multipleActions.isExpanded()) {
                    multipleActions.collapse();
                    return true;
                }
                return false;
            }
        });

    }

    public void finish(View view){
        dialogExitApplication(view);
    }

    public void updateMenuItens(int position){
        for(br.com.danielsouza.minhaaplicacao.entity.MenuItem m : listMenuItens){
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
        finish(view);

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
