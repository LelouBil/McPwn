package net.leloubil.mcpwn.carvinglabs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
public class CLConsumption implements Serializable {
    @SerializedName("burnProduct")
    public CLProduct burnProduct;
    @SerializedName("createdAt")
    public Date createdAt;
    @SerializedName("order")
    public CLOrder order;
}
