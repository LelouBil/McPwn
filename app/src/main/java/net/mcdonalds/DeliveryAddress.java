package net.mcdonalds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class DeliveryAddress {
    @SerializedName("additionalInformation")
    @Expose
    private String additionalInformation;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("mobilePhoneNumber")
    @Expose
    private String mobilePhoneNumber;
}
