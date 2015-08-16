package com.graywolf.rxstockstreams.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 8/15/15.
 */
public class User {
    @SerializedName("id")
    public Integer ID;
    @SerializedName("username")
    public String UserName;
    @SerializedName("name")
    public String Name;
    @SerializedName("avatar_url")
    public String AvatarUrl;
    @SerializedName("avatar_url_ssl")
    public String AvatarUrlSsl;
    @SerializedName("official")
    public Boolean Official;
    @SerializedName("identity")
    public String Identity;
    @SerializedName("classification")
    public List<String> Classification = new ArrayList<>();
    @SerializedName("join_date")
    public String JoinDate;
    @SerializedName("followers")
    public Integer Followers;
    @SerializedName("following")
    public Integer Following;
    @SerializedName("ideas")
    public Integer Ideas;
    @SerializedName("following_stocks")
    public Integer FollowingStocks;
    @SerializedName("location")
    public String Location;
    @SerializedName("bio")
    public String Bio;
    @SerializedName("website_url")
    public String WebsiteUrl;
    @SerializedName("email")
    public String Email;
    @SerializedName("like_count")
    public Integer LikeCount;
    @SerializedName("subscribers_count")
    public Integer SubscribersCount;
    @SerializedName("subscribed_to_count")
    public Integer SubscribedToCount;
    @SerializedName("active_platforms")
    public String ActivePlatforms;
}
