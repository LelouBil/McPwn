package net.leloubil.mcpwn;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import net.leloubil.mcpwn.async.CallBackPair;
import net.leloubil.mcpwn.mcapi.UserData;
import net.leloubil.mcpwn.mcapi.UserInfo;

import java.util.*;
import java.util.concurrent.TimeUnit;

@UtilityClass
public class DataManagement {

    @Getter
    private List<UserData> userDataList = new ArrayList<>();

    private int size;

    @SuppressLint("StaticFieldLeak")
    private CallBackPair<Class, Class> callbacks;
    private static boolean run = true;
    private Timer current;
    private static HashMap<String, UserInfo> tosave = new HashMap<>();

    private void loadData(Context c) {
        SharedPreferences pref = c.getSharedPreferences("userList", 0);
        size = pref.getAll().size();
        if (size == 0) {
            callbacks.success(DataManagement.class);
            return;
        }
        pref.getAll().forEach((key, value) -> UserData.fromRefresh((String) value, CallBackPair.duo(c, DataManagement::success, DataManagement::failure)));
    }

    private static void success(UserData data) {
        loadThis(data);
    }

    private static void failure(Class cls) {
        callbacks.failure(cls);
    }

    private void loadThis(UserData data) {
        getUserDataList().add(data);
        if (userDataList.size() == size) {
            callbacks.success(DataManagement.class);
        }
    }

    public void saveData(Context c) {
        SharedPreferences pref = c.getSharedPreferences("userList", 0);
        SharedPreferences.Editor editorlist = pref.edit();
        SharedPreferences.Editor editorInfo = c.getSharedPreferences("userInfo", 0).edit();
        for (int i = 0; i < userDataList.size(); i++) {
            editorlist.putString(String.valueOf(i), userDataList.get(i).getUserToken().getRefreshToken());
            if (userDataList.get(i).getInfo() == null) {
                int finalI = i;
                userDataList.get(i).syncInfo(inf -> DataManagement.saveInfo(userDataList.get(finalI).getUserToken().getRefreshToken(), inf, c));
            }
            editorInfo.putString(userDataList.get(i).getUserToken().getRefreshToken(), new Gson().toJson(userDataList.get(i).getInfo()));
        }
        editorlist.apply();
        editorInfo.apply();
    }

    private static void saveInfo(String refreshToken, UserInfo inf, Context c) {
        tosave.put(refreshToken, inf);

        if (current == null) current = new Timer();
        current.purge();
        current.cancel();
        current.schedule(new TimerTask() {
            @Override
            public void run() {
                DataManagement.saveAll(c);
            }
        }, TimeUnit.SECONDS.toMillis(5));
    }

    private static void saveAll(Context c) {
        SharedPreferences.Editor editorInfo = c.getSharedPreferences("userInfo", 0).edit();
        for (int i = 0; i < userDataList.size(); i++) {
            if (userDataList.get(i).getInfo() == null) {
                int finalI = i;
                userDataList.get(i).syncInfo(inf -> DataManagement.saveInfo(userDataList.get(finalI).getUserToken().getRefreshToken(), inf, c));
            }
            editorInfo.putString(userDataList.get(i).getUserToken().getRefreshToken(), new Gson().toJson(userDataList.get(i).getInfo()));
        }
        editorInfo.apply();
    }

    public void addUser(UserData d, Context c) {
        if (userDataList.stream().anyMatch(u -> u.getInfo().getRef().equals(d.getInfo().getRef()))) {
            ((Activity) c).runOnUiThread(() -> Toast.makeText(c, "This account is already connected !", Toast.LENGTH_LONG).show());
            return;
        }
        getUserDataList().add(d);
        saveData(c);
    }

    public void init(CallBackPair<Class, Class> callbacks) {
        DataManagement.callbacks = callbacks;
        DataManagement.loadData(callbacks.getContext());
    }

    public static boolean hasInfo(String token, Context c) {
        SharedPreferences pref = c.getSharedPreferences("userInfo", 0);
        return pref.getString(token, null) != null;
    }

    public static UserInfo cachedInfo(String token, Context c) {
        return new Gson().fromJson(c.getSharedPreferences("userInfo", 0).getString(token, null), UserInfo.class);
    }
}
