package com.graywolf.rxstockstreams.service;

import com.graywolf.rxstockstreams.data.StreamResponse;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import rx.Observable;

public class StreamService {
    private static final String STOCKTWITS_URL = "https://api.stocktwits.com/api/2";
    public static String TOKEN = "";
    private StreamApi mApi;

    public StreamService(){
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
                request.addQueryParam("access_token", TOKEN);
            }
        };

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(STOCKTWITS_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mApi = adapter.create(StreamApi.class);
    }

    public StreamApi getApi() {
        return mApi;
    }

    public interface StreamApi{
        @GET("/streams/trending.json")
        Observable<StreamResponse> getMessages();
    }
}
