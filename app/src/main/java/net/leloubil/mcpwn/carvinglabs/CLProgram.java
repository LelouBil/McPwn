package net.leloubil.mcpwn.carvinglabs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;

@Getter
public class CLProgram {
    @SerializedName("activated")
    public Boolean activated;
    @SerializedName("boosters")
    public ArrayList<CLBooster> boosters = new ArrayList<>();
    @SerializedName("burnProducts")
    public ArrayList<CLProduct> burnProduct = new ArrayList<>();
    @SerializedName("channels")
    public ArrayList<String> channels;
    @SerializedName("endAt")
    public Date dateEndAt;
    @SerializedName("startAt")
    public Date dateStartAt;
    @SerializedName("earnProducts")
    public ArrayList<CLProduct> earnProduct = new ArrayList<>();
    @SerializedName("hasTarget")
    public Boolean hasTarget;
    @SerializedName("id")
    public Long id;
    @SerializedName("maxPoints")
    public Long maxPoints;
    @SerializedName("name")
    public String name;
    @SerializedName("nbStamps")
    public Long nbStamps;
    @SerializedName("restaurants")
    public ArrayList<String> restaurants = new ArrayList<>();
    @SerializedName("steps")
    public ArrayList<CLStep> steps = new ArrayList<>();
    @SerializedName("type")
    public TypeProgram type;

    public boolean doesContainsRestaurantRef(String str) {
        if (this.restaurants != null && this.restaurants.size() > 0) {
            for (String restaurant : this.restaurants) {
                if (restaurant.equalsIgnoreCase(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public long getLowerRewardPointAmount() {
        if (this.steps != null) {
            return steps.stream().mapToLong(CLStep::getNbPoints).min().orElse(-1);
        }
        return -1;
    }

    public enum TypeProgram {
        POINTS,
        STAMPS
    }
}
