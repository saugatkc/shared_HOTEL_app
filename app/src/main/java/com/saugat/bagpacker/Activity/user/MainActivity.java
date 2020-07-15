package com.saugat.bagpacker.Activity.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.saugat.bagpacker.R;
import com.saugat.bagpacker.fragment.bookingFragment;
import com.saugat.bagpacker.fragment.homeFragment;
import com.saugat.bagpacker.fragment.profileFragment;
import com.saugat.bagpacker.fragment.savedFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    Fragment selectedFragment =null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(selected_menu);
        bottomNav.setSelectedItemId(R.id.nav_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new homeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selected_menu = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Menu menu = bottomNav.getMenu();
            menu.findItem(R.id.nav_home).setIcon(R.drawable.ic_home_black_24dp);
            menu.findItem(R.id.nav_booking).setIcon(R.drawable.ic_work_black_24dp);
            menu.findItem(R.id.nav_saved).setIcon(R.drawable.ic_favorite_black_24dp);
            menu.findItem(R.id.nav_profile).setIcon(R.drawable.ic_person_black_24dp);

            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new homeFragment();
                    menuItem.setIcon(R.drawable.ic_home_black_24dp);
                    break;
                case R.id.nav_booking:
                    selectedFragment = new bookingFragment();
                    menuItem.setIcon(R.drawable.ic_work_black_24dp);
                    break;
                case  R.id.nav_saved:
                    selectedFragment = new savedFragment();
                    menuItem.setIcon(R.drawable.ic_favorite_black_24dp);
                    break;
                case R.id.nav_profile:
                    selectedFragment = new profileFragment();
                    menuItem.setIcon(R.drawable.ic_person_black_24dp);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();

            if (selectedFragment != null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }
            return true;
        }
    };
}