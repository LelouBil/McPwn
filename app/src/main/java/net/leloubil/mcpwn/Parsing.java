package net.leloubil.mcpwn;

import io.sentry.Sentry;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Parsing {
    public static JSONObject toJson(String string) {
        try {
            return new JSONObject(string);
        } catch (JSONException e) {
            Sentry.capture(e);
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, String> toMap(String[] strings, String[] strings1) {
        if (strings.length != strings1.length) return null;
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            map.put(strings[i], strings1[i]);
        }
        return map;
    }
}
