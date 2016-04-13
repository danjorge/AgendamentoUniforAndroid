package br.com.danielsouza.ssa.activity;



import android.app.Fragment;
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

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;

import br.com.danielsouza.ssa.R;
import br.com.danielsouza.ssa.adapter.ListViewSideMenuAdapter;

/**
 * Created by Daniel Jorge on 01/12/2015
 */
public class MainActivity extends NavigationDrawer {

    private ArrayList<br.com.danielsouza.ssa.entity.MenuItem> listMenuItens = new ArrayList<>();
    private boolean doubleBackToExitPressedOnce = false;
    private boolean novaSolicitacao = false;
    private FloatingActionsMenu multipleActions;
    private Integer clickMenu = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        super.onCreateDrawer();

        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.changeable, homeFragment).commit();

        TypedArray navMenuIcon = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        String[] navMenuName = getResources().getStringArray(R.array.nav_drawer_items);

        listMenuItens.add(new br.com.danielsouza.ssa.entity.MenuItem(navMenuIcon.getResourceId(0, -1), navMenuName[0]));
        listMenuItens.add(new br.com.danielsouza.ssa.entity.MenuItem(navMenuIcon.getResourceId(1, -1), navMenuName[1]));
        listMenuItens.add(new br.com.danielsouza.ssa.entity.MenuItem(navMenuIcon.getResourceId(2, -1), navMenuName[2]));
        listMenuItens.add(new br.com.danielsouza.ssa.entity.MenuItem(navMenuIcon.getResourceId(3, -1), navMenuName[3]));
        listMenuItens.add(new br.com.danielsouza.ssa.entity.MenuItem(navMenuIcon.getResourceId(4, -1), navMenuName[4]));

        navMenuIcon.recycle();

        adapter = new ListViewSideMenuAdapter(this, listMenuItens);
        listView.setAdapter(adapter);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        multipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        RelativeLayout fabMenuLayout = (RelativeLayout) findViewById(R.id.layout_fab_menu);
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case 0:
                        HomeFragment homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.changeable, homeFragment).commit();
                        break;

                    case 1:
                        SolicitacoesFragment solicitacoes = new SolicitacoesFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.changeable, solicitacoes).commit();
                        novaSolicitacao = true;
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
    }

    public void finish(View view){
        dialogExitApplication(view);
    }

    public void updateMenuItens(int position){
        for (br.com.danielsouza.ssa.entity.MenuItem m : listMenuItens) {
            m.setSelected(false);
        }
        listMenuItens.get(position).setSelected(true);

        adapter.notifyDataSetChanged();

        if(position == 1) {
            clickMenu++;
        }

        if(position == 1 && clickMenu == 1){
            multipleActions.addButton(createButtonNewSolicitacao(novaSolicitacao));
        }
        else if(multipleActions.getChildAt(3).isClickable()){
            multipleActions.removeButton(actionNewSolicitacao);
            clickMenu--;
        }
        else if(clickMenu > 1){
            clickMenu--;
        }
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

    public FloatingActionButton createButtonNewSolicitacao(final Boolean novaSolicitacao) {
        actionNewSolicitacao = new FloatingActionButton(getBaseContext());
        actionNewSolicitacao.setTitle("Nova Solicitação");
        actionNewSolicitacao.setVisibility(novaSolicitacao ? View.VISIBLE : View.GONE);
        actionNewSolicitacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NovaSolicitacaoFragment novaSolicitacaoFragment = new NovaSolicitacaoFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.changeable, novaSolicitacaoFragment).commit();
            }
        });

        return actionNewSolicitacao;
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
                        Prefs.remove("matricula");
                        Prefs.remove("senha");
                        finish();
                        //error here. Intend to close the activtiy that created this dialog and has the error
                    }
                });

        AlertDialog alert = builder.create();
        // The dialog utils is outside an activity. Need to set owner
        alert.show();
    }
}
