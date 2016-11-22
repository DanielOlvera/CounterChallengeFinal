package com.example.daniel.timedchallenge_final;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.daniel.timedchallenge_final.entities.Result;
import com.example.daniel.timedchallenge_final.entities.TestAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mainActivityTAG_";
    //private String myJsn = "{\"results\":[{\"gender\":\"male\",\"name\":{\"title\":\"mr\",\"first\":\"jonas\",\"last\":\"clement\"},\"location\":{\"street\":\"5983 rue des cuirassiers\",\"city\":\"rennes\",\"state\":\"savoie\",\"postcode\":79331},\"email\":\"jonas.clement@example.com\",\"login\":{\"username\":\"ticklishelephant348\",\"password\":\"zzzz\",\"salt\":\"Fa4tHsZI\",\"md5\":\"54f60e894fad808e55a0d4a9a2c0f315\",\"sha1\":\"7f9ef3649bde97f4a761d37bab1d735a9233ff06\",\"sha256\":\"99b0f9f50d6dd2467f7b2f0e1bfe82ad107912fac58e04038d4aa700e62dbca0\"},\"dob\":\"1958-01-25 21:24:35\",\"registered\":\"2012-11-02 20:41:13\",\"phone\":\"03-62-03-58-09\",\"cell\":\"06-20-65-37-51\",\"id\":{\"name\":\"INSEE\",\"value\":\"158096906240 26\"},\"picture\":{\"large\":\"https://randomuser.me/api/portraits/men/25.jpg\",\"medium\":\"https://randomuser.me/api/portraits/med/men/25.jpg\",\"thumbnail\":\"https://randomuser.me/api/portraits/thumb/men/25.jpg\"},\"nat\":\"FR\"}],\"info\":{\"seed\":\"0bd0a64b634f2525\",\"results\":1,\"page\":1,\"version\":\"1.1\"}}";

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(networkCheck()) {
            String myJsn = "";
            try {
                myJsn = run("https://randomuser.me/api");
            }catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "onCreate: " + myJsn);
            }

            Gson gson = new GsonBuilder().create();
            //Here we are going to put the POJO class in this case ResultAPI
            TestAPI resultAPI = gson.fromJson(myJsn, TestAPI.class);
            //ArrayList<TestAPI> resultAPI = gson.fromJson(myJsn, ArrayList<TestAPI>);

            Log.d(TAG, "onCreate: " + resultAPI); //This will show the hashcode

            for(Result result_ : resultAPI.getResults()){
                Log.d(TAG, "onCreate: " + result_);
            }
        } else {
            Toast.makeText(this, "Device not connected to the Internet.", Toast.LENGTH_SHORT).show();
        }

        Gson gson = new GsonBuilder().create();
        //Here we are going to put the POJO class in this case ResultAPI
        //TestAPI resultAPI = gson.fromJson(myJsn, TestAPI.class);
        //ArrayList<ResultAPI> resultAPI = gson.fromJson(myJson, ArrayList<ResultAPI>);

        //Log.d(TAG, "onCreate: " + resultAPI); //This will show the hashcode
    }

    private boolean networkCheck() {
        boolean isConnected = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                isConnected = true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                isConnected = true;
            }
        }

        return isConnected;
    }

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
