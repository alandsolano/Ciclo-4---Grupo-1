package com.example.delcampofresco;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.delcampofresco.databinding.ActivityMarketViewBinding;

public class MarketViewActivity extends Login implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMarketViewBinding binding;
    DrawerLayout drawer;
    NavController navController;
    TextView T1, NavUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMarketViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMarketView.toolbar);
        binding.appBarMarketView.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getString(R.string.addItem), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        navigationView.setItemIconTintList(null);

        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_fruits, R.id.nav_vegetables)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_market_view);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);

        // welcome (user)
        T1 = findViewById(R.id.text_welcome);
        String welcome = getString(R.string.welcome);
        String user = getIntent().getExtras().getString("user");
        if (user != "") welcome += " " + user;
        T1.setText(welcome);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.market_view, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        // welcome (user)
        String user = getIntent().getExtras().getString("user");
        if (user != "") {
            NavUser = findViewById(R.id.navUser);
            NavUser.setText(user);
            try {
                T1 = findViewById(R.id.text_welcome);
                //T1.setText(getString(R.string.welcome));
            } catch (Exception e){
                System.out.println("<<Current Navigation: " + navController.getCurrentDestination() + ">>");
            }
        }

        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case(R.id.nav_home):
                navController.navigate(R.id.nav_home);
                break;
            case(R.id.nav_fruits):
                navController.navigate(R.id.nav_fruits);
                break;
            case(R.id.nav_vegetables):
                navController.navigate(R.id.nav_vegetables);
                break;
            case(R.id.nav_logout):
                logout();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.search):

                // TODO: search
                showToast("Search");
                break;
            case (R.id.signOut):
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openFruits(View view) {
        navController.navigate(R.id.nav_fruits);
    }

    public void openVegetables(View view) {
        navController.navigate(R.id.nav_vegetables);
    }

    public void logout(){

        // TODO: sing out
        showToast("Sign Out");
    }

}