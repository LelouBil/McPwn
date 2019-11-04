package net.leloubil.mcpwn.carvinglabs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.Date;

@Getter
public class CLMemberShip {
    @SerializedName("completedAt")
    public Date dateCompletedAt;
    @SerializedName("createdAt")
    public Date dateCreatedAt;
    @SerializedName("expiresAt")
    public Date dateExpiresAt;
    @SerializedName("lastOrderAt")
    public Date dateLastOrderAt;
    @SerializedName("suspendedAt")
    public Date dateSuspendedAt;
    @SerializedName("id")
    public Long id;
    @SerializedName("loyaltyProgramId")
    public Long loyaltyProgramId;
    @SerializedName("nbPoints")
    public Long nbPoints;
    @SerializedName("nbStamps")
    public Long nbStamps;
    @SerializedName("nbStampsRemaining")
    public Long nbStampsRemaining;
    @SerializedName("optinEmail")
    public Boolean optinEmail;
    @SerializedName("optinPush")
    public Boolean optinPush;
    @SerializedName("optinSMS")
    public Boolean optinSMS;
    @SerializedName("optinWallet")
    public Boolean optinWallet;
    @SerializedName("restaurantId")
    public String restaurantId;
}
