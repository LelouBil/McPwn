package net.mcdonalds;

import com.google.gson.annotations.SerializedName;

class Device {
    @SerializedName("deviceId")
    private String deviceId;
    @SerializedName("deviceType")
    private String deviceType = "ANDROID";
}
