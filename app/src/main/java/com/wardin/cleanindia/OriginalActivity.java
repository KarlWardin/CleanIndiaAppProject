package com.wardin.cleanindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class OriginalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mfirebaseAuth;



    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;

    final ComplaintFragment complaintFragment = new ComplaintFragment();
    final LearnFragment learnFragment = new LearnFragment();
    final AccountFragment accountFragment = new AccountFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_original);
        setNavigationViewListener();

        mfirebaseAuth=FirebaseAuth.getInstance();

        drawerLayout=findViewById(R.id.drawer_layout);
        bottomNavigationView=findViewById(R.id.bottom_nav);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_closed);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, complaintFragment);
        fragmentTransaction.add(R.id.fragment_container, learnFragment);
        fragmentTransaction.add(R.id.fragment_container, accountFragment);

        fragmentTransaction.show(learnFragment);
        fragmentTransaction.hide(complaintFragment);
        fragmentTransaction.hide(accountFragment);
        fragmentTransaction.commit();

        if(mfirebaseAuth.getCurrentUser() == null){
              startActivity(new Intent(this,MainActivity.class));
              //Toast.makeText(this,"sent from original",Toast.LENGTH_SHORT).show();
              finish();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.complaint:
                        fragment_change(complaintFragment);
                        return true;
                    case R.id.account:
                        fragment_change(accountFragment);
                        return true;
                    case R.id.home:
                        fragment_change(learnFragment);
                        return true;

                }
                return true;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OriginalActivity.this,PostActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.o_menu, menu);
        return true;
    }



    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
        super.onBackPressed();
        }
    }


    private void fragment_change(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(accountFragment);
        fragmentTransaction.hide(learnFragment);
        fragmentTransaction.hide(complaintFragment);
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.out:
                Toast.makeText(this,"yo",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.sign_out: {
                Toast.makeText(this,"yoyo",Toast.LENGTH_SHORT).show();
                // mfirebaseAuth.signOut();
                // startActivity(new Intent(OriginalActivity.this,RegisterActivity.class));
                break;
            }
        }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void edit(){
        startActivity(new Intent(OriginalActivity.this,AccountActivity.class));
    }
}
