package com.graywolf.rxstockstreams.data;

import com.google.gson.annotations.SerializedName;

public class SymbolDetail {
    @SerializedName("price")
    public double Price;
    @SerializedName("change")
    public double Change;
    @SerializedName("percent")
    public double Percent;
    @SerializedName("high")
    public double High;
    @SerializedName("low")
    public double Low;
    @SerializedName("open")
    public double Open;
    @SerializedName("volume")
    public double Volume;
}