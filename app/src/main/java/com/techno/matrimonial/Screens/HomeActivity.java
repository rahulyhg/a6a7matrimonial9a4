package com.techno.matrimonial.Screens;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.techno.matrimonial.Adapter.NavMenuAdapter;
import com.techno.matrimonial.Fragments.AboutUsFragment;
import com.techno.matrimonial.Fragments.BoysFragment;
import com.techno.matrimonial.Fragments.EventsFragment;
import com.techno.matrimonial.Fragments.FeedbackFragment;
import com.techno.matrimonial.Fragments.GirlsFragment;
import com.techno.matrimonial.Fragments.Send_BioDataFragment;
import com.techno.matrimonial.Fragments.SettingsFragment;
import com.techno.matrimonial.Fragments.SortlistFragment;
import com.techno.matrimonial.Global.AnimUtils;
import com.techno.matrimonial.Global.Constants;
import com.techno.matrimonial.Global.Global;
import com.techno.matrimonial.Listener.RecyclerItemClickListener;
import com.techno.matrimonial.Model.common.LeftMenuMain;
import com.techno.matrimonial.Model.common.LoginDetails;
import com.techno.matrimonial.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity  {
    Fragment fragment;
    //For Header
    SimpleDraweeView hdrProfileImg;
    RelativeLayout hdrRL;
    TextView hdrProfileName;
    //For Toolbar
    Toolbar toolbar;
    TextView tbTitle;
    private FragmentManager fragmentManager;
    boolean doubleBackToExitPressedOnce = false;

    RecyclerView rvCandidateMenu;
    NavMenuAdapter navMenuAdapter;
    ArrayList<LeftMenuMain> leftMenuMainArrayList;
    LeftMenuMain leftMenuMain;


    LoginDetails loginDetails;
    String userLoginResponse;
    Gson gson = new Gson();

    String profileUrl, tempUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Custom toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.color.colorWhite);
        tbTitle = (TextView) toolbar.findViewById(R.id.tbTitle);
        tbTitle.setText(getString(R.string.nav_girls));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        /*  Set the home as default*/
        fragment = new GirlsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();



        userLoginResponse = Global.getPreference("LoginDetails", "");
        loginDetails = gson.fromJson(userLoginResponse, LoginDetails.class);

        /****************************************Header View****************************************/
        View headerLayout = navigationView.getHeaderView(0);
        hdrRL = (RelativeLayout) headerLayout.findViewById(R.id.hdrRL);
        hdrProfileImg = (SimpleDraweeView) headerLayout.findViewById(R.id.hdrProfileImg);
        hdrProfileName = (TextView) headerLayout.findViewById(R.id.hdrProfileName);

        if (loginDetails != null) {

            tempUrl = loginDetails.getProfile_media();
            profileUrl = tempUrl.replace(Constants.LocalHost, Constants.DefaultIP);
            Uri imageUri = Uri.parse(profileUrl);
            hdrProfileImg.setImageURI(imageUri);
            hdrProfileName.setText(loginDetails.getFull_name());

            hdrRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //startActivity(new Intent(getApplicationContext(), CandidateDetailsActivity.class));
                }
            });
        }

        /***************************************for Nav Menu***************************************/
        //Recycler View For Player Menu
        rvCandidateMenu = (RecyclerView) findViewById(R.id.rvCandidateMenu);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        rvCandidateMenu.setLayoutManager(mLayoutManager1);

        leftMenuMainArrayList = new ArrayList<>();
        leftMenuMainArrayList.add(new LeftMenuMain(getString(R.string.nav_girls), R.drawable.ic_girl, ""));
        leftMenuMainArrayList.add(new LeftMenuMain(getString(R.string.nav_boys), R.drawable.ic_boys, ""));
        leftMenuMainArrayList.add(new LeftMenuMain(getString(R.string.nav_shortlist), R.drawable.ic_sortlist, "--"));
        leftMenuMainArrayList.add(new LeftMenuMain(getString(R.string.nav_event), R.drawable.ic_event, ""));
        leftMenuMainArrayList.add(new LeftMenuMain(getString(R.string.nav_biodata), R.drawable.ic_sendbio_data, ""));
        leftMenuMainArrayList.add(new LeftMenuMain(getString(R.string.nav_feedback), R.drawable.ic_feedback, ""));
        leftMenuMainArrayList.add(new LeftMenuMain(getString(R.string.nav_aboutus), R.drawable.ic_about, ""));
        leftMenuMainArrayList.add(new LeftMenuMain(getString(R.string.nav_settings), R.drawable.ic_settings, ""));

        navMenuAdapter = new NavMenuAdapter(leftMenuMainArrayList, getApplicationContext());
        rvCandidateMenu.setAdapter(navMenuAdapter);

        rvCandidateMenu.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                leftMenuMain = leftMenuMainArrayList.get(position);
                try {
                    if (leftMenuMain.getItemName().equals(getString(R.string.nav_girls))) {
                        changeFragment(new GirlsFragment());
                        DrawerLayout drawer_home = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer_home.closeDrawer(GravityCompat.START);
                    } else if (leftMenuMain.getItemName().equals(getString(R.string.nav_boys))) {
                        changeFragment(new BoysFragment());
                        DrawerLayout drawer_home = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer_home.closeDrawer(GravityCompat.START);
                    } else if (leftMenuMain.getItemName().equals(getString(R.string.nav_shortlist))) {
                        changeFragment(new SortlistFragment());
                        DrawerLayout drawer_home = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer_home.closeDrawer(GravityCompat.START);
                    } else if (leftMenuMain.getItemName().equals(getString(R.string.nav_event))) {
                        changeFragment(new EventsFragment());
                        DrawerLayout drawer_home = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer_home.closeDrawer(GravityCompat.START);
                    } else if (leftMenuMain.getItemName().equals(getString(R.string.nav_biodata))) {
                        changeFragment(new Send_BioDataFragment());
                        DrawerLayout drawer_home = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer_home.closeDrawer(GravityCompat.START);
                    } else if (leftMenuMain.getItemName().equals(getString(R.string.nav_feedback))) {
                        changeFragment(new FeedbackFragment());
                        DrawerLayout drawer_home = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer_home.closeDrawer(GravityCompat.START);
                    } else if (leftMenuMain.getItemName().equals(getString(R.string.nav_aboutus))) {
                        changeFragment(new AboutUsFragment());
                        DrawerLayout drawer_home = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer_home.closeDrawer(GravityCompat.START);
                    } else if (leftMenuMain.getItemName().equals(getString(R.string.nav_settings))) {
                        changeFragment(new SettingsFragment());
                        DrawerLayout drawer_home = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer_home.closeDrawer(GravityCompat.START);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));


        //for animation
        AnimUtils.activityenterAnim(HomeActivity.this);

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

    void changeFragment(Fragment fragment) {
        try {
            String backStateName = fragment.getClass().getName();
            String fragmentTag = backStateName;

            FragmentManager manager = getSupportFragmentManager();
            boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);


            if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) {
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment, fragmentTag);
                fragmentTransaction.addToBackStack(backStateName);
                fragmentTransaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
