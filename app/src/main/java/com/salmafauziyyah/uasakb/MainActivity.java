package com.salmafauziyyah.uasakb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.salmafauziyyah.uasakb.Fragment.Kontak;
import com.salmafauziyyah.uasakb.Fragment.Logout;
import com.salmafauziyyah.uasakb.Fragment.Profil;
import com.salmafauziyyah.uasakb.Fragment.Teman;
//8-08-2019
//10116596
//Salma Fauziyyah
//AKB3
public class MainActivity extends AppCompatActivity  {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private TextView textView;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nv);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nvDrawer);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.lfragment, new Profil()).commit();
        }

        View header = nvDrawer.getHeaderView(0);

        TextView lblUser= (TextView) header.findViewById(R.id.txtuser);

        /* Men-set Label Nama dengan data User sedang login dari Preferences */
        lblUser.setText(Preferences.getLoggedInUser(getBaseContext()));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer (MenuItem menuItem){
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.mp:
                fragmentClass = Profil.class;
                break;
            case R.id.mt:
                fragmentClass = Teman.class;
                break;
            case R.id.mk:
                fragmentClass = Kontak.class;
                break;
            case R.id.ml:
                fragmentClass = Logout.class;
                //Menghapus Status login dan kembali ke Login Activity
                break;

            default:
                fragmentClass = Profil.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.lfragment, fragment).commit();
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }
    private void setupDrawerContent (NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }
}
