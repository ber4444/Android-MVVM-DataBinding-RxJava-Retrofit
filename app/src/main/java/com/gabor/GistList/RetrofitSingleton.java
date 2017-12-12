package com.gabor.GistList;

import com.gabor.GistList.models.Gist;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by gabor on 12/3/17.
 */

final class RetrofitSingleton {
    private static volatile RetrofitSingleton instance = null;
    private GistApi client;

    private RetrofitSingleton() {
        Retrofit retrofit =
                new Retrofit.Builder().baseUrl("https://api.github.com")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create()).build();
        client = retrofit.create(GistApi.class);
    }

    static RetrofitSingleton getInstance() {
        if (instance == null) {
            synchronized(RetrofitSingleton.class) {
                if (instance == null) {
                    instance = new RetrofitSingleton();
                }
            }
        }
        return instance;
    }

    GistApi provideClient() {
        return client;
    }

    interface GistApi {
        @SuppressWarnings("unused")
        @GET("/gists/public?per_page=100")
        Call<ArrayList<Gist>> getGists();
        @GET("/gists/public?per_page=100")
        Observable<ArrayList<Gist>> getGistsObservable();
    }
}
