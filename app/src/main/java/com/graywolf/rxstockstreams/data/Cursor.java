package com.graywolf.rxstockstreams.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by robert on 8/15/15.
 */
public class Cursor {
    @SerializedName("max")
    public String Max;
    @SerializedName("more")
    public Boolean More;
    @SerializedName("since")
    public String Since;
}
