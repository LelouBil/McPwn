package net.leloubil.mcpwn.carvinglabs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class CLProduct {
    @SerializedName("children")
    public ArrayList<String> children = new ArrayList<>();
    @SerializedName("delivery")
    public Boolean delivery;
    @SerializedName("label")
    public String label;
    @SerializedName("pmix")
    public String pmix;
    @SerializedName("type")
    public TypeCLProduct type = TypeCLProduct.UNKNOWN;
    @SerializedName("img")
    public String urlimg;

    public enum TypeCLProduct {
        UNKNOWN,
        MENU,
        PRODUCT,
        CATEGORY
    }
}
