package com.thiman.android.reservationmanager.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thiman.android.reservationmanager.RoomDetails.RoomDetailsModel;
import com.thiman.android.reservationmanager.RoomDetails.RecyclerViewDetailsAdapter;

import com.thiman.android.reservationmanager.R;
import com.thiman.android.reservationmanager.rest.ApiClient;
import com.thiman.android.reservationmanager.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Asus on 1/25/2018.
 */

public class FragmentSingle extends Fragment {
    RecyclerView recyclerView;
    ApiInterface apiInterface;
    RecyclerViewDetailsAdapter recyclerviewAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_single, container, false);
        recyclerView = rootView.findViewById(R.id.rv_recycler_viewrds);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        fetchData();

        return rootView;
    }

    private void fetchData(){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<RoomDetailsModel> call = apiInterface.getRoomDetails();
        call.enqueue(new Callback<RoomDetailsModel>() {
            @Override
            public void onResponse(Call<RoomDetailsModel> call, Response<RoomDetailsModel> response) {
                List<RoomDetailsModel.Array> model = new ArrayList<RoomDetailsModel.Array>();
                for(int i = 0; i<response.body().getData().size(); i++){
                    if(response.body().getData().get(i).getRoomType() == 100){
                        model.add(response.body().getData().get(i));
                    }
                }
                recyclerviewAdapter = new RecyclerViewDetailsAdapter(model);
                recyclerView.setAdapter(recyclerviewAdapter);
            }

            @Override
            public void onFailure(Call<RoomDetailsModel> call, Throwable t) {
//                Log.i("Tag ", t.getMessage());
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}