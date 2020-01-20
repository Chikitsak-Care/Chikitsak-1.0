package com.vivek.chikitsak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction ;
    RecyclerView recyclerView ;
    List<item> mData;
    MenuAdapter adapter;

    public static final int DASHBOARD_CODE = 0;
    public static final int MEDICAL_HISTORY = 0;
    public static final int MY_RECORDS = 0;
    public static final int NEAREST_BLOOD_BANK = 0;
    public static final int GOVT_MED_SCHEMES = 0;
    public static final int REMINDERS = 5;
    public static final int FAQ = 6;
    public static final int PROFILE = 7;
    public static final int SETTINGS = 8;

    private String MY_FRAGMENT_TAG;

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        recyclerView = findViewById(R.id.rv_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        mData.add(new item(R.drawable.ic_dashboard_black_24dp, DASHBOARD_CODE, "Dashboard Fragment"));
        mData.add(new item(R.drawable.ic_content_medhist_24dp, MEDICAL_HISTORY, getString(R.string.myrecords)));
        mData.add(new item(R.drawable.ic_folder_myrec_24dp, MY_RECORDS, getString(R.string.medicalhistory)));
        mData.add(new item(R.drawable.ic_add_bloodbank_24dp, NEAREST_BLOOD_BANK, getString(R.string.bloodbank)));
        mData.add(new item(R.drawable.ic_govtschemes_24dp, GOVT_MED_SCHEMES, getString(R.string.govtschemes)));
        mData.add(new item(R.drawable.ic_add_alert_black_24dp, REMINDERS, getString(R.string.reminders)));
        mData.add(new item(R.drawable.ic_live_help_black_24dp, FAQ, getString(R.string.faqs)));
        mData.add(new item(R.drawable.ic_person_black_24dp, PROFILE, getString(R.string.profile)));
        mData.add(new item(R.drawable.ic_settings_black_24dp, SETTINGS, getString(R.string.settings)));

        adapter = new MenuAdapter(this, mData);
        recyclerView.setAdapter(adapter);

        // Dashboard instantiate
        DashboardFrag1 dashboardFrag1 = new DashboardFrag1();
        MyRecords2 myRecords2 = new MyRecords2();
        MedicalHistory3 medicalHistory3 = new MedicalHistory3();
        BloodBankNearby4 bloodBankNearby4 = new BloodBankNearby4();
        GovtHelpSchemes5 govtHelpSchemes5 = new GovtHelpSchemes5();
        RemindersFrag6 remindersFrag6 = new RemindersFrag6();
        FAQsFrag7 faQsFrag7 = new FAQsFrag7();
        ProfileFrag8 profileFrag8 = new ProfileFrag8();
        SettingsFrag9 settingsFrag9 = new SettingsFrag9();

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, dashboardFrag1);
            fragmentTransaction.commit();
        }
        else {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, dashboardFrag1);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Toast.makeText(this, ""+user.getEmail(), Toast.LENGTH_SHORT).show();
        } else {
            // No user is signed in
            Intent goback = new Intent(DashboardActivity.this, LoginActivity.class);
            startActivity(goback);
            finish();
        }

    }
    @Override
    public void onBackPressed() {

        {
            if (exit)
            {
                super.onBackPressed();
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);
                finishAffinity();
            } else

            exit = true;
            Toast.makeText(this, "Tap Back again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }
}


