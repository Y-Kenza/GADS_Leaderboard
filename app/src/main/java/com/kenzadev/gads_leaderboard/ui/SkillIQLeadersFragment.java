package com.kenzadev.gads_leaderboard.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kenzadev.gads_leaderboard.API.RetrofitApiCalls;
import com.kenzadev.gads_leaderboard.API.RetrofitClient;
import com.kenzadev.gads_leaderboard.Adapters.LearningLeadersAdapter;
import com.kenzadev.gads_leaderboard.Adapters.SkillIQAdapter;
import com.kenzadev.gads_leaderboard.R;
import com.kenzadev.gads_leaderboard.models.LearningLeaderModel;
import com.kenzadev.gads_leaderboard.models.SkillIQModel;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIQLeadersFragment extends Fragment {
    SkillIQAdapter adapter;
    RetrofitApiCalls service;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        service = RetrofitClient.getRetrofitInstance().create(RetrofitApiCalls.class);

        View rootView = inflater
                .inflate(R.layout.fragment_skill_iq_leaders, container, false);

        recyclerView = rootView.findViewById(R.id.skill_iq_recycler);

        initAdapter();
        return rootView;
    }

    public void initAdapter() {
        Call<List<SkillIQModel>> call = service.getSkillLeaders();
        call.enqueue(new Callback<List<SkillIQModel>>() {
            @Override
            public void onResponse(Call<List<SkillIQModel>> call, Response<List<SkillIQModel>> response) {
                if (response.isSuccessful()) {
                    List<SkillIQModel> learners = response.body();

                    Collections.sort(learners, Collections.reverseOrder());
                    adapter = new SkillIQAdapter(getActivity(),learners);

                    LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<SkillIQModel>> call, Throwable t) {

            }
        });
    }
}
