package io.rocketfox.overwatchinfo;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setItemIconTintList(null);

        drawer.setBackgroundResource(R.color.colorPrimary);

        View headerNav = navigationView.getHeaderView(0);
        TextView txtMenu = (TextView) headerNav.findViewById(R.id.navDrawer_HeaderText);
        Typeface overwatchfontCursive = Typeface.createFromAsset(getAssets(), "fonts/big_noodle_titling_oblique.ttf");
        txtMenu.setTypeface(overwatchfontCursive);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = NewsFragment.newInstance();

        setTitle("News");

        navigationView.getMenu().getItem(0).setChecked(true);
        fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;
        Class fragmentClass;

        int id = item.getItemId();

         if(id == R.id.nav_heroesselect)
           fragmentClass = HeroSelectFragment.class;
         else if(id == R.id.nav_patchnotes)
           fragmentClass = PatchNotesFragment.class;
         else if(id == R.id.navNews)
           fragmentClass = NewsFragment.class;
         else if(id == R.id.nav_mapselect)
           fragmentClass = MapsSelectFragment.class;
         else
           fragmentClass = PatchNotesFragment.class;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        }catch(Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();

        item.setChecked(true);
        setTitle(item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
