package com.graywolf.rxstockstreams.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StreamResponse {
    @SerializedName("cursor")
    public Cursor Cursor;
    @SerializedName("messages")
    public List<Message> Messages;
}
