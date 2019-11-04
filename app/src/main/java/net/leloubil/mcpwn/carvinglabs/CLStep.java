package net.leloubil.mcpwn.carvinglabs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class CLStep {
    @SerializedName("burnProducts")
    public ArrayList<CLProduct> burnProduct = new ArrayList<>();
    @SerializedName("nbPoints")
    public Long nbPoints;
}
