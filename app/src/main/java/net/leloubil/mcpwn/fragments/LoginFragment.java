package net.leloubil.mcpwn.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.function.Consumer;

@SuppressWarnings("NullableProblems")
@AllArgsConstructor
public class LoginFragment extends Fragment {

    private static final String url = "https://connexion.mcdonalds.fr/oauth/authorize?response_type=code&client_id=gomcdo-android-app&redirect_uri=mcdo:login";
    private Consumer<String> onCodeReceive;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookies(null);
        cookieManager.setAcceptCookie(true);

        WebView myWebView = new WebView(getContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // chromium, enable hardware acceleration
            myWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            // older android version, disable hardware acceleration
            myWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        WebSettings ws = myWebView.getSettings();
        //noinspection deprecation
        ws.setSaveFormData(false);
        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
        ws.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (request.getUrl().toString().startsWith("mcdo:login")) {
                    onCode(request.getUrl().toString().split("code=")[1]);
                    return true;
                } else return false;
            }
        });
        myWebView.loadUrl(url);
        return myWebView;
    }

    private void onCode(String code) {
        onCodeReceive.accept(code);
    }

}
