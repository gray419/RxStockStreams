package com.graywolf.rxstockstreams.data;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class SymbolDetailResponse extends BaseResponse {
    @SerializedName("prices")
    public HashMap<String, SymbolDetail> Prices;
}
