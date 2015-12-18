package br.com.danielsouza.minhaaplicacao;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NavigationView navView;
    DrawerLayout drawerLayout;
    ListView listView;
    String[] opcoes = {"opcao1", "opcao2", "opcao3", "opcao4", "opcao5"};
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("");

        listView = (ListView) findViewById(R.id.list_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);

        adapter = new ListViewAdapter(this);
        listView.setAdapter(adapter);

        ImageView profileImage = (ImageView) findViewById(R.id.profile_image);
        Picasso.with(this).load(R.drawable.image_usuario).into(profileImage);

        TextView profileLogin = (TextView) findViewById(R.id.profile_login);
        profileLogin.setText("danjorge@gmail.com");

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Item: " + opcoes[position], Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();
            }
        });

        ImageView imgView = (ImageView)findViewById(R.id.img_drawer);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleMenu();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    private void toogleMenu() {
        if(drawerLayout.isDrawerOpen(navView)){
            drawerLayout.closeDrawers();
        } else {
            drawerLayout.openDrawer(navView);
        }
    }
}
