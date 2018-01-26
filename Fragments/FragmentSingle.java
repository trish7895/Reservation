package com.thiman.android.reservationmanager.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thiman.android.reservationmanager.Adapters.RoomDetailsAdapter;
import com.thiman.android.reservationmanager.R;

/**
 * Created by Asus on 1/25/2018.
 */

public class FragmentSingle extends Fragment {
    public FragmentSingle() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_single, container, false);

        RecyclerView rv = rootView.findViewById(R.id.rv_recycler_viewrds);
        rv.setHasFixedSize(true);
        RoomDetailsAdapter adapter = new RoomDetailsAdapter(new String[]{" 1", " 2", " 3", " 4", " 5" , " 6" , " 7","8","9","10"},
                new String[]{"5","1","3","4","3","1","5","1","4","2"},new String[]{"10$","20$","15$","35$","22$","10$","20$","15$","35$","22$"});
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;    }
}