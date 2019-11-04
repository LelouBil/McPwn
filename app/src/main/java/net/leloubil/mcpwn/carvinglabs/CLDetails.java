package net.leloubil.mcpwn.carvinglabs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class CLDetails {
    @SerializedName("endAt")
    public String dateEnd;
    @SerializedName("startAt")
    public String dateStart;
    @SerializedName("dayOfWeek")
    public Long dayOfWeek;
}
