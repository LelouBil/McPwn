package net.leloubil.mcpwn.frontpage;

import lombok.experimental.UtilityClass;
import net.leloubil.mcpwn.DataManagement;
import net.leloubil.mcpwn.mcapi.UserData;

import java.util.Comparator;

@UtilityClass
public class SpecialDataGetter {

    public UserData mostPoints() {
        return DataManagement.getUserDataList().stream().max(Comparator.comparingLong(d -> d.getMemberShip().getNbPoints())).orElse(null);
    }

    public UserData leastPoints() {
        return DataManagement.getUserDataList().stream().min(Comparator.comparingLong(d -> d.getMemberShip().getNbPoints())).orElse(null);
    }
}
