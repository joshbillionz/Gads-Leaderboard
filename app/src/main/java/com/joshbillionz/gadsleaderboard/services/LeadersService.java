package com.joshbillionz.gadsleaderboard.services;

import com.joshbillionz.gadsleaderboard.models.HoursLeader;
import com.joshbillionz.gadsleaderboard.models.SkillIQLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LeadersService {

    @GET("skilliq")
    Call<List<SkillIQLeader>> getSkillIQLeaders();

    @GET("hours")
    Call<List<HoursLeader>> getHoursLeaders();

}
