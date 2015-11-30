package com.subliminals_central.subliminalscentral.subliminalscentral;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.subliminals_central.subliminalscentral.subliminalscentral.Adapters.TabsPagerFragmentAdapter;
import com.subliminals_central.subliminalscentral.subliminalscentral.UserActions.UserAuth;
import com.subliminals_central.subliminalscentral.subliminalscentral.UserActions.UserAuthActivity;
import com.subliminals_central.subliminalscentral.subliminalscentral.UserActions.UserData;
import com.subliminals_central.subliminalscentral.subliminalscentral.YouTube.VideosGetter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private VideosGetter getData;
    private SharedPreferences prefs;
    private TabLayout tabLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        getData = new VideosGetter();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        prefs = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

if(!prefs.getString("email", "").equalsIgnoreCase(""))
    UserData.isLogin=true;

        UserData.setUserEmail(prefs.getString("email", ""));
        UserData.setUserPasswd(prefs.getString("pass", ""));
        UserData.setUserPoints(prefs.getInt("points", 0));

        try {
            if (UserData.isLogin) {
                changeMenuItemUser();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        createTabLayout();

        if(UserData.isLogin)
            UpdateScoreStartup();
    }

    private void changeMenuItemUser(){
        Menu menu = navigationView.getMenu();
        MenuItem itemLogin = (MenuItem) menu.findItem(R.id.log_in);
        if(UserData.isLogin) {
            itemLogin.setTitle("Exit");
            MenuItem itemEmail = (MenuItem) menu.findItem(R.id.email_menu_item);
            itemEmail.setTitle(UserData.getUserEmail());
            MenuItem itemPoint = (MenuItem) menu.findItem(R.id.points_menu_item);
            itemPoint.setTitle(UserData.getUserPoints() + " points");
        } else {
            itemLogin.setTitle("Sign In|Up");
            MenuItem itemEmail = (MenuItem) menu.findItem(R.id.email_menu_item);
            itemEmail.setTitle("Not signed in");
            MenuItem itemPoint = (MenuItem) menu.findItem(R.id.points_menu_item);
            itemPoint.setTitle("points");
        }
    }

    private void UpdateScoreStartup(){
        String usrEmail = prefs.getString("email", "");
        String usrPass = prefs.getString("pass", "");
        try {
            String[] isOk = UserAuth.userAuth(usrEmail, usrPass);
            if(isOk[0].equalsIgnoreCase("100")){

                SharedPreferences.Editor editor = prefs.edit();

                int scores = Integer.parseInt(isOk[1]);

                editor.putString("email", usrEmail);
                editor.putString("pass", usrPass);
                editor.putInt("points", scores);
                editor.apply();

                UserData.setUserEmail(usrEmail);
                UserData.setUserPasswd(usrPass);
                UserData.setUserPoints(scores);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
    changeMenuItemUser();
        Toast.makeText(this, "points: "+prefs.getInt("points", 0), Toast.LENGTH_LONG).show();
        super.onResume();
    }

    private void createTabLayout() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        TabsPagerFragmentAdapter adapter = new TabsPagerFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

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



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.log_in) {
            if(!UserData.isLogin) {
                Intent intent = new Intent(this, UserAuthActivity.class);
                startActivity(intent);
            } else {
                UserData.setUserPoints(0);
                UserData.setUserEmail("");
                UserData.setUserPasswd("");
                UserData.isLogin=false;
                prefs.edit().putString("pass", "");
                prefs.edit().putString("email", "");
                prefs.edit().putInt("points", 0);
                prefs.edit().commit();
                changeMenuItemUser();
                Toast.makeText(this, "You have been Loged Out!", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_shop) {

            TabLayout.Tab tab = tabLayout.getTabAt(3);
            tab.select();

        } else if (id == R.id.nav_share) {

            TabLayout.Tab tab = tabLayout.getTabAt(1);
            tab.select();

        } else if (id == R.id.nav_send) {

            TabLayout.Tab tab = tabLayout.getTabAt(2);
            tab.select();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
