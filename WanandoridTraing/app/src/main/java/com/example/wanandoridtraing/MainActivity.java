package com.example.wanandoridtraing;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.wanandoridtraing.base.BaseActivity;
import com.example.wanandoridtraing.home.HomeFragment;
import com.example.wanandoridtraing.widget.view.ButtonNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseActivity{

    private Toolbar toolbar;
    private ButtonNavigationView main_bottom_navigation;
    private NavigationView nav_view;
    private DrawerLayout drawer_layout;
    private FrameLayout main_fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        main_bottom_navigation = (ButtonNavigationView) findViewById(R.id.main_bottom_navigation);
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        main_fragment_container = findViewById(R.id.main_fragment_container);

        main_bottom_navigation.setOnTabChangedListener(new ButtonNavigationView.OnTabCheckedChangedListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, buttonView.getText().toString(), Toast.LENGTH_SHORT).show();
                int id = buttonView.getId();
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                switch (id) {
                    case R.id.main_button_tab_home:
                        addFragment(supportFragmentManager, HomeFragment.class,R.id.main_fragment_container,null );
                        break;
                }
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.app_name, R.string.app_name);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();


    }


}
