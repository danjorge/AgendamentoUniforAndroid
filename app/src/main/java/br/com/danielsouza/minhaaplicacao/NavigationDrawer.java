package br.com.danielsouza.minhaaplicacao;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by daniel.souza on 22/12/2015.
 */
public class NavigationDrawer extends AppCompatActivity {

    NavigationView navView;
    DrawerLayout drawerLayout;
    ListView listView;
    String[] opcoes = {"opcao1", "opcao2", "opcao3", "opcao4", "opcao5"};
    ListViewAdapter adapter;

    protected void onCreateDrawer() {

        listView = (ListView) findViewById(R.id.list_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);

        adapter = new ListViewAdapter(this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent solicitacoesActivity = new Intent(view.getContext(), SolicitacoesNavigation.class);
                        startActivity(solicitacoesActivity);
                        break;
                }
                toogleMenu();
            }
        });

        ImageView profileImage = (ImageView) findViewById(R.id.profile_image);
        Picasso.with(this).load(R.drawable.image_usuario).into(profileImage);

        TextView profileLogin = (TextView) findViewById(R.id.profile_login);
        profileLogin.setText("danjorge@gmail.com");

        ImageView imgView = (ImageView)findViewById(R.id.img_drawer);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleMenu();
            }
        });
    }

    void toogleMenu() {
        if(drawerLayout.isDrawerOpen(navView)){
            drawerLayout.closeDrawers();
        } else {
            drawerLayout.openDrawer(navView);
        }
    }
}
