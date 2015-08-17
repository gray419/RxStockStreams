package com.graywolf.rxstockstreams.service;

import com.graywolf.rxstockstreams.data.StreamResponse;
import com.graywolf.rxstockstreams.data.SymbolDetailResponse;
import com.graywolf.rxstockstreams.data.WatchlistResponse;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public class StreamService {
    private static final String STOCKTWITS_URL = "https://api.stocktwits.com/api/2";
    private static final String TOKEN = "676d6a0b21abd9d953a7110c20a021db742fac30";
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

        @GET("/watchlists/show/static.json")
        Observable<WatchlistResponse> getWatchlist();

        @GET("/prices.json")
        Observable<SymbolDetailResponse> getSymbolDetails(@Query("symbols") String symbols);
    }
}