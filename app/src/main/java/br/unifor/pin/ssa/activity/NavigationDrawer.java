package br.unifor.pin.ssa.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.picasso.Picasso;

import br.unifor.pin.ssa.R;
import br.unifor.pin.ssa.adapter.ListViewSideMenuAdapter;

/**
 * Classe de instancia do componente NavigationDrawer
 * Created by Daniel Jorge on 22/12/2015.
 */
public class NavigationDrawer extends AppCompatActivity {

    NavigationView navView;
    DrawerLayout drawerLayout;
    ListView listView;
    ListViewSideMenuAdapter adapter;
    protected FrameLayout frameLayout;
    FloatingActionButton actionB;
    FloatingActionButton actionA;
    FloatingActionButton actionNewSolicitacao;


    protected void onCreateDrawer() {

        listView = (ListView) findViewById(R.id.list_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);

        ImageView profileImage = (ImageView) findViewById(R.id.profile_image);
        Picasso.with(this).load(R.drawable.image_usuario).into(profileImage);

        TextView profileLogin = (TextView) findViewById(R.id.profile_login);
        profileLogin.setText(Prefs.getString("nome", "Daniel Jorge"));
    }


    void toogleMenu() {
        if(drawerLayout.isDrawerOpen(navView)){
            drawerLayout.closeDrawers();
        } else {
            drawerLayout.openDrawer(navView);
        }
    }
}