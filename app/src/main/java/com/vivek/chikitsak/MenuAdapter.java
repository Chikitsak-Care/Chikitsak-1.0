package com.vivek.chikitsak;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    Context mContext;
    List<item> mData;
    int selected_position = 0 ;

    public MenuAdapter(Context mContext, List<item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout,viewGroup,false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder, final int i) {


        menuViewHolder.imgIcon.setImageResource(mData.get(i).getIcon());
        menuViewHolder.imgIcon.setBackgroundColor(selected_position == i ? Color.parseColor("#fff1f1f1") :
                Color.parseColor("#ffffff"));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{

        ImageView imgIcon;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.img_icon) ;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyItemChanged(selected_position);
                    selected_position = getAdapterPosition();
                    notifyItemChanged(selected_position);
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new Fragment();

                    switch (getAdapterPosition()) {
                        case 0:
                            myFragment = new DashboardFrag1();
                            break;

                        case 1:
                            myFragment = new MyRecords2();
                            break;
                        case 2:
                            myFragment = new MedicalHistory3();
                            break;
                        case 3:
                            myFragment = new BloodBankNearby4();
                            break;
                        case 4:
                            myFragment = new GovtHelpSchemes5();
                            break;
                        case 5:
                            myFragment = new RemindersFrag6();
                            break;
                        case 6:
                            myFragment = new FAQsFrag7();
                            break;
                        case 7:
                            myFragment = new ProfileFrag8();
                            break;
                        case 8:
                            myFragment = new SettingsFrag9();
                            break;
                        default:
                            myFragment = new DashboardFrag1();
                    }

                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).commit();

                }
            });
        }
    }

}
