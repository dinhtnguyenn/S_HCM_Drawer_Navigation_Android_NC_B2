package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.fragment.AccountFragment;
import com.example.myapplication.fragment.HomeFragment;
import com.example.myapplication.fragment.SettingFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private FragmentManager fm;
    private FragmentTransaction ft_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        ft_add = fm.beginTransaction();
        ft_add.add(R.id.fragment_container_view,new HomeFragment());
        ft_add.commit();


        //code drawer navigation
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //sự kiện click item trong drawer navifation
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.fragment_account:
                        fm = getSupportFragmentManager();
                        ft_add = fm.beginTransaction();
                        ft_add.replace(R.id.fragment_container_view,new AccountFragment());
                        ft_add.commit();
                        break;
                    case R.id.fragment_setting:
                        fm = getSupportFragmentManager();
                        ft_add = fm.beginTransaction();
                        ft_add.replace(R.id.fragment_container_view,new SettingFragment());
                        ft_add.commit();
                        break;
                    case R.id.activity_info:
                        startActivity(new Intent(MainActivity.this, InfoActivity.class));
                        break;
                    case R.id.logout:
                        Toast.makeText(getApplicationContext(), "Dễ gì logout", Toast.LENGTH_SHORT).show();
                        break;
                }

                //đóng drawer navigation khi click item
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        View headerView = navigationView.getHeaderView(0);
        TextView txtName = headerView.findViewById(R.id.txtName);
        txtName.setText("Nguyễn Văn A");

    }

    //khai báo drawer navigation
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
           return true;
        }

        return super.onOptionsItemSelected(item);
    }
}