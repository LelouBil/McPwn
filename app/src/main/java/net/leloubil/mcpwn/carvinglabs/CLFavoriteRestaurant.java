package net.leloubil.mcpwn.carvinglabs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class CLFavoriteRestaurant {
    @SerializedName("currentLoyaltyPrograms")
    public ArrayList<CLProgram> mCurrentProgram = new ArrayList<>();
    @SerializedName("futureLoyaltyPrograms")
    public ArrayList<CLProgram> mFutureProgram = new ArrayList<>();
}
