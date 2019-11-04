package net.leloubil.mcpwn.carvinglabs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
public class CLOrder implements Serializable {
    @SerializedName("amount")
    public Long amount;
    @SerializedName("createdAt")
    public Date dateCreatedAt;
    @SerializedName("id")
    public Long id;
    @SerializedName("orderId")
    public String internalOrderId;
    @SerializedName("restaurantId")
    public String restaurantId;
}
