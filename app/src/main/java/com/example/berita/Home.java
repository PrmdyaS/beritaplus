package com.example.berita;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements BeritaFragment.OnFragmentListener, ProfileFragment.OnFragmentListener, DashboardFragment.OnFragmentListener, MenuFragment.OnFragmentListener{

    private String panggilFragment = "Berita";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        PanggilFragment();
    }

    public void PanggilFragment() {
        Button btnHome=findViewById(R.id.btnHome);
        Button btnDashboard=findViewById(R.id.btnDashboard);
        Button btnMenu=findViewById(R.id.btnMenu);
        Button btnProfile=findViewById(R.id.btnProfile);
        if (panggilFragment == "Berita") {
            BeritaFragment beritaFragment=BeritaFragment.newInstance();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment, beritaFragment)
                    .addToBackStack(null).commit();
            btnHome.setCompoundDrawableTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black)));
            btnHome.setTextColor(ContextCompat.getColor(this, R.color.black));
            btnDashboard.setCompoundDrawableTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray)));
            btnDashboard.setTextColor(ContextCompat.getColor(this, R.color.gray));
            btnMenu.setCompoundDrawableTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray)));
            btnMenu.setTextColor(ContextCompat.getColor(this, R.color.gray));
            btnProfile.setCompoundDrawableTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray)));
            btnProfile.setTextColor(ContextCompat.getColor(this, R.color.gray));
        } else if (panggilFragment == "Profile") {
            ProfileFragment profileFragment=ProfileFragment.newInstance();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment, profileFragment)
                    .addToBackStack(null).commit();
            btnHome.setCompoundDrawableTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray)));
            btnHome.setTextColor(ContextCompat.getColor(this, R.color.gray));
            btnDashboard.setCompoundDrawableTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray)));
            btnDashboard.setTextColor(ContextCompat.getColor(this, R.color.gray));
            btnMenu.setCompoundDrawableTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray)));
            btnMenu.setTextColor(ContextCompat.getColor(this, R.color.gray));
            btnProfile.setCompoundDrawableTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black)));
            btnProfile.setTextColor(ContextCompat.getColor(this, R.color.black));
        } else if (panggilFragment == "Dashboard") {
            DashboardFragment dashboardFragment=DashboardFragment.newInstance();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment, dashboardFragment)
                    .addToBackStack(null).commit();
            btnHome.setCompoundDrawableTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray)));
            btnHome.setTextColor(ContextCompat.getColor(this, R.color.gray));
            btnDashboard.setCompoundDrawableTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black)));
            btnDashboard.setTextColor(ContextCompat.getColor(this, R.color.black));
            btnMenu.setCompoundDrawableTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray)));
            btnMenu.setTextColor(ContextCompat.getColor(this, R.color.gray));
            btnProfile.setCompoundDrawableTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray)));
            btnProfile.setTextColor(ContextCompat.getColor(this, R.color.gray));
        } else if (panggilFragment == "Menu") {
            MenuFragment menuFragment=MenuFragment.newInstance();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment, menuFragment)
                    .addToBackStack(null).commit();
            btnHome.setCompoundDrawableTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray)));
            btnHome.setTextColor(ContextCompat.getColor(this, R.color.gray));
            btnDashboard.setCompoundDrawableTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray)));
            btnDashboard.setTextColor(ContextCompat.getColor(this, R.color.gray));
            btnMenu.setCompoundDrawableTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black)));
            btnMenu.setTextColor(ContextCompat.getColor(this, R.color.black));
            btnProfile.setCompoundDrawableTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray)));
            btnProfile.setTextColor(ContextCompat.getColor(this, R.color.gray));
        }
    }

    public void OpenPageBerita(View view) {
        Intent intent = new Intent(Home.this, PageBerita.class);
        startActivity(intent);
    }

    public void openBookmark(View view) {
        Intent intent = new Intent(Home.this, Bookmark.class);
        startActivity(intent);
    }

    public void openLogin(View view) {
        Intent intent = new Intent(Home.this, Login.class);
        startActivity(intent);
    }

    public void openSignUp(View view) {
        Intent intent = new Intent(Home.this, SignUp.class);
        startActivity(intent);
    }

    public void openHome(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment);
        if (currentFragment != null) {
            fragmentManager.beginTransaction().remove(currentFragment).commit();
        }
        panggilFragment = "Berita";
        PanggilFragment();
    }

    public void openProfile(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment);
        if (currentFragment != null) {
            fragmentManager.beginTransaction().remove(currentFragment).commit();
        }
        panggilFragment = "Profile";
        PanggilFragment();
    }

    public void openDashboard(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment);
        if (currentFragment != null) {
            fragmentManager.beginTransaction().remove(currentFragment).commit();
        }
        panggilFragment = "Dashboard";
        PanggilFragment();
    }

    public void openMenu(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment);
        if (currentFragment != null) {
            fragmentManager.beginTransaction().remove(currentFragment).commit();
        }
        panggilFragment = "Menu";
        PanggilFragment();
    }

    public void openSearch(View view) {
        Intent intent = new Intent(Home.this, Search.class);
        startActivity(intent);
    }

    public void openListUser(View view) {
        Intent intent = new Intent(Home.this, ListUser.class);
        startActivity(intent);
    }

    public void openListBerita(View view) {
        Intent intent = new Intent(Home.this, ListBerita.class);
        startActivity(intent);
    }

    public void openListKategoriBerita(View view) {
        Intent intent = new Intent(Home.this, ListKategoriBerita.class);
        startActivity(intent);
    }
}