package com.joshbillionz.gadsleaderboard.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.joshbillionz.gadsleaderboard.R;
import com.joshbillionz.gadsleaderboard.adapters.SkillIQAdapter;
import com.joshbillionz.gadsleaderboard.models.SkillIQLeader;
import com.joshbillionz.gadsleaderboard.services.ServiceBuilder;
import com.joshbillionz.gadsleaderboard.services.LeadersService;

import java.util.List;

public class SkillIQFragment extends Fragment {

    public SkillIQFragment() {
        // Required empty public constructor
    }


    public static SkillIQFragment newInstance() {
        SkillIQFragment fragment = new SkillIQFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hours, container, false);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LeadersService service = ServiceBuilder.buildService(LeadersService.class);
        Call<List<SkillIQLeader>> call = service.getSkillIQLeaders();
        call.enqueue(new Callback<List<SkillIQLeader>>() {
            @Override
            public void onResponse(Call<List<SkillIQLeader>> call, Response<List<SkillIQLeader>> response) {
                recyclerView.setAdapter(new SkillIQAdapter(response.body()));
                Toast.makeText(getContext(), "SUCCESSS", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<SkillIQLeader>> call, Throwable t) {
                Log.d("FAILLLLLLLLLLLLLLLED", "onFailure: ");
                Toast.makeText(getContext(), "fAILURE", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}