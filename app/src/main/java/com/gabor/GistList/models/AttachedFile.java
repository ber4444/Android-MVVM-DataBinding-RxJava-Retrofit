package com.gabor.GistList.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kayvan on 7/21/16.
 */
public class AttachedFile {
    @SerializedName("filename") @Expose public String filename;
    @SerializedName("type") @Expose public String type;
    @SerializedName("language") @Expose public String language;
    @SerializedName("raw_url") @Expose public String rawUrl;
    @SerializedName("size") @Expose public Integer size;
}
