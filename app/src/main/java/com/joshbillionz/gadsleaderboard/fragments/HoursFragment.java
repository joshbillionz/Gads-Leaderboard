package com.joshbillionz.gadsleaderboard.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.joshbillionz.gadsleaderboard.R;
import com.joshbillionz.gadsleaderboard.adapters.HoursAdapter;
import com.joshbillionz.gadsleaderboard.models.HoursLeader;
import com.joshbillionz.gadsleaderboard.services.LeadersService;
import com.joshbillionz.gadsleaderboard.services.ServiceBuilder;

import java.util.List;

public class HoursFragment extends Fragment {

    Call<List<HoursLeader>> call;
    RecyclerView recyclerView;
    ProgressBar mProgressBar;

    public HoursFragment() {
        // Required empty public constructor
    }

    public static HoursFragment newInstance() {
        HoursFragment fragment = new HoursFragment();
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

        mProgressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LeadersService service = ServiceBuilder.buildService(LeadersService.class);
        call = service.getHoursLeaders();
        loadLeaders();

        return view;
    }

    private void loadLeaders() {
        call.enqueue(new Callback<List<HoursLeader>>() {
            @Override
            public void onResponse(Call<List<HoursLeader>> call, Response<List<HoursLeader>> response) {
                recyclerView.setAdapter(new HoursAdapter(response.body()));
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<HoursLeader>> call, Throwable t) {
                Toast.makeText(getContext(), "failure!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}