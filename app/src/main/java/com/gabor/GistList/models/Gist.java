package com.gabor.GistList.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

public class Gist {

    @SerializedName("url") @Expose public String url;
    @SerializedName("forks_url") @Expose public String forksUrl;
    @SerializedName("commits_url") @Expose public String commitsUrl;
    @SerializedName("id") @Expose public String id;
    @SerializedName("git_pull_url") @Expose public String gitPullUrl;
    @SerializedName("git_push_url") @Expose public String gitPushUrl;
    @SerializedName("html_url") @Expose public String htmlUrl;
    @SerializedName("public") @Expose public Boolean _public;
    @SerializedName("created_at") @Expose public String createdAt;
    @SerializedName("updated_at") @Expose public String updatedAt;
    @SerializedName("description") @Expose public String description;
    @SerializedName("comments") @Expose public Integer comments;
    @SerializedName("comments_url") @Expose public String commentsUrl;
    @SerializedName("owner") @Expose public Owner owner;
    @SerializedName("truncated") @Expose public Boolean truncated;
    @SerializedName("files") @Expose public LinkedTreeMap<String, AttachedFile> files;
}
