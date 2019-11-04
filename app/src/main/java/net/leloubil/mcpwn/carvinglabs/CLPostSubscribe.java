package net.leloubil.mcpwn.carvinglabs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CLPostSubscribe {
    @SerializedName("loyaltyProgramIds")
    public ArrayList<Long> loyaltyProgramIds;
    @SerializedName("optinEmail")
    public Boolean optinEmail;
    @SerializedName("optinPush")
    public Boolean optinPush;
    @SerializedName("optinSms")
    public Boolean optinSms;

    public CLPostSubscribe() {
        this.loyaltyProgramIds = new ArrayList<>();
        this.loyaltyProgramIds = new ArrayList<>();
        this.optinSms = false;
        this.optinEmail = false;
        this.optinPush = false;
    }

    public CLPostSubscribe(List<CLProgram> list, Boolean bool, Boolean bool2, Boolean bool3) {
        this.loyaltyProgramIds = new ArrayList<>();
        this.loyaltyProgramIds = new ArrayList<>();
        for (CLProgram cLProgram : list) {
            this.loyaltyProgramIds.add(cLProgram.id);
        }
        this.optinSms = bool;
        this.optinEmail = bool2;
        this.optinPush = bool3;
    }
}
