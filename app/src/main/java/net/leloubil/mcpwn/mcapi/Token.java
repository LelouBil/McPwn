package net.leloubil.mcpwn.mcapi;

import android.util.Log;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.sentry.Sentry;
import lombok.ToString;
import org.joda.time.LocalTime;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

@ToString
public abstract class Token {
    protected String token;
    protected int expiresIn;
    protected LocalTime expireTime;

    Token() {
    }

    Token(String token, int expiresIn) {
        Log.d("new token", "tooooo");
        this.token = token;
        this.expiresIn = expiresIn;
        setTime();
    }

    boolean isValid() {
        if (expireTime == null) return false;
        return !LocalTime.now().isAfter(expireTime);
    }

    private void update() {
        if (isValid()) return;
        forceUpdate();
        setTime();
    }

    protected abstract void forceUpdate();

    String getToken() {
        this.update();
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    void setTime() {
        this.expireTime = LocalTime.now().plusSeconds(expiresIn);
    }

    public int getExpiresIn() {
        return this.expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public LocalTime getExpireTime() {
        return this.expireTime;
    }

    @SuppressWarnings("unchecked")
    static class TokenDeserializer implements JsonDeserializer<Token> {
        public Token deserialize(JsonElement json, Type typeOfT,
                                 JsonDeserializationContext context) throws JsonParseException {
            Log.i("HJFHGFSDFJSDJFJSHFF", json.toString());
            Class<Token> cls = (Class<Token>) typeOfT;
            try {
                Token t = cls.getDeclaredConstructor(String.class, int.class)
                        .newInstance(json.getAsJsonObject().get("access_token").getAsString(), json.getAsJsonObject().get("expires_in").getAsInt());
                if (t instanceof UserToken) {
                    Field f = cls.getDeclaredField("refreshToken");
                    f.setAccessible(true);
                    f.set(t, json.getAsJsonObject().get("refresh_token").getAsString());
                }
                return t;
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException | NoSuchFieldException e) {
                Sentry.capture(e);
                e.printStackTrace();
                return null;
            }
        }
    }
}
