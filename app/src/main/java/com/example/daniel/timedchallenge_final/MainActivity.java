package com.example.daniel.timedchallenge_final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.daniel.timedchallenge_final.entities.TestAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mainActivityTAG_";
    private String myJsn = "{\"results\":[{\"gender\":\"male\",\"name\":{\"title\":\"mr\",\"first\":\"jonas\",\"last\":\"clement\"},\"location\":{\"street\":\"5983 rue des cuirassiers\",\"city\":\"rennes\",\"state\":\"savoie\",\"postcode\":79331},\"email\":\"jonas.clement@example.com\",\"login\":{\"username\":\"ticklishelephant348\",\"password\":\"zzzz\",\"salt\":\"Fa4tHsZI\",\"md5\":\"54f60e894fad808e55a0d4a9a2c0f315\",\"sha1\":\"7f9ef3649bde97f4a761d37bab1d735a9233ff06\",\"sha256\":\"99b0f9f50d6dd2467f7b2f0e1bfe82ad107912fac58e04038d4aa700e62dbca0\"},\"dob\":\"1958-01-25 21:24:35\",\"registered\":\"2012-11-02 20:41:13\",\"phone\":\"03-62-03-58-09\",\"cell\":\"06-20-65-37-51\",\"id\":{\"name\":\"INSEE\",\"value\":\"158096906240 26\"},\"picture\":{\"large\":\"https://randomuser.me/api/portraits/men/25.jpg\",\"medium\":\"https://randomuser.me/api/portraits/med/men/25.jpg\",\"thumbnail\":\"https://randomuser.me/api/portraits/thumb/men/25.jpg\"},\"nat\":\"FR\"}],\"info\":{\"seed\":\"0bd0a64b634f2525\",\"results\":1,\"page\":1,\"version\":\"1.1\"}}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder().create();
        //Here we are going to put the POJO class in this case ResultAPI
        TestAPI resultAPI = gson.fromJson(myJsn, TestAPI.class);
        //ArrayList<ResultAPI> resultAPI = gson.fromJson(myJson, ArrayList<ResultAPI>);

        Log.d(TAG, "onCreate: " + resultAPI); //This will show the hashcode
    }

}
