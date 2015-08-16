package com.graywolf.rxstockstreams.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by robert on 8/15/15.
 */
public class Message{
    @SerializedName("id")
    public String Id;
    @SerializedName("body")
    public String Body;
    @SerializedName("created_at")
    public String CreatedAt;
    @SerializedName("user")
    public com.graywolf.rxstockstreams.data.User User;
}
