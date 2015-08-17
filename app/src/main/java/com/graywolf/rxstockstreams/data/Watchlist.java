package com.graywolf.rxstockstreams.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by robertgray on 2/6/15.
 */
public class Watchlist {
    @SerializedName("id")
    public Integer ID;
    @SerializedName("name")
    public String Name;
    @SerializedName("updated_at")
    public String UpdatedAt;
    @SerializedName("created_at")
    public String CreatedAt;
    @SerializedName("symbols")
    public List<Symbol> Symbols;
}
