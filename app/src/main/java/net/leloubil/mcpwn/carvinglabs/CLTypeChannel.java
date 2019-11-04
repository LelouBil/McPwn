package net.leloubil.mcpwn.carvinglabs;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public enum CLTypeChannel {
    KIOSK,
    DRIVE,
    COUNTER,
    WEB;

    public static String toString(Context context, CLTypeChannel cLTypeChannel) {
        String str = "";
        switch (cLTypeChannel) {
            case KIOSK:
                return "aux bornes";
            case DRIVE:
                return "au McDrive";
            case COUNTER:
                return "au comptoir";
            case WEB:
                return "en ligne";
            default:
                return str;
        }
    }

    public static List<CLTypeChannel> getTypeListFromStringList(List<String> list) {
        List<CLTypeChannel> arrayList = new ArrayList<>();
        for (String typeFromString : list) {
            arrayList.add(getTypeFromString(typeFromString));
        }
        return arrayList;
    }

    public static CLTypeChannel getTypeFromString(String str) {
        if (str.equalsIgnoreCase(KIOSK.toString())) {
            return KIOSK;
        }
        if (str.equalsIgnoreCase(DRIVE.toString())) {
            return DRIVE;
        }
        if (str.equalsIgnoreCase(COUNTER.toString())) {
            return COUNTER;
        }
        return str.equalsIgnoreCase(WEB.toString()) ? WEB : null;
    }
}
