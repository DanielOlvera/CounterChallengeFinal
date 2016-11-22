package com.example.daniel.timedchallenge_final;

import com.example.daniel.timedchallenge_final.entities.TestAPI;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Daniel on 11/22/16.
 */

public interface RetroHelper {

    String BASE_URL = "https://randomuser.me/api/";
    String GENDER_ID = "male";
    String NAME_ID = "jonas";

    @GET("/v2/search_results")
    public Observable<TestAPI> getListings(@Query("gender") String client_id,
                                           @Query("name") String user_lng);


    class RetrofitThing {
        Observable<TestAPI> getResults() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            return retrofit.create(RetroHelper.class).getListings(GENDER_ID,
                    NAME_ID);
        }
    }
}
