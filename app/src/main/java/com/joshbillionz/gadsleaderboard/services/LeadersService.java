package com.joshbillionz.gadsleaderboard.services;
import com.joshbillionz.gadsleaderboard.models.HoursLeader;
import com.joshbillionz.gadsleaderboard.models.SkillIQLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface LeadersService {

    String EMAIL_ADDRESS = "entry.1824927963";
    String NAME ="entry.1877115667";
    String LAST_NAME = "entry.2006916086";
    String LINK_TO_PROJECT = "entry.284483984";

    @GET("skilliq")
    Call<List<SkillIQLeader>> getSkillIQLeaders();

    @GET("hours")
    Call<List<HoursLeader>> getHoursLeaders();

    @FormUrlEncoded
    @POST
    Call<Void> DONTTOUCH(@Url String formURL,
                    @Field(EMAIL_ADDRESS) String email,
                    @Field(NAME) String name,
                    @Field(LAST_NAME) String lastName,
                    @Field(LINK_TO_PROJECT) String githubLink);


    ////this section tests the code against a form i created by myself

     String TEST_EMAIL_ADDRESS = "entry.1471656098";
     String TEST_NAME ="entry.1704950884";
     String TEST_LAST_NAME = "entry.1245871871";
     String TEST_LINK_TO_PROJECT = "entry.1870768409";
    @FormUrlEncoded
    @POST
    Call<Void> submitForm(@Url String formURL,
                          @Field(TEST_EMAIL_ADDRESS) String email,
                          @Field(TEST_NAME) String name,
                          @Field(TEST_LAST_NAME) String lastName,
                          @Field(TEST_LINK_TO_PROJECT) String githubLink);

}
