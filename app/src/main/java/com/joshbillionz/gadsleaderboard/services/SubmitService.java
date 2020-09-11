package com.joshbillionz.gadsleaderboard.services;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Url;

public interface SubmitService {
    static final String TEST_EMAIL_ADDRESS = "entry.1471656098";
    static final String TEST_NAME ="entry.1704950884";
    static final String TEST_LAST_NAME = "entry.1245871871";
    static final String TEST_LINK_TO_PROJECT = "entry.1870768409";

    @FormUrlEncoded
    Call<Void> submitForm(@Url String formURL,
                          @Field(TEST_EMAIL_ADDRESS) String email,
                          @Field(TEST_NAME) String name,
                          @Field(TEST_LAST_NAME) String lastName,
                          @Field(TEST_LINK_TO_PROJECT) String githubLink);

}
