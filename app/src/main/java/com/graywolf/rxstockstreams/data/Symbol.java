package com.graywolf.rxstockstreams.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by robert on 8/16/15.
 */
public class Symbol {
    @SerializedName("id")
    public int ID;
    @SerializedName("symbol")
    public String Symbol;
    @SerializedName("title")
    public String Title;
    @SerializedName("exchange")
    public String Exchange;
    @SerializedName("sector")
    public String Sector;
    @SerializedName("industry")
    public String Industry;
    @SerializedName("trending")
    public boolean Trending;
    @SerializedName("trending_score")
    public double TrendingScore;
    @SerializedName("watchlist_count")
    public long WatchlistCount;
    @SerializedName("sentiment_change")
    public double SentimentChange;
    @SerializedName("volume_change")
    public double VolumeChange;
}
