package com.graywolf.rxstockstreams.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by robert on 8/16/15.
 */
public class WatchlistResponse extends BaseResponse {
    @SerializedName("watchlist")
    public Watchlist Watchlist;
}
