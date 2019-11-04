package net.leloubil.mcpwn.mcapi;

import android.os.Build;
import com.google.gson.GsonBuilder;
import io.sentry.Sentry;
import lombok.Getter;
import net.leloubil.mcpwn.async.CallBackPair;
import net.leloubil.mcpwn.carvinglabs.ICarvinglabs;
import net.mcdonalds.IMcDonaldsUserAuth;
import net.mcdonalds.IOAuth;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.util.Locale;

public class McAPI {

    static final String client_id = "gomcdo-android-app";

    static final String client_secret = "53ce5df5a9624b9498e517e4cf23091341ed901053573b1e0ae7fa2c26c650f1195d3fe76ddb9212";
    static final String baseUrl = "https://ws.mcdonalds.fr";
    private static final String carvbaseUrl = "https://ws-fid.mcdonalds.fr/";
    private static final int vcode = 511;
    private static final String vn = "4.5.4";
    @Getter
    private static IOAuth ioAuth;
    @Getter
    private static ICarvinglabs carvinglabs;
    @Getter
    private static IMcDonaldsUserAuth userAuth;

    public static void init(CallBackPair<Class, Class> callbacks) {
        try {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> chain.proceed(chain.request().newBuilder().addHeader("User-Agent", String.format(Locale.FRANCE, "%s/%s-%d (Android %s; %s; %s; %s)", "McDo France", vn, vcode, Build.VERSION.RELEASE, Build.MODEL, Build.BRAND, Locale.getDefault().getLanguage())).build()))
                    .addInterceptor(chain -> chain.proceed(chain.request().newBuilder().removeHeader("Cache-Control").addHeader("Cache-Control", "no-cache, no-store, must-revalidate")
                            .build()))
                    .addInterceptor(interceptor);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client.build())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .registerTypeHierarchyAdapter(Token.class, new Token.TokenDeserializer())
                            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").setLenient().create()))
                    .build();
            McAPI.ioAuth = retrofit.create(IOAuth.class);
            McAPI.userAuth = retrofit.create(IMcDonaldsUserAuth.class);
            Retrofit otherfit = new Retrofit.Builder()
                    .baseUrl(carvbaseUrl)
                    .client(client.build())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .registerTypeHierarchyAdapter(Token.class, new Token.TokenDeserializer())
                            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").setLenient().create()))
                    .build();
            McAPI.carvinglabs = otherfit.create(ICarvinglabs.class);
            callbacks.success(McAPI.class);
        } catch (Exception e) {
            Sentry.capture(e);
            callbacks.failure(McAPI.class);
        }

    }
}
