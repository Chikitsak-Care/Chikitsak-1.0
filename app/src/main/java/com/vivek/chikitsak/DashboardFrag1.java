package com.vivek.chikitsak;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DashboardFrag1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashboardFrag1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFrag1 extends Fragment {

    private CardView cardtop, cardRight, cardLeft, cardLeft2;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DashboardFrag1() {
        // Required empty public constructor
    }



    /*


     */

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFrag1.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFrag1 newInstance(String param1, String param2) {
        DashboardFrag1 fragment = new DashboardFrag1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //card init
        cardtop = v.findViewById(R.id.cardtop);
        cardRight = v.findViewById(R.id.cardRight);
        cardLeft = v.findViewById(R.id.cardLeft);
        cardLeft2 = v.findViewById(R.id.cardLeft2);

        //card animations
        Animation animeBottomToTop = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_bottom_to_top);
        Animation animeTopToBottom = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_top_to_bottom);
        Animation animeRightToLeft = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_right_to_left);
        Animation animeLeftToRight = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_left_to_right);

        cardLeft2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call 1st phone number api
                Uri callIntentUri = Uri.parse("tel: 108");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, callIntentUri);
                startActivity(callIntent);
            }
        });


        //setup animations
        cardLeft2.setAnimation(animeBottomToTop);
        cardtop.setAnimation(animeTopToBottom);
        cardRight.setAnimation(animeRightToLeft);
        cardLeft.setAnimation(animeLeftToRight);

        // Inflate the layout for this fragment
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Deleted the code here

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    int count = 0;
    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}

