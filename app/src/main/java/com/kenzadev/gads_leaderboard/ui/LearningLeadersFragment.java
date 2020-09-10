package com.kenzadev.gads_leaderboard.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kenzadev.gads_leaderboard.API.RetrofitApiCalls;
import com.kenzadev.gads_leaderboard.API.RetrofitClient;
import com.kenzadev.gads_leaderboard.Adapters.LearningLeadersAdapter;
import com.kenzadev.gads_leaderboard.R;
import com.kenzadev.gads_leaderboard.models.LearningLeaderModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeadersFragment extends Fragment {
    LearningLeadersAdapter adapter;
    RetrofitApiCalls service;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        service = RetrofitClient.getRetrofitInstance().create(RetrofitApiCalls.class);

        View rootView = inflater
                .inflate(R.layout.fragment_learning_leaders, container, false);

        recyclerView = rootView.findViewById(R.id.learning_leader_recycler);

        initAdapter();
        return rootView;
    }

  /*  @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }*/

    public void initAdapter() {
        Call<List<LearningLeaderModel>> call = service.getLearningLeaders();
        call.enqueue(new Callback<List<LearningLeaderModel>>() {
            @Override
            public void onResponse(Call<List<LearningLeaderModel>> call, Response<List<LearningLeaderModel>> response) {
                if (response.isSuccessful()) {
                    List<LearningLeaderModel> learners = response.body();

                    adapter = new LearningLeadersAdapter(getActivity(),learners);

                    LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<LearningLeaderModel>> call, Throwable t) {

            }
        });
    }

}
