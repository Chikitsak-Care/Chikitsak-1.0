package com.vivek.chikitsak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class KotlinConverter extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    RecyclerView recyclerView;
    public static final int DASHBOARD_CODE = 0;
    public static final int MEDICAL_HISTORY = 0;
    public static final int MY_RECORDS = 0;
    public static final int NEAREST_BLOOD_BANK = 0;
    public static final int GOVT_MED_SCHEMES = 0;
    public static final int REMINDERS = 0;
    public static final int FAQ = 0;
    public static final int PROFILE = 0;

    List<item> mData;
    MenuAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kotlin_converter);

        recyclerView = findViewById(R.id.rv_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        mData.add(new item(R.drawable.ic_dashboard_black_24dp, DASHBOARD_CODE, "Dashboard Fragment"));
        adapter = new MenuAdapter(this, mData);
        recyclerView.setAdapter(adapter);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();


        //Dashboard Instantiate
        DashboardFrag1 dashboardFrag1 = new DashboardFrag1();
        fragmentTransaction.replace(R.id.container, dashboardFrag1);
        fragmentTransaction.commit();

    }
}
