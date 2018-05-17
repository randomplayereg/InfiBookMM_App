package com.randomplayer.infibookmm_app.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.randomplayer.infibookmm_app.R;
import com.randomplayer.infibookmm_app.activities.WatcherActivity;
import com.randomplayer.infibookmm_app.adapters.CustomExpandableListViewAdapter;
import com.randomplayer.infibookmm_app.models.Transaction;
import com.randomplayer.infibookmm_app.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class TrackingListFragment extends Fragment {

    ExpandableListView trackingListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tracking_list, container, false);
        trackingListView = v.findViewById(R.id.tracking_list_view);

//        bindData();



        // Inflate the layout for this fragment
        return v;
    }

//    private void bindData() {
//        List<String> statusBundle = new ArrayList<String>();
//        final ArrayList<ArrayList<Transaction>> transactionBundle = new ArrayList<ArrayList<Transaction>>();
//
//        statusBundle.add("Ready!");
//        statusBundle.add("Awaiting...");
//        statusBundle.add("Canceled?");
//
//        User andy = new User("andy@gmail.com", 11.0, 105.0);
//        User johnny = new User("johnny@gmail.com", 11.0, 105.0);
//        User emil = new User("emil@gmail.com", 11.0, 105.0);
//        User emin = new User("emin@gmail.com", 10.05, 105.0);
//        User dzungvu = new User("dungvu@gmail.com", 10.1, 105.5);
//        Date deadline = new Date(2018, 5, 10);
//
//        ArrayList<Transaction> readyBundle = new ArrayList<Transaction>();
//        readyBundle.add(new Transaction(andy, johnny, deadline, "I am Legend!"));
//        readyBundle.add(new Transaction(andy, emil, deadline, "Mua tren phim dan"));
//
//        ArrayList<Transaction> awaitBundle = new ArrayList<Transaction>();
//        awaitBundle.add(new Transaction(dzungvu, emin, deadline, "Harry Potter"));
//        awaitBundle.add(new Transaction(dzungvu, johnny, deadline, "The Lord of the Rings"));
//
//        ArrayList<Transaction> cancledBundle = new ArrayList<Transaction>();
//
//        transactionBundle.add(readyBundle);
//        transactionBundle.add(awaitBundle);
//        transactionBundle.add(cancledBundle);
//
//        CustomExpandableListViewAdapter adapter = new CustomExpandableListViewAdapter(getActivity(), statusBundle, transactionBundle);
//        trackingListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                return true;
//            }
//        });
//        trackingListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Intent intent = new Intent(getActivity(), WatcherActivity.class);
//                intent.putExtra("lender_lat", transactionBundle.get(groupPosition).get(childPosition).getLender().getLat());
//                intent.putExtra("lender_lat", transactionBundle.get(groupPosition).get(childPosition).getLender().getLng());
//                intent.putExtra("borrower_lat", transactionBundle.get(groupPosition).get(childPosition).getBorrower().getLat());
//                intent.putExtra("borrower_lng", transactionBundle.get(groupPosition).get(childPosition).getBorrower().getLng());
//                startActivity(intent);
//                return true;
//            }
//        });
//        trackingListView.setAdapter(adapter);
//    }


}
