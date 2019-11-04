package net.leloubil.mcpwn;

import io.sentry.Sentry;

public class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler defaultUEH;

    /*
     * if any of the parameters is null, the respective functionality
     * will not be used
     */
    public CustomExceptionHandler() {
        this.defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
    }

    public void uncaughtException(Thread t, Throwable e) {
        Sentry.capture(e);
        defaultUEH.uncaughtException(t, e);
    }

}
